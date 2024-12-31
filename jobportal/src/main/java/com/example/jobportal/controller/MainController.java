package com.example.jobportal.controller;


import com.example.jobportal.entity.Jobs;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.JobRepo;
import com.example.jobportal.repository.userRepo;
import com.example.jobportal.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    userRepo usr;
    @Autowired
    JobRepo job;

    @Autowired
    MessageService mservice;

    @GetMapping("/login")
    public ModelAndView test(){
        return new ModelAndView("login");
    }

    @GetMapping("/registerJob")
    public ModelAndView registerJob(){
        return new ModelAndView("jobRegister");
    }


    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @PostMapping("/registerUser")
    public ModelAndView registerUser(@ModelAttribute User user, Model m){
        Optional<User> u = Optional.ofNullable(usr.save(user));
        if(u.isPresent()){
            m.addAttribute("name", user.getUsername());
            return new ModelAndView("registerd");
        }

        return new ModelAndView("register");
    }


    @PostMapping("/login")
    public ModelAndView registerUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session){
        Optional<User> optionalUser = usr.findByEmail(email);
        if (optionalUser.isPresent()) {
            User temp_usr = optionalUser.get();
            session.setAttribute("userId", temp_usr.getId());
            session.setAttribute("userEmail", email);


            if(temp_usr.getPassword().equals(password)){
                return new ModelAndView("home");
            }
        }
        return new ModelAndView("register");
    }


    @PostMapping("/registerJob")
    public ResponseEntity<Jobs> registerJob(@ModelAttribute Jobs jobs){
        Optional<Jobs> j = Optional.ofNullable(job.save(jobs));
        if(j.isPresent()){
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        }

        return new ResponseEntity<>(jobs, HttpStatus.BAD_REQUEST);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    //@Secured("JOBSEEKER")
    @GetMapping("/applyJobs")
    public ModelAndView applyJob(Model m, HttpSession session){
        int userId = (int)session.getAttribute("userId");
        List<Jobs> jlist= job.findJobExcludingUser(userId).get();
        System.out.println(jlist);
        m.addAttribute("jobs", jlist);
        return new ModelAndView("applyjobs");
    }


    @GetMapping("/apply/{id}")
    public RedirectView applyJob(@PathVariable String id, HttpSession session){
        String userEmail = (String)session.getAttribute("userEmail");
        User temp_user = usr.findByEmail(userEmail).get();
        Jobs temp_job = job.findById(Integer.parseInt(id)).get();

        temp_user.getJob().add(temp_job);

        //sending mail
        /*
        try {
            String title = "Regarding " + temp_job.getTitle();
            String content = "This user is interested in the job you posted";
            mservice.sentMail(temp_job.getEmail(), title, content);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            usr.save(temp_user);
        }
         */
        usr.save(temp_user);
        return new RedirectView("/api/applyJobs");
    }


    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }
}
