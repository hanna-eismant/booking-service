package com.epam.spring.core.users;

import com.epam.spring.core.shared.DuplicateException;
import com.epam.spring.core.shared.Mapper;
import com.epam.spring.core.shared.NotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private MapperFacade mapper = Mapper.getMapper();

    @Override
    public User register(String name, String email, String password, LocalDate birthday)
            throws IllegalArgumentException, DuplicateException {

        // check user's name
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty or 'null'");
        }
        if (userRepository.existsByName(name)) {
            throw new DuplicateException("User with name '" + name + "' already exist");
        }

        // check user's email
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("User email cannot be empty or 'null'");
        }
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateException("User with email '" + email + "' already exist");
        }

        // check pass
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("User password cannot be empty or 'null'");
        }
        String encodedPassword = passwordEncoder.encode(password);

        // check user birthday
        LocalDate currentDate = LocalDate.now();
        if (birthday == null) {
            throw new IllegalArgumentException("User birthday cannot be 'null'");
        }
        if (birthday.isAfter(currentDate) || birthday.isEqual(currentDate)) {
            throw new IllegalArgumentException("User birthday cannot be in future or today");
        }

        UserEntity userEntity = new UserEntity(name, email, encodedPassword, birthday);
        userEntity.getRoles().add(UserRoles.ROLE_REGISTERED_USER);
        UserEntity save = userRepository.save(userEntity);

        return mapper.map(save, User.class);
    }

    @Override
    public User getById(Long id) throws IllegalArgumentException, NotFoundException {
        // check id
        if (id == null) {
            throw new IllegalArgumentException("Id for search cannot be 'null'");
        }

        UserEntity userEntity = userRepository.findOne(id);

        if (userEntity == null) {
            throw new NotFoundException("User with id '" + id + "' doesn't exist");
        }

        return mapper.map(userEntity, User.class);
    }

    @Override
    public User getByEmail(String email) throws IllegalArgumentException, NotFoundException {
        // check email
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email for search cannot be empty or 'null'");
        }

        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new NotFoundException("User with email '" + email + "' doesn't exist");
        }

        return mapper.map(userEntity, User.class);
    }

    @Override
    public User getByName(String name) throws IllegalArgumentException, NotFoundException {
        // check name
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name for search cannot be empty or 'null'");
        }

        UserEntity userEntity = userRepository.findByName(name);

        if (userEntity == null) {
            throw new NotFoundException("User with name '" + name + "' doesn't exist");
        }

        return mapper.map(userEntity, User.class);
    }

    @Override
    public List<User> getAll() {
        Iterable<UserEntity> allEntities = userRepository.findAll();
        return mapper.mapAsList(allEntities, User.class);
    }
}
