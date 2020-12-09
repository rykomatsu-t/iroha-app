package com.solxyz.irohaapp.controller;

import com.solxyz.irohaapp.block.SendAsset;
import com.solxyz.irohaapp.entity.UserInfo;
import com.solxyz.irohaapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * ホーム画面での操作に関わるコントローラー
 */
@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @Autowired
    SendAsset sendAsset;

    @GetMapping("/home")
    public ModelAndView home(ModelAndView mav){
        mav.addObject("login_user", session.getAttribute("login_user"));
        mav.setViewName("home");
        return mav;
    }

    /**
     * 入力された名前をもとに、ユーザーを探して表示する
     *
     * @param mav
     * @param name
     * @return
     */
    @GetMapping("/search")
    public ModelAndView getUserInfo(ModelAndView mav, @RequestParam("name") String name) {
        mav.addObject("login_user", session.getAttribute("login_user"));
        System.out.println(session.getAttribute("login_user"));

        UserInfo user = userService.searchUserByName(name);

        if (user != null) {
            mav.addObject("userid", user.getId());
            mav.addObject("username", user.getName());
        }
        mav.setViewName("home");
        return mav;
    }

    /**
     * @param mav
     * @param to
     * @return
     */
    @PostMapping("/send")
    public ModelAndView send(ModelAndView mav, @RequestParam("to") String to) throws Exception {
        mav.addObject("login_user", session.getAttribute("login_user"));

        String from = (String) session.getAttribute("login_user");

        System.out.println("from: "+from);
        System.out.println("to: "+to);

        // 送る側、受け取る側、いくら渡すかの情報を入力し、ブロックに書き込む
        sendAsset.send(from, to, 10.0);

        mav.setViewName("send");
        return mav;
    }

}
