package com.demo.donations.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.donations.model.entity.UserLevelEntity;

@Repository
public interface UserLevelRepo extends JpaRepository<UserLevelEntity, Long> {
    
    UserLevelEntity findByIdLevel(Long idLevel);

}
