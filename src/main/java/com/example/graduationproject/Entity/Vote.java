package com.example.graduationproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "VOTE")
@Getter
@Setter
@NoArgsConstructor
public class Vote extends BaseAbstractEntityId {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "DATE_INPUT")
    @NotNull
    private LocalDate dateVote;
}