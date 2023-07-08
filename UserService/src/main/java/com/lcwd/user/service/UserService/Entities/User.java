package com.lcwd.user.service.UserService.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name="id")
    private String  userId;
    private String name;
    private String email;
    private String about;



}
