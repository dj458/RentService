package com.uberrent.core.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq")
    private Long id;
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name ="account_expired")
    private Boolean accountExpired;

    @Column (name ="account_locked")
    private Boolean accountLocker;

    @Column(name ="credential_expired")
    private Boolean credentialsExpired;

    @Column(name ="enabled")
    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<Equipment> equipments;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy ="user" )
    @Transient
    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    @Column(name = "last_name")
    private String lastName;

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void  setAuthorities (Collection<? extends GrantedAuthority> authorities) {
        this.authorities=authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public void setUsername(String username){ this.username = username; }

    public String getEmail() {return email; }

    public void setEmail(String email){ this.email = email; }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocker;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocker(Boolean accountLocker) {
        this.accountLocker = accountLocker;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = !credentialsExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}