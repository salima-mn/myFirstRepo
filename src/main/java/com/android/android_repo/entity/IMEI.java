package com.android.android_repo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class IMEI implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer imei_id;
    private String value;
    private String password;
    private String name;
    private String surname;
    private String expired_token;

    public IMEI(String value, String password, String name, String surname, String expired_token) {
        this.value = value;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.expired_token = expired_token;
    }
}
