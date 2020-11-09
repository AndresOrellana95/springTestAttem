package com.demo.donations.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.donations.model.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    boolean existsByDocument(String document);

    boolean existsByEmail(String email);

    UserEntity findByEmail(String email);
}
