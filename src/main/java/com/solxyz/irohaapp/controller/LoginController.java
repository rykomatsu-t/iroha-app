package com.solxyz.irohaapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.solxyz.irohaapp.service.UserService;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    /**
     * ルートにアクセスしたらログインページにリダイレクト
     * @return
     */
    @GetMapping("/")
    @PostMapping("/")
    public String index() {
        // index.htmlに飛ぶ
        return "index";
    }

    /**
     * ログイン処理を行う
     * @param mav
     * @param name ユーザー名
     * @param password ログインパスワード
     * @return
     */
    @PostMapping("/login") // localhost:8080/loginにアクセスされたときにこのメソッドが呼ばれる
    public ModelAndView login(ModelAndView mav, @RequestParam("name") String name, @RequestParam("password") String password) {

        boolean isLogin = userService.login(name, password);

        if(isLogin){
            session.setAttribute("login_user", name);
            mav.setViewName("redirect:/home");

            return mav;
        } else {
            mav.addObject("loginFailed", true);
            mav.setViewName("index");
            return mav;
        }
    }

}
