package com.boots.entity;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Base64;
import java.util.Collection;
import java.util.Set;

@Entity
    @Table(name = "t_user")
    public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Size(min=2, message = "Не меньше 5 знаков")
        private String username;
        @Size(min=2, message = "Не меньше 5 знаков")
        private String password;


        private String email;
        public String getEmail() {
        return email;
    }
        public void setEmail(String email) {
        this.email = email;
    }

        @Lob
        @Column(name = "profileImage", columnDefinition = "bytea")
        @Type(type="org.hibernate.type.BinaryType")
        private byte[] profileImage;
        public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
        public String getProfileImage() {
            if (profileImage != null) {
                return Base64.getEncoder().encodeToString(profileImage);
            } else {
                return null;
        }}



        @Transient
        private String passwordConfirm;
        @ManyToMany(fetch = FetchType.EAGER)
        private Set<Role> roles;

        public User() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String getUsername() {
            return username;
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

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return getRoles();
        }

        @Override
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPasswordConfirm() {
            return passwordConfirm;
        }

        public void setPasswordConfirm(String passwordConfirm) {
            this.passwordConfirm = passwordConfirm;
        }

        public Set<Role> getRoles() {
            return roles;
        }

        public void setRoles(Set<Role> roles) {
            this.roles = roles;
        }
    }




