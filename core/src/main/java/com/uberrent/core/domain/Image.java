package com.uberrent.core.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "images_id_seq")
    @SequenceGenerator(name = "images_id_seq", sequenceName = "images_id_seq")
    private Long id;

    @Column(name = "url")
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId(){ return id;}

    public void setUrl (String url){this.url=url;}
    public String getUrl (){return url;}

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user;}
}
