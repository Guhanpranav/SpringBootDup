package com.sql.starter.repository;

import com.sql.starter.entity.Employee;
import com.sql.starter.projection.EmpProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer> {

    @Query("SELECT e.name AS name, e.phNo AS phNo FROM Employee e WHERE e.name = :name")
    Optional<EmpProjection> findByName(String name);
}
