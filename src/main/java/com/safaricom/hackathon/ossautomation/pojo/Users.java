package com.safaricom.hackathon.ossautomation.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "users", schema = "oss_automation")
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class Users extends AuditModel implements UserDetails {
    @EmbeddedId
    private UserIdentifier userCode;

    @NotNull
    @Size(max = 255)
    @Column(name = "SURNAME")
    private String surname;

    @NotNull
    @Size(max = 255)
    @Column(name = "OTHERNAMES")
    private String otherNames;

    @NotNull
    @Size(max = 255)
    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Size(max = 255)
    @Column(name = "MSISDN")
    private String msisdn;

    @NotNull
    @Size(max = 255)
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    public Users() {
    }

    public Users(UserIdentifier userCode, @NotNull @Size(max = 255) String surname, @NotNull @Size(max = 255) String otherNames,
                 @NotNull @Size(max = 255) @Email String email, @NotNull @Size(max = 255) String msisdn, @NotNull @Size(max = 255) String username, @NotNull String password, List<String> roles) {
        this.userCode = userCode;
        this.surname = surname;
        this.otherNames = otherNames;
        this.email = email;
        this.msisdn = msisdn;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserIdentifier getUserCode() {
        return userCode;
    }

    public void setUserCode(UserIdentifier userIdentifier) {
        this.userCode = userIdentifier;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getUserCode().equals(users.getUserCode()) &&
                getSurname().equals(users.getSurname()) &&
                getOtherNames().equals(users.getOtherNames()) &&
                getEmail().equals(users.getEmail()) &&
                getMsisdn().equals(users.getMsisdn()) &&
                getUsername().equals(users.getUsername()) &&
                getPassword().equals(users.getPassword()) &&
                getRoles().equals(users.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserCode(), getSurname(), getOtherNames(), getEmail(), getMsisdn(), getUsername(), getPassword(), getRoles());
    }
}
