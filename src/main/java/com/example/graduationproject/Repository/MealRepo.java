package com.example.graduationproject.Repository;

import com.example.graduationproject.Entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface MealRepo extends JpaRepository<Meal, Integer> {
}
