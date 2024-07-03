package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> userMap = new HashMap<>();
    private Long currId = 0L;
    @Override
    public User createUser(User user) {
        currId++;
        user.setId(currId);
        userMap.put(currId, user);

        return user;
    }

    @Override
    public User updateUser(User user, Long id) {
        userMap.put(id, user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User getUserById(Long id) {
        return userMap.get(id);
    }

    @Override
    public User deleteUser(Long userId) {
        return userMap.remove(userId);
    }

}
