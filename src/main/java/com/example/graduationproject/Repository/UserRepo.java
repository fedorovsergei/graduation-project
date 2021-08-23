package com.example.graduationproject.Repository;

import com.example.graduationproject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByName(String name);
}