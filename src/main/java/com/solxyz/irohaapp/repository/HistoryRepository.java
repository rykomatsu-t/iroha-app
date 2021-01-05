package com.solxyz.irohaapp.repository;

import com.solxyz.irohaapp.entity.History;
import com.solxyz.irohaapp.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    public List<History> findBySendIdOrRecieveIdOrderBySendTimeAsc(UserInfo sendId, UserInfo recieveId);
}
