package com.example.graduationproject.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "USERS")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends BaseAbstractEntityId {

    @Column(name = "USERNAME")
    protected String name;

    @Column(name = "RESTAURANT_VOTE")
    private Integer restaurantVote;

    @Column(name = "RESTAURANT_DATE_VOTE")
    private LocalDate restaurantVoteDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @ToString.Exclude
    private List<Vote> votes;
}
