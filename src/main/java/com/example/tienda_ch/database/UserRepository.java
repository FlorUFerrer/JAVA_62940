package com.example.tienda_ch.database;

import com.example.tienda_ch.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
