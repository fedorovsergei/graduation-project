package com.example.graduationproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MEAL")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Meal extends BaseAbstractEntityName {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "DATE_INPUT")
    private LocalDate dateInput;

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", restaurant=" + restaurant +
                ", price=" + price +
                ", dateInput=" + dateInput +
                '}';
    }
}
