package com.example.devsphere_backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT r FROM User r WHERE r.email = ?1")
    Optional<User> findUserByEmail(String email);

    // Find a user by their username
    @Query("SELECT r FROM User r WHERE r.name = :name")
    Optional<User> findByName(@Param("name") String name);
}
