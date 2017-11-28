package ua.cardsmanager.util;

import ua.cardsmanager.model.Card;
import ua.cardsmanager.model.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by j on 24.11.2017.
 */
public class HelperUtil {


    public static Map<Category, Integer> getCategoryListWithSize(List<Category> categories)

    {
        Map<Category, Integer> categoryList = new HashMap<>();
        if (categories != null) {

            for (Category category : categories) {
                int size = 0;
                List<Card> cardsList = category.getCardList();
                for (Card item : cardsList) {
                    if (!item.isDone())
                        size++;
                }
                categoryList.put(category, size);
            }
        }
        return categoryList;
    }

}
