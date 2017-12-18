package ua.cardsmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cardsmanager.dto.TrainingDto;
import ua.cardsmanager.model.Card;
import ua.cardsmanager.model.Category;
import ua.cardsmanager.model.Status;
import ua.cardsmanager.repository.jpa.CardsRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CardsServiceImpl implements CardsService {

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    @Transactional
    public Card create(Card card, int userId) {
        Card cardNew = cardsRepository.save(card, userId);
        if (cardNew != null)
            cardsRepository.saveStatus(card);
        return cardNew;
    }

    @Override
    @Transactional
    public Category create(Category category, int userId) {
        return cardsRepository.saveCategory(category, userId);
    }

    @Override
    @Transactional
    public void delete(int id, int userId) {
        cardsRepository.delete(id, userId);
    }

    @Override
    @Transactional
    public void deleteCategory(int id, int userId) {
        cardsRepository.deleteCategory(id, userId);
    }

    @Override
    @Transactional
    public void deleteCardsFromCategory(int categoryId, int userId) {
        cardsRepository.deleteCardsFromCategory(categoryId, userId);
    }

    @Override
    @Transactional
    public void deleteAll(int userId) {

        cardsRepository.deleteAll(userId);
    }

    @Override
    @Transactional
    public Card update(Card card, int userId) {
        return cardsRepository.save(card, userId);
    }

    @Override
    @Transactional
    public Category updateCategory(Category category, int userId) {
        return cardsRepository.saveCategory(category, userId);
    }

    @Override
    public Card get(int id, int userId) {
        return cardsRepository.get(id, userId);
    }

    @Override
    public Card getByName(String name, int userId) {
        return cardsRepository.getByName(name, userId);
    }

    @Override
    public Category getCategory(int id, int userId) {
        return cardsRepository.getCategory(id, userId);
    }

    @Override
    @Transactional
    public Category getByCategoryName(String name, int userId) {
        Category categoryGen = cardsRepository.getByCategoryName(name,userId);
        if (categoryGen==null)
        {
            categoryGen = new Category();
            categoryGen.setName("general");
            return create(categoryGen,userId);
        }
        return categoryGen;
    }

    @Override
    public List<Card> getAll(int userId) {
        return cardsRepository.getAll(userId);
    }

    @Override
    public List<Card> getAllByStatus(int userId, boolean status) {
        return cardsRepository.getAllByStatus(userId, status);
    }

    @Override
    public List<Card> getAllFromCategoryByStatus(int categoryId, int userId, boolean status) {
        return cardsRepository.getAllFromCategoryByStatus(categoryId, userId, status);
    }

    @Override
    public List<Card> getAllFromCategory(int categoryId, int userId) {
        return cardsRepository.getAllFromCategory(categoryId, userId);

    }

    @Override
    public List<Category> getAllCategories(int userId) {
        return cardsRepository.getAllCategories(userId);
    }


    @Override
    public List<Category> getAllCategoriesWithoutCards(int userId) {
        return cardsRepository.getAllCategoriesWithoutCards(userId);
    }

    @Override
    public List<Status> getStatusForTraining(int userId, List<Integer> array, String trainingName) {
        return cardsRepository.getStatusForTraining(userId, array, trainingName);
    }

    @Override
    public List<Status> getAllStatus(int userId, String trainingName) {
        return cardsRepository.getAllStatus(userId, trainingName);
    }

    @Override
    @Transactional
    public Status updateStatus(Card card, String trainingName, int userId) {

        Status status = cardsRepository.updateStatus(card.getId(), trainingName);
        if (status != null) checkCardStatus(status, userId);
        return status;
    }

    @Override
    public void checkCardStatus(Status statusUpdated, int userId) {

        Card card = statusUpdated.getCard();
        if (card != null) {
            int count = card.getStatusList().size();

            for (Status status : card.getStatusList()) {
                if (status.isDone())
                    count--;
            }
            if (count == 0) {
                card.setDone(true);
                cardsRepository.save(card, userId);
            }
        }
    }

    @Override
    public List<Card> getSortedCardList(Integer userId, TrainingDto trainingDto) {
        List<Integer> categoryIds = trainingDto.getCategories().stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Card> cardsList = new ArrayList<>();
        List<Status> statusList = getStatusForTraining(userId, categoryIds, trainingDto.getTraining());

        cardsList.addAll(statusList.stream().map(Status::getCard).collect(Collectors.toList()));

        return cardsList;

    }

    @Override
    public Map<Category, Integer> getCategorySortedList(int userId, String trainingName) {
        List<Status> statusList = getAllStatus(userId, trainingName);
        Map<Category, Integer> categoryMap = new HashMap<>();

        for (Status status : statusList) {
            Category category = status.getCard().getCategory();
            categoryMap.put(category, categoryMap.get(category) == null ? 1 : categoryMap.get(category) + 1);
        }

        return categoryMap;
    }

    @Override
    @Transactional
    public void changeStatus(Card card) {
        boolean done = card.isDone();

        card.getStatusList().stream().filter(status -> status.isDone() != done).forEach(status -> {
            status.setDone(done);
            cardsRepository.changeStatus(status);
        });
    }
}
