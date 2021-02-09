package com.solxyz.irohaapp.controller;

import javax.servlet.http.HttpSession;

import com.solxyz.irohaapp.entity.Department;
import com.solxyz.irohaapp.entity.History;
import com.solxyz.irohaapp.service.DepartmentService;
import com.solxyz.irohaapp.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.solxyz.irohaapp.block.SendAsset;
import com.solxyz.irohaapp.entity.UserInfo;
import com.solxyz.irohaapp.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * ホーム画面での操作に関わるコントローラー
 */
@Controller
public class HomeController {

    @Autowired
    HistoryService historyService;

    @Autowired
    UserService userService;

    @Autowired
    DepartmentService depService;

    /**
     * session
     * 主にログイン情報を格納する
     */
    @Autowired
    HttpSession session;

    /**
     * ホーム画面を表示する
     * @param mav
     * @return
     */
    @GetMapping("/home")
    public ModelAndView home(ModelAndView mav) {

        // ログイン者の名前を取得
        String myName = (String) session.getAttribute("login_user");

        // 名前をもとにユーザーを取得
        UserInfo self = userService.getMyInfo(myName);

        // ユーザーが存在しない場合、ログインしていないとみなし、ログイン画面に飛ばす
        if (self == null) {
            mav.setViewName("redirect:/");
            return mav;
        }

        // ユーザー一覧を取得
        List<UserInfo> userList = userService.getUserList();

        // 部署一覧を取得
        List<Department> depList = depService.getDepartmentList();

        mav.addObject("userlist", userList);
        mav.addObject("deplist", depList);
        mav.addObject("mypoint", self.getQuantity());
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
    public ModelAndView getUserInfo(ModelAndView mav, @RequestParam("name") String name, @RequestParam("dep") int dep) {

        String myName = (String) session.getAttribute("login_user");
        UserInfo self = userService.getMyInfo(myName);

        // ログイン状態のチェック
        // ログインしていなければログイン画面へ飛ばす
        if (self == null) {
            mav.setViewName("redirect:/");
            return mav;
        }

        // ユーザー一覧を代入する変数を宣言
        List<UserInfo> userList = new ArrayList<>();

        // 検索条件が入力されていたらそれで検索、されていなかったら全てのユーザーを取得
        if (dep == 0 && (name == null || "".equals(name))) {
            userList = userService.getUserList();
        } else {
            userList = userService.searchUser(dep, name);
        }

        List<Department> depList = depService.getDepartmentList();

        mav.addObject("userlist", userList);
        mav.addObject("mypoint", self.getQuantity());
        mav.addObject("deplist", depList);
        mav.setViewName("home");
        return mav;
    }

    /**
     * @param to
     * @return
     */
    @PostMapping("/send")
    public String send(RedirectAttributes attr, @RequestParam("to") String to, @RequestParam("quantity") int quantity) throws Exception {

        String myName = (String) session.getAttribute("login_user");
        UserInfo self = userService.getMyInfo(myName);

        // ログイン状態のチェック
        // ログインしていなければログイン画面へ飛ばす
        if (self == null) {
            return "redirect:/";
        }

        boolean isSendCompleted = userService.send(self.getName(), to, quantity);
        String sendMsg = isSendCompleted ? to + "さんに " + quantity + "pt を送りました。" : "コインの送信に失敗しました。";

        attr.addFlashAttribute("sendMsg", sendMsg);

        return "redirect:/home";
    }

    /**
     *
     */
    @GetMapping("/history")
    public ModelAndView getHistory(ModelAndView mav){
        String myName = (String) session.getAttribute("login_user");
        UserInfo self = userService.getMyInfo(myName);

        // ログイン状態のチェック
        // ログインしていなければログイン画面へ飛ばす
        if (self == null) {
            mav.setViewName("redirect:/");
            return mav;
        }

        List<History> history = historyService.getHistory(self);

        mav.addObject("historylist", history);
        mav.setViewName("history");

        return mav;
    }

}
