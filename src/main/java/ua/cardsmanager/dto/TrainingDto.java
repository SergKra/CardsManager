package ua.cardsmanager.dto;

import ua.cardsmanager.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j on 17.11.2017.
 */
public class TrainingDto {

    private List<String> categories = new ArrayList<>();

    private String training;

    private List<Card> cards;


    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "TrainingDto{" +
                "categories=" + categories +
                ", training='" + training + '\'' +
                '}';
    }
}
