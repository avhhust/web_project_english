package com.springpetproject.services.implementation;

import com.springpetproject.models.User;
import com.springpetproject.repositories.UserRepository;
import com.springpetproject.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User readById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public User update(User user) {
        User chkUser = readById(user.getId());
        return userRepository.save(chkUser);
    }

    @Override
    public void delete(long id) {
        User user = readById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }
}
