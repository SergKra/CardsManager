package ua.cardsmanager.service;

import ua.cardsmanager.model.User;

import java.util.List;

/**
 * Created by j on 06.11.2017.
 */
public interface UserService {

    User create(User user);

    void delete(int id); //throws NotFoundException;

    User get(int id);// throws NotFoundException;

    User getByEmail(String email);// throws NotFoundException;

    User getBySessionId(String sessionId, String email);

    void update(User user);

    List<User> getAll();

    User getWithCards(int id);

    boolean checkEmailExists(String email);
}
