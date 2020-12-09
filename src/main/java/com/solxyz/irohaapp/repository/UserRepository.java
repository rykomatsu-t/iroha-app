package com.solxyz.irohaapp.repository;

import com.solxyz.irohaapp.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    public UserInfo findByName(String name);
}
