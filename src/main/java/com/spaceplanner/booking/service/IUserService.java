package com.spaceplanner.booking.service;

import com.spaceplanner.booking.entity.User;
import java.util.List;

public interface IUserService {


    public List<User> findAllUsers();

    public User saveUser(User user);

    public User updateUser(User user);

    public User findUserById(Long id);

    public void deleteUser(Long id);








}
