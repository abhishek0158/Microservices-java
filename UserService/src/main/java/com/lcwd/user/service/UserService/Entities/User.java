package com.lcwd.user.service.UserService.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    @Transient//@Transient allows not store in database
    private List<Rating> ratings=new ArrayList<>();

}
