package ua.cardsmanager.service;

import ua.cardsmanager.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id); //throws NotFoundException;

    User get(int id);// throws NotFoundException;

    User getByEmail(String email);// throws NotFoundException;

    void update(User user);

    List<User> getAll();

    boolean checkEmailExists(String email);
}
