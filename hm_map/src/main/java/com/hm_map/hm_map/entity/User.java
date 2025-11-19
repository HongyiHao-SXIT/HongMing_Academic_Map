package com.hm_map.hm_map.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

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

  private String tags;

  private String role;
  private boolean isDeleted;

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(boolean deleted) {
    isDeleted = deleted;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
