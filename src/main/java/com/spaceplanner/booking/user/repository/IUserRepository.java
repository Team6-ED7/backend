package com.spaceplanner.booking.user.repository;

import com.spaceplanner.booking.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);



}
