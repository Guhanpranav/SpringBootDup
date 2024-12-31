package com.example.banking.repo;

import com.example.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    public Optional<User> findByAccNo(Long accNo);

    public Optional<User> findByPhNo(long phNo);
}
