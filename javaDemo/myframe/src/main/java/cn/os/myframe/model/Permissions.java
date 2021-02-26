package cn.os.myframe.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "permissions")
public class Permissions extends BaseModel {

    @Column(name = "permissions_name")
    private String permissionsName;

    @Column(name = "permissions_Url")
    private String permissionsUrl;
}
