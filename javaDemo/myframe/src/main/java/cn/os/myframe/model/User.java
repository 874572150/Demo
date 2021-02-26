package cn.os.myframe.model;

import lombok.Data;

import javax.persistence.*;

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

    @Column(name = "role_id")
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Role role;

}
