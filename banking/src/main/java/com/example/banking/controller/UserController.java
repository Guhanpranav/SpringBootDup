package com.example.banking.controller;

import com.example.banking.entity.User;
import com.example.banking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/start")
    public ModelAndView home(){
        return new ModelAndView("login");
    }

    @GetMapping("/getUser")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok(users);

    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        try{
            userRepo.save(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("User registry failed => "+ user.toString() + " with error "+e.getMessage());
        }
        return ResponseEntity.ok("User registered successfully => " + user.toString());
    }

    //ADMIN
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable int id, @RequestBody User user){
        Optional<User> u = userRepo.findById(id);
        if(u.isEmpty()){
            return ResponseEntity.ok("User not found");
        }
        User existingUser = u.get();
        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getPhNo() != 0) {
            existingUser.setPhNo(user.getPhNo());
        }
        if (user.getAccNo() != 0) {
            existingUser.setAccNo(user.getAccNo());
        }
        if (user.getIfsc() != null) {
            existingUser.setIfsc(user.getIfsc());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        if (user.getBalance() != 0) {
            existingUser.setBalance(user.getBalance());
        }
        if (user.getRole() != null) {
            existingUser.setRole(user.getRole());
        }
        userRepo.save(existingUser);
        return ResponseEntity.ok("User updated");
    }

    @PostMapping("/log-in")
    public ModelAndView userLogin(@ModelAttribute User usr, HttpSession ss){
        // System.out.println(usr.toString());
        long phNo = usr.getPhNo();
        String password = usr.getPassword();


        Optional<User> originalUser = userRepo.findByPhNo(phNo);

        if(originalUser.isPresent() && (originalUser.get().getPassword().equals(password))){
            ss.setAttribute("currentUserId",originalUser.get().getId());
            return new ModelAndView("home");
        }
        return new ModelAndView("login");
    }
}
