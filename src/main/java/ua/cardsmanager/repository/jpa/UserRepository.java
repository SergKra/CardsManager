package ua.cardsmanager.repository.jpa;

import ua.cardsmanager.model.User;

import java.util.List;

/**
 * Created by j on 06.11.2017.
 */
public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    User getBySessionId(String sessionId, String email);

    List<User> getAll();

    User getWithCards(int id);
}
