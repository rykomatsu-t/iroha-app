package com.solxyz.irohaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    /**
     * ルートにアクセスしたらログインページにリダイレクト
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * ログイン処理を行う
     * @param mav
     * @param id ログインID
     * @param password ログインパスワード
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(ModelAndView mav, @RequestParam("id") String id, @RequestParam("password") String password) {

        // TODO idとpasswordをもとにログインの成否を判定
        // 例: boolean isLogin = loginService.login(id, password);
        boolean isLogin = true;

        if(isLogin){
            mav.setViewName("home");
            return mav;
        } else {
            // TODO ログイン失敗時にエラーページに飛ばす
            // 例: mav.setViewName("errorpage");
            return mav;
        }
    }

}
