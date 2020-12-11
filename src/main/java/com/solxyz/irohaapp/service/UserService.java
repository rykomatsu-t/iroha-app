package com.solxyz.irohaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solxyz.irohaapp.block.SendAsset;
import com.solxyz.irohaapp.entity.UserInfo;
import com.solxyz.irohaapp.repository.UserRepository;

/**
 * ユーザーを検索するサービス
 */
@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    SendAsset sendAsset;


    /**
     * 入力したname と pass をもとにログイン判定
     * @param name ユーザー名
     * @param pass パスワード
     * @return
     */
    public boolean login(String name, String pass){
        UserInfo user = repository.findByName(name);
        if(user.getPassword() == pass){
            return true;
        } else {
            return false;
        }
    }

    /**
     * ユーザー名からユーザーの情報を取得
     * @param name
     * @return
     */
    public UserInfo searchUserByName(String name) {
        return repository.findByName(name);
    }

}
