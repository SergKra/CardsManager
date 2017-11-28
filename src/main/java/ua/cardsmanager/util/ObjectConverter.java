package ua.cardsmanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ua.cardsmanager.model.Category;
import ua.cardsmanager.repository.jpa.CardsRepository;


/**
 * Created by j on 14.11.2017.
 */

public class ObjectConverter implements Converter<String, Category> {

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public Category convert(String source) {
        if (source == null || source.isEmpty())
            /*throw new IllegalArgumentException()*/
            return null;

        /*if (category==null)
            throw new NotFoundException("not found");*/

        return cardsRepository.getCategoryById(Integer.parseInt(source));
    }
}
