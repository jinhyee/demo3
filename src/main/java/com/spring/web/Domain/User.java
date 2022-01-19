package com.spring.web.Domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "test_user")
public class User implements UserDetails {
    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "id", unique = true, nullable = false, length = 100)
    private String id;

    @Column(name = "pw", nullable = false, length = 100)
    private String pw;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "tel", nullable = false, length = 100)
    private String tel;

    @Column(name = "postcode", nullable = false, length = 100)
    private String postcode;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "detailaddress", nullable = false, length = 100)
    private String detailaddress;

    @Column(name = "extraaddress", nullable = false, length = 100)
    private String extraaddress;

    @Column(name = "auth", nullable = false, length = 100)
    private String auth;

    @Column(name = "joindate", nullable = false, length = 100)
    private LocalDateTime joindate;

    @Builder
    public User(String id, String pw, String name, String email, String tel, String postcode, String address, String detailaddress, String extraaddress, String auth, LocalDateTime joindate) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.postcode = postcode;
        this.address = address;
        this.detailaddress = detailaddress;
        this.extraaddress = extraaddress;
        this.auth = auth;
        this.joindate = joindate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles=new HashSet<>();
        for(String role:auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return id;
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
