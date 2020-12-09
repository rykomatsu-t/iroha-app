package com.solxyz.irohaapp.controller;

import com.solxyz.irohaapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Name;
import javax.servlet.http.HttpSession;

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
    public String index() {
        userService.addUser();
        return "index";
    }

    /**
     * ログイン処理を行う
     * @param mav
     * @param name ユーザー名
     * @param password ログインパスワード
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(ModelAndView mav, @RequestParam("name") String name, @RequestParam("password") String password) {

        // TODO nameとpasswordをもとにログインの成否を判定
        boolean isLogin = true;

        if(isLogin){
            session.setAttribute("login_user", name);
            mav.addObject("login_user", name);
            mav.setViewName("redirect:/home");
            return mav;
        } else {
            // TODO ログイン失敗時にエラーページに飛ばす
            // 例: mav.setViewName("errorpage");
            return mav;
        }
    }

}
