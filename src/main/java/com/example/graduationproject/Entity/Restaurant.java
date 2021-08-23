package com.example.graduationproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RESTAURANT")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restaurant extends BaseAbstractEntityName {


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @ToString.Exclude
    private List<Meal> meals;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @ToString.Exclude
    private List<Vote> votes;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Transient
    private Integer voteCount;
}