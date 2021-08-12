package com.example.graduationproject.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
public class User extends BaseAbstractEntityId {

    @Column(name = "USERNAME")
    protected String name;

    @Column(name = "RESTAURANT_VOTE")
    private Integer restaurantVote;

    @Column(name = "RESTAURANT_DATE_VOTE")
    private LocalDate restaurantVoteDate;
}
