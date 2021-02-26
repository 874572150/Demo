package cn.os.myframe.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role extends BaseModel {
    @Column(name = "role_name")
    private String roleName;

    @OneToMany
    private Set<Permissions> permissions;
}
