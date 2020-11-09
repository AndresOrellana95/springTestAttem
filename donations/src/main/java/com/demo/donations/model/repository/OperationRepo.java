package com.demo.donations.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import com.demo.donations.model.entity.OperationEntity;

@Repository
public interface OperationRepo extends JpaRepository<OperationEntity, Long> {
    //SELECT * FROM employees ORDER BY employment_date DESC FETCH FIRST ROW ONLY;
    @Query(value = "SELECT * FROM donations.operation operation WHERE operation.id_country = ?1 " + 
    " AND operation.id_user = ?2 " +
    " ORDER BY operation.execution DESC FETCH FIRST ROW ONLY", nativeQuery = true)
    OperationEntity findLastUserOperationByCountry(Long country, Long user);

    @Query(value = "SELECT * FROM donations.operation operation WHERE operation.id_user = ?1 AND operation.execution > ?2 AND operation.execution < ?3 ORDER BY operation.execution DESC", nativeQuery = true)
    List<OperationEntity> getDonations(Long user, Date start, Date end);

    @Query(value = "SELECT * FROM donations.operation operation WHERE operation.execution > ?1 AND operation.execution < ?2 ORDER BY operation.execution DESC", nativeQuery = true)
    List<OperationEntity> getDonationsInfo(Date start, Date end);
}
