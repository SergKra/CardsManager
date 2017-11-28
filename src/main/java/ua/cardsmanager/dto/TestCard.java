package ua.cardsmanager.dto;

import ua.cardsmanager.model.Category;

/**
 * Created by j on 24.11.2017.
 */
public class TestCard {

    private Category category;

    private Integer size;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public TestCard(Category category, Integer size) {
        this.category = category;
        this.size = size;
    }

    public TestCard() {
    }
}
