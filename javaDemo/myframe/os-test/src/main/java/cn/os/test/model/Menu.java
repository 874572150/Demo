package cn.os.test.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "menu")
public class Menu extends BaseModel {

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "component_path")
    private String componentPath;

//    @Transient
//    private List<Menu> children;
}
