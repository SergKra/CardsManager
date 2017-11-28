package ua.cardsmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cardsmanager.model.User;
import ua.cardsmanager.repository.jpa.UserRepository;

import java.util.List;

/**
 * Created by j on 06.11.2017.
 */

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        if (checkEmailExists(user.getEmail()))
            return null;
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public User getBySessionId(String sessionId, String email) {
        return userRepository.getBySessionId(sessionId, email);
    }

    @Override
    @Transactional
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getWithCards(int id) {
        return userRepository.getWithCards(id);
    }

    @Override
    public boolean checkEmailExists(String email) {
        List<User> users = userRepository.getAll();
        for (User userExisted : users) {
            if (email.equals(userExisted.getEmail()))
                return true;
        }
        return false;
    }
}
