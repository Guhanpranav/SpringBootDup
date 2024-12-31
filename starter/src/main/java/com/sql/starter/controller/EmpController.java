package com.sql.starter.controller;


import com.sql.starter.entity.Employee;
import com.sql.starter.projection.EmpProjection;
import com.sql.starter.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmpController {


    @Autowired
    EmpRepo repo;

    @PostMapping("/api/add")
    public ResponseEntity<Employee> addEmp(@RequestBody Employee temp){
        Optional<Employee> ans = Optional.ofNullable(repo.save(temp));
        if(ans.isPresent()) {
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }

        return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/get/{name}")
    public ResponseEntity<EmpProjection> getEmp(@PathVariable String name){
        Optional<EmpProjection> det = repo.findByName(name);
        if(det.isPresent()){
            return new ResponseEntity<>(det.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
