package com.example.banking.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private long phNo;

    @Column(unique = true)
    private long accNo;

    private String ifsc;

    private String password;

    private long balance = 1000L;

    @Enumerated(EnumType.STRING)
    private Role role;

    private enum Role{
        USER,
        ADMIN
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transaction> transactions;

    @PrePersist
    private void generateFields(){
        accNo = generateAccountNumber();
        ifsc = generateIfsc();
    }

    private Long generateAccountNumber(){
        //System.out.println(1000000000L + phNo);
        return 1000000000L + phNo;
    }

    private String generateIfsc(){
        //String str = (char)('A' + new Random().nextInt(26) )+ "" + (char)('A' + new Random().nextInt(26) ) +"" + (char)('A' + new Random().nextInt(26) );
        //int code = new Random().nextInt(1000);
        return "CBB12335546";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+getRole()));
    }

    @Override
    public String getUsername() {
        return String.valueOf(phNo);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
