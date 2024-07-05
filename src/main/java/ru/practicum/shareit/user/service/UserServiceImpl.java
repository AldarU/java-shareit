package ru.practicum.shareit.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.AlreadyException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (isEmailDuplicate(user.getEmail()) != null) {
            throw new AlreadyException("User с таким email уже существует.");
        }

        return userRepository.createUser(user);
    }

    @Override
    public User updateUser(User user, Long id) {
        user.setId(id);

        if (user.getName() == null) {
            user.setName(userRepository.getUserById(id).getName());
        }
        if (user.getEmail() == null) {
            user.setEmail(userRepository.getUserById(id).getEmail());
        }

        if (isEmailDuplicate(user.getEmail()) != null && !user.getId().equals(isEmailDuplicate(user.getEmail()))) {
            throw new AlreadyException("User с таким email уже существует.");
        }

        return userRepository.updateUser(user, id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public User deleteUser(Long userId) {
        return userRepository.deleteUser(userId);
    }

    private Long isEmailDuplicate(String inEmail) {
        List<User> userList = userRepository.getUsers();

        for (User user : userList) {
            String email = user.getEmail();
            if (email.equals(inEmail)) {
                return user.getId();
            }
        }
        return null;
    }
}
