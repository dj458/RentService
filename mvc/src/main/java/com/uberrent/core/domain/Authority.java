package com.uberrent.core.domain;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table (name= "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "authorities_id_seq")
    @SequenceGenerator(name = "authorities_id_seq", sequenceName = "authorities_id_seq",allocationSize=1)
    private Long id;

    @Column(name = "role")
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //static String registerRole="REGISTERED_USER";

    public String getRole() { return role; }

    public void setRole(String role) {this.role = role;}

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user;}
}
