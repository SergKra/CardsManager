package ua.cardsmanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ua.cardsmanager.model.Status;
import ua.cardsmanager.repository.jpa.CardsRepository;

public class StatusConverter implements Converter<String, Status> {

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public Status convert(String source) {
        if (source == null || source.isEmpty())
            /*throw new IllegalArgumentException()*/
            return null;

        /*if (category==null)
            throw new NotFoundException("not found");*/

        return cardsRepository.getStatusById(Integer.parseInt(source));
    }

}
