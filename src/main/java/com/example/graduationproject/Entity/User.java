package com.example.graduationproject.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseAbstractEntityId {

    @Column(name = "USERNAME")
    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
    private List<Vote> votes;
}