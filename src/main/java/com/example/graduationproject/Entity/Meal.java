package com.example.graduationproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Table(name = "MEAL")
@Getter
@Setter
@NoArgsConstructor
public class Meal extends BaseAbstractEntityName {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Min(value = 1)
    @NotNull
    @Column(name = "PRICE")
    private int price;

    @Column(name = "DATE_INPUT")
    private LocalDate dateInput;

    public Meal(String name, int price) {
        this.name = name;
        this.price = price;
    }
}