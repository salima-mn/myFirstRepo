package com.android.android_repo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table
public class Role implements Serializable {

    @Id
    private Integer id;
    private String role;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id",insertable=false ,updatable=false)
    private User user;
}
