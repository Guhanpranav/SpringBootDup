package com.example.jobportal.repository;


import com.example.jobportal.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepo extends JpaRepository<Jobs,Integer> {

    @Query(value = "select * from jobs where jobs.id NOT IN(select job_id from user_jobs where user_jobs.user_id = :id)", nativeQuery = true)
    Optional<List<Jobs>> findJobExcludingUser(int id);
}
