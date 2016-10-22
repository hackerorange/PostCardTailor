package com.soho.postCardTailor.controller;

import com.soho.postCardTailor.business.IOperatorBusiness;
import com.soho.postCardTailor.exception.NoSuchOperatorException;
import com.soho.postCardTailor.exception.OperatorPasswordWrongException;
import com.soho.postCardTailor.pojo.Operator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 操作员控制器
 */
@Controller
public class OperatorController {
    @Resource
    IOperatorBusiness operatorBusiness;

    @RequestMapping({"/", ""})
    public String index() {
        return "redirect:login";
    }

    /**
     * GET——登录页面
     *
     * @return 转发到登录页面
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpSession session) {
        Operator operator = (Operator) session.getAttribute("operator");
        if (operator != null) {//如果当前会话已经有用户登录，跳转到主页面
            return "redirect:mainPage";
        } else {
            return "operatorLogin";
        }
    }

    /**
     * POST——验证登录信息
     *
     * @param operator 页面传过来的对象
     * @param session  登录成功后，用来保存用户信息
     * @return 登录成功页面
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(Operator operator, HttpSession session) throws NoSuchOperatorException, OperatorPasswordWrongException {
        System.out.println("执行此操作");
        System.out.println(operator);
        session.setAttribute("operator", operatorBusiness.login(operator));
        return "redirect:mainPage";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("operator");
        return "redirect:login";
    }

    @RequestMapping("mainPage")
    public String mainPage() {
        System.out.println("执行此操作主页面操作");
        return "mainForm";
    }
}
