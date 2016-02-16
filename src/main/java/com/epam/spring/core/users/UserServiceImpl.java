package com.epam.spring.core.users;

import com.google.common.collect.Lists;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

//    @Autowired
//    private UserDAO userDAO;

    @Autowired
    private UserRepository userRepository;

//    EntityManager entityManager;

    @Override
    public User register(String name, String email, LocalDate birthday) {
        return null;

//        // check user's name
//        if (name == null || name.isEmpty()) {
//            throw new IllegalArgumentException("User name cannot be empty or 'null'");
//        }
//
//        // check user's email
//        if (email == null || email.isEmpty()) {
//            throw new IllegalArgumentException("User email cannot be empty or 'null'");
//        }
//
//        User forCheck = userDAO.findByEmail(email);
//        if (forCheck != null) {
//            throw new Exception("User email '" + email + "' is duplicated");
//        }
//        forCheck = userDAO.findByName(name);
//        if (forCheck != null) {
//            throw new Exception("User name '" + name + "' is duplicated");
//        }
//
//
//        // check user birthday
//        LocalDate currentDate = LocalDate.now();
//        if (birthday == null) {
//            throw new IllegalArgumentException("User birthday cannot be 'null'");
//        }
//        if (birthday.isAfter(currentDate) || birthday.isEqual(currentDate)) {
//            throw new IllegalArgumentException("User birthday cannot be in future or today");
//        }
//
//        User user = new User(name, email, birthday);
//        user = userDAO.create(user);
//
//        return user;
    }

    @Override
    public void remove(User user) {
//        userDAO.remove(user);
    }

    @Override
    public User getById(Long id) {
        userRepository.findOne(id);
        return null;
//        return userDAO.findById(id);
    }

    @Override
    public User getByEmail(String email) {
        return null;
//        return userDAO.findByEmail(email);
    }

    @Override
    public User getByName(String name) {
        return null;
//        return userDAO.findByName(name);
    }

    @Override
    public List<UserEntity> getAll() {
//        Lists.newArrayList(userRepository.findAll());
        return Lists.newArrayList(userRepository.findAll());
//        return userDAO.findAll();
    }
}
