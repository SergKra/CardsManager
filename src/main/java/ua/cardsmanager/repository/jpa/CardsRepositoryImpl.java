package ua.cardsmanager.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ua.cardsmanager.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CardsRepositoryImpl implements CardsRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Card save(Card card, int userId) {

        if (!card.isNew() && get(card.getId(), userId) == null) {
            return null;
        }
        card.setUser(em.getReference(User.class, userId));
        if (card.isNew()) {
            em.persist(card);
            return card;
        } else {
            return em.merge(card);
        }
    }

    @Override
    public Category saveCategory(Category category, int userId) {
        if (!category.isNew() && getCategory(category.getId(), userId) == null) {
            return null;
        }
        category.setUser(em.getReference(User.class, userId));
        if (category.isNew()) {
            em.persist(category);
            return category;
        } else {
            return em.merge(category);
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Card.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public boolean deleteCategory(int id, int userId) {
        return em.createNamedQuery(Category.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public boolean deleteCardsFromCategory(int categoryId, int userId) {
        return em.createNamedQuery(Card.DELETE_FROM_CATEGORY)
                .setParameter("categoryId", categoryId)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public boolean deleteAll(int userId) {
        return em.createNamedQuery(Card.DELETE_ALL)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Card get(int id, int userId) {
        Card card = em.find(Card.class, id);
        return card != null && card.getUser().getId() == userId ? card : null;
    }

    @Override
    public Card getByName(String name, int userId) {
        List<Card> cards = em.createNamedQuery(Card.BY_NAME, Card.class)
                .setParameter("name", name)
                .setParameter("userId", userId)
                .getResultList();
        return DataAccessUtils.singleResult(cards);
    }

    @Override
    public Category getCategory(int id, int userId) {
        Category category = em.find(Category.class, id);
        return category != null ? category : null;
    }

    @Override
    public Category getByCategoryName(String name, int userId) {
        List<Category> cards = em.createNamedQuery(Category.BY_NAME, Category.class)
                .setParameter("name", name)
                .setParameter("userId", userId)
                .getResultList();
        return DataAccessUtils.singleResult(cards);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return em.find(Category.class, id);
    }


    @Override
    public List<Card> getAll(int userId) {
        return em.createNamedQuery(Card.ALL_SORTED, Card.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Card> getAllByStatus(int userId, boolean status) {
        return em.createNamedQuery(Card.STATUS_SORTED, Card.class)
                .setParameter("userId", userId)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<Card> getAllFromCategoryByStatus(int categoryId, int userId, boolean status) {
        return em.createNamedQuery(Card.ALL_STATUS_SORTED_FROM_CATEGORY, Card.class)
                .setParameter("userId", userId)
                .setParameter("categoryId", categoryId)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<Card> getAllFromCategory(int categoryId, int userId) {
        return em.createNamedQuery(Card.ALL_SORTED_FROM_CATEGORY, Card.class)
                .setParameter("userId", userId)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public List<Category> getAllCategories(int userId) {
        return em.createNamedQuery(Category.ALL_SORTED, Category.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Category> getAllCategoriesWithoutCards(int userId) {
        return em.createNamedQuery(Category.ALL_WITHOUT_CARDS, Category.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    /*@Override
    public List<Category> getAllCategoriesByCardStatus(int userId, boolean status)
    {
        return em.createNamedQuery(Category.ALL_BY_CARDSTATUS, Category.class)
                .setParameter("userId", userId)
                .setParameter("status", status)
                .getResultList();
    }*/


    @Override
    public List<Status> getStatusForTraining(int userId, List<Integer> array, String trainingName) {
        return em.createQuery("SELECT s from Status s where s.card.user.id=:userId AND s.training.name=:trainingName AND s.done=:done AND s.card.category.id IN :array", Status.class)
                .setParameter("userId", userId)
                .setParameter("trainingName", trainingName)
                .setParameter("done", false)
                .setParameter("array", array)
                .getResultList();
    }

    @Override
    public List<Status> getAllStatus(int userId, String trainingName) {
        return em.createQuery("SELECT s from Status s where s.card.user.id=:userId AND s.training.name=:trainingName AND s.done=:done", Status.class)
                .setParameter("userId", userId)
                .setParameter("trainingName", trainingName)
                .setParameter("done", false)
                .getResultList();
    }


    @Override
    public Status updateStatus(int cardId, String trainingName) {
        Status status = DataAccessUtils.singleResult(em.createQuery("select s from Status s where s.card.id=:cardId AND s.training.name=:trainingName", Status.class)
                .setParameter("cardId", cardId)
                .setParameter("trainingName", trainingName)
                .getResultList());
        status.setDone(true);

        return em.merge(status);
    }

    @Override
    public Status changeStatus(Status status) {
        return em.merge(status);
    }

    @Override
    public List<Training> getAllTrainings() {
        return em.createQuery("SELECT t from Training t", Training.class).getResultList();
    }

    @Override
    public void saveStatus(Card card) {
        for (Training training : getAllTrainings()) {
            em.persist(new Status(training, card));
        }

    }


}
