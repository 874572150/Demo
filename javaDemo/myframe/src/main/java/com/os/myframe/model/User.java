package com.os.myframe.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User extends BaseModel {
    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "nickname")
    private String nickname;

    @OneToOne
    private Role role;

}
