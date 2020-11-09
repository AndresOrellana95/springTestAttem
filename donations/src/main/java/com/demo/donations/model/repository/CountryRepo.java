package com.demo.donations.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.donations.model.entity.CountryEntity;

@Repository
public interface CountryRepo extends JpaRepository<CountryEntity, Long> {
    
    CountryEntity findByIdCountry(Long idCountry);
}
