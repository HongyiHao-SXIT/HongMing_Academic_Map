package com.hm_map.hm_map.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String account;

    private String password;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String intro;

    private String organization;

    private String department;

    private String address;

    private String postCode;

    private String field;
}
