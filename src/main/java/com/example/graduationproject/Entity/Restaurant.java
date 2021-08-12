package com.example.graduationproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "RESTAURANT")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restaurant extends BaseAbstractEntityName {


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @ToString.Exclude
    private List<Meal> meals;

    @Column(name = "VOTE")
    private volatile Integer votes;

    public synchronized void incrementVotes() {
        votes++;
    }

    public synchronized void decrementVotes() {
        votes--;
    }

    public synchronized void setNullVotes() {
        votes = 0;
    }
}

