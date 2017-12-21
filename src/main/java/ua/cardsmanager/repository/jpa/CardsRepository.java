package ua.cardsmanager.repository.jpa;

import ua.cardsmanager.model.Card;
import ua.cardsmanager.model.Category;
import ua.cardsmanager.model.Status;
import ua.cardsmanager.model.Training;

import java.util.List;

public interface CardsRepository {

    Card save(Card card, int userId);

    Category saveCategory(Category category, int userId);

    boolean delete(int id, int userId);

    boolean deleteCategory(int id, int userId);

    boolean deleteCardsFromCategory(int categoryId, int userId);

    boolean deleteAll(int userId);

    Card get(int id, int userId);

    Card getByName(String name, int userId);

    Category getCategory(int id, int userId);

    Category getByCategoryName(String name, int userId);

    Category getCategoryById(Integer id);

    List<Card> getAll(int userId);

    List<Card> getAllByStatus(int userId, boolean status);

    List<Card> getAllFromCategoryByStatus(int categoryId, int userId, boolean status);

    List<Card> getAllFromCategory(int categoryId, int userId);

    List<Category> getAllCategories(int userId);

    List<Category> getAllCategoriesWithoutCards(int userId);

    List<Training> getAllTrainings();

    List<Status> getStatusForTraining(int userId, List<Integer> array, String trainingName);

    List<Status> getAllStatus(int userId, String trainingName);

    Status updateStatus(int cardId, String trainingName, boolean done);

    Status changeStatus(Status status);

    void saveStatus(Card card);

    Status getStatusById(int id);


}
