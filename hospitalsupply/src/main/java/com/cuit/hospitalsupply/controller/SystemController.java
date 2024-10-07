package com.cuit.hospitalsupply.controller;

import com.cuit.hospitalsupply.pojo.Hospital;
import com.cuit.hospitalsupply.pojo.LoginForm;
import com.cuit.hospitalsupply.pojo.Supplier;
import com.cuit.hospitalsupply.pojo.User;
import com.cuit.hospitalsupply.service.HospitalService;
import com.cuit.hospitalsupply.service.SupplierService;
import com.cuit.hospitalsupply.service.UserService;
import com.cuit.hospitalsupply.util.CreateVerifiCodeImage;
import com.cuit.hospitalsupply.util.JwtHelper;
import com.cuit.hospitalsupply.util.Result;
import com.cuit.hospitalsupply.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Api(tags = "系统控制器")
@RestController
@RequestMapping("/hs/system")
public class SystemController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private UserService userService;

    @ApiOperation("通过token口令，获取当前登录的用户信息的方法")
    @GetMapping("/getInfo")
    public Result getInfo(
            @ApiParam("token口令 ") @RequestHeader("token") String token
    ) {
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration){
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        // 从token中解析出 用户 ID 和用户类型
        String userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);

        Map<String, Object> map = new LinkedHashMap<>();
        switch (userType){
            case 1:
                Hospital hospital = hospitalService.getByHospitalId(userId);
                map.put("userType",1);
                map.put("user",hospital);
                break;
            case 2:
                Supplier supplier = supplierService.getBySupplierId(userId);
                map.put("userType",2);
                map.put("user",supplier);
                break;
        }
        return Result.ok(map).message("成功获取登录用户信息");
    }

    @ApiOperation("登录的方法")
    @PostMapping("/login")
    public Result login(
            @ApiParam("登录提交的Form表单") @RequestBody LoginForm loginForm,
            HttpServletRequest request)
    {
        // 验证码校验
        HttpSession session = request.getSession();
        String sessionVerifiCode = (String)session.getAttribute("verifiCode");
        String loginVerifiCode = loginForm.getVerifiCode();

        if ("".equals(sessionVerifiCode) || null == sessionVerifiCode){
            return Result.fail().message("验证码失效，请刷新后重试");
        }
        if (!sessionVerifiCode.equalsIgnoreCase(loginVerifiCode)){
            System.out.println(loginVerifiCode +" loginVerifiCode");
            return Result.fail().message("验证码有误，请小心输入后重试");
        }
        // 从session中移除现有验证码
        session.removeAttribute("verifiCode");
        // 分用户类型进行校验。
        //准备一个map用户存放响应数据
        Map<String, Object> map = new LinkedHashMap<>();
        switch (loginForm.getUsertype()) {
            case 1:
                try {
                    Hospital hospital = hospitalService.login(loginForm);
                    if (null != hospital) {
                        // 用户的类型和ID 转换为一个密文，以token向客户端反馈
                        map.put("token", JwtHelper.createToken(hospital.getHospitalid(), 1));
                        map.put("userType",1);
                    } else {
                        throw new RuntimeException("用户名或密码有误");
                    }
                    return Result.ok(map).message("登录成功");
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 2:
                try {
                    Supplier supplier = supplierService.login(loginForm);
                    if (null != supplier) {
                        // 用户的类型和ID 转换为一个密文，以token向客户端反馈
                        map.put("token", JwtHelper.createToken(supplier.getSupplierid(), 2));
                        map.put("userType",2);
                    } else {
                        throw new RuntimeException("用户名或密码有误");
                    }
                    return Result.ok(map).message("登录成功");
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 3:
                try {
                    User user = userService.login(loginForm);
                    if (null != user) {
                        // 用户的类型和ID 转换为一个密文，以token向客户端反馈
                        map.put("token", JwtHelper.createToken(user.getUsername(), 3));
                        map.put("userType",3);
                    } else {
                        throw new RuntimeException("用户名或密码有误");
                    }
                    return Result.ok(map).message("管理员登录成功");
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
        }
        return Result.fail().message("查无此用户");
    }

    @ApiOperation(("获取验证码图片"))
    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response){
        // 获取验证码图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        // 获取图片上验证码
        String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());
        // 将验证码文本放入session作用域，
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode",verifiCode);
        // 设置响应头，指定响应的内容类型为图片/jpeg
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        // 将验证码图片响应给浏览器
        try {
            ImageIO.write(verifiCodeImage,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
