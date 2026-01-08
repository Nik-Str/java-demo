package com.example.demo.repository;

import com.example.demo.model.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {}
