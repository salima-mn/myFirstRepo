package com.android.android_repo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int smtp_port;
    private String smtp_host;
    private String smtp_username;
    private String smtp_password;


    public Email(int i, String s, String s1, String s2) {
        this.smtp_port= i;
        this.smtp_host= s;
        this.smtp_username= s1;
        this.smtp_password= s2;
    }
}
