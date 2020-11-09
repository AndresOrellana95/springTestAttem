package com.demo.donations.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.donations.model.entity.BinnacleEntity;

@Repository
public interface BinnacleRepo extends JpaRepository<BinnacleEntity, Long> {
    
}
