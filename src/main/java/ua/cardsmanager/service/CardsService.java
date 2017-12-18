package ua.cardsmanager.service;

import ua.cardsmanager.dto.TrainingDto;
import ua.cardsmanager.model.Card;
import ua.cardsmanager.model.Category;
import ua.cardsmanager.model.Status;

import java.util.List;
import java.util.Map;

public interface CardsService {

    Card create(Card card, int userId);

    Category create(Category category, int userId);

    void delete(int id, int userId);

    void deleteCategory(int id, int userId);

    void deleteCardsFromCategory(int categoryId, int userId);

    void deleteAll(int userId);

    Card update(Card card, int userId);

    Category updateCategory(Category category, int userId);

    Card get(int id, int userId);

    Card getByName(String name, int userId);

    Category getCategory(int id, int userId);

    Category getByCategoryName(String name, int userId);

    List<Card> getAll(int userId);

    List<Card> getAllByStatus(int userId, boolean status);

    List<Card> getAllFromCategoryByStatus(int categoryId, int userId, boolean status);

    List<Card> getAllFromCategory(int categoryId, int userId);

    List<Category> getAllCategories(int userId);

    List<Category> getAllCategoriesWithoutCards(int userId);

    List<Status> getStatusForTraining(int userId, List<Integer> array, String trainingName);

    List<Status> getAllStatus(int userId, String trainingName);

    Status updateStatus(Card card, String trainingName, int userId);

    void checkCardStatus(Status statusUpdated, int userId);

    void changeStatus(Card card);

    List<Card> getSortedCardList(Integer userId, TrainingDto trainingDto);

    Map<Category, Integer> getCategorySortedList(int userId, String trainingName);


    /*List<TestCard> testtest(int userId);*/

    /*List<Category> getAllCategoriesByCardStatus(int userId, boolean status);*/
}
