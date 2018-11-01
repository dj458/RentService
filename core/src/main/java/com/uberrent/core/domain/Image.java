package com.uberrent.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "images_id_seq")
    @SequenceGenerator(name = "images_id_seq", sequenceName = "images_id_seq",allocationSize=1)
    private Long id;

    @Column(name = "url")
    private String url;
    @Column(name = "s3Key")
    private String s3Key;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId(){ return id;}

    @Transient
    public void setUrl (String url){this.url=url;}
    public String getUrl (){return url;}

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user;}

    public void setS3Key(String s3Key){this.s3Key=s3Key;}
    public String getS3Key(){return s3Key;}
}
