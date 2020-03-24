package com.enuocms.web.controller;

import com.enuocms.account.admin.AdminService;
import com.enuocms.web.util.RandCodeImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Controller
@RequestMapping("account")
@Slf4j
public class AccountCtroller {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(
            @RequestParam(value="error",required=false,defaultValue="") String error,
            HttpServletRequest request,
            Model model) {

        String referer = request.getHeader("Referer");
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:/";
        }

        if(error!=null){
            model.addAttribute("error", error);
        }

        model.addAttribute("referer", referer);
        return "/account/login";
    }

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String login( Model model,
//                         HttpServletRequest request,
//                         String userName,
//                         String password){
//        String error = null;
//        String errorClassName = (String)request.getAttribute("shiroLoginFailure");
//        if(UnknownAccountException.class.getName().equals(errorClassName)) {
//            error = "用户不存在";
//        } else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {
//            error = "您输入的密码不正确";
//        } else if(DisabledAccountException.class.getName().equals(errorClassName)) {
//            error = "您的企业或账户已被禁用,请联系客服人员!";
//        }  else if(errorClassName != null) {
//            error = "用户名/密码错误";
//        }
//
//        if (error != null) {// 出错了，返回登录页面
//            model.addAttribute("error", error);
//            return "/account/login";
//        } else {
//            return null;
//        }
//    }

    @RequestMapping(value = "checkLogin", method = RequestMethod.POST)
    public String login( Model model,
                         HttpServletRequest request,
                         String username,
                         String password,
                         Boolean rememberMe,
                         RedirectAttributes redirectAttributes){

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch(UnknownAccountException uae){
            redirectAttributes.addFlashAttribute("message", "未知账户");
        }catch(IncorrectCredentialsException ice){
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        }catch(LockedAccountException lae){
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        if(subject.isAuthenticated()){
            token.setRememberMe(rememberMe!=null?rememberMe:false);
            return "redirect:/";
        }else{
            token.clear();
            return "redirect:/account/login";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }

    /**
     * 生成验证码图片io流
     */
    @RequestMapping(value = "validCode", method = RequestMethod.GET)
    public void generateImage(HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        RandCodeImageUtils.generateImage(response, request);
    }
}
