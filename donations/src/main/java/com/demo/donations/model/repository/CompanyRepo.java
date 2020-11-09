package com.demo.donations.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.demo.donations.model.entity.CompanyEntity;
import com.demo.donations.model.entity.CountryEntity;

@Repository
public interface CompanyRepo extends JpaRepository<CompanyEntity, Long>  {
    
    CompanyEntity findByIdCompany(Long idCompany);
    List<CompanyEntity> findByCountry(CountryEntity country);
}
