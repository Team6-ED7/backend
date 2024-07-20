package com.spaceplanner.booking.repository;

import com.spaceplanner.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
