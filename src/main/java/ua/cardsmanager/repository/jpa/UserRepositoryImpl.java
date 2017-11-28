package ua.cardsmanager.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ua.cardsmanager.model.Role;
import ua.cardsmanager.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by j on 06.11.2017.
 */

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setRoles(EnumSet.of(Role.USER));
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public boolean delete(int id) {

      /*User ref = em.getReference(User.class, id);
        em.remove(ref);*/

        /*Query query = em.createQuery("DELETE FROM User u WHERE u.id=:id");
        query.setParameter("id", id).executeUpdate();*/

        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getBySessionId(String sessionId, String email) {
        List<User> users = em.createNamedQuery(User.BY_SESSION, User.class)
                .setParameter(1, sessionId)
                .setParameter(2, email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public User getWithCards(int id) {
        List<User> users = em.createNamedQuery(User.WITH_CARDS, User.class).setParameter(1, id).getResultList();
        return DataAccessUtils.singleResult(users);
    }

}
