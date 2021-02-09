package com.solxyz.irohaapp.service;

import com.solxyz.irohaapp.entity.History;
import com.solxyz.irohaapp.entity.UserInfo;
import com.solxyz.irohaapp.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository repository;

    /**
     * ログインユーザーが関係する取引履歴を取得する
     * @param loginUser
     * @return 取引履歴一覧
     */
    public List<History> getHistory(UserInfo loginUser){
        List<History> historyList = repository.findBySendIdOrReceiveIdOrderBySendTimeDesc(loginUser, loginUser);
        return historyList;
    }
}
