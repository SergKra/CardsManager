package ua.cardsmanager.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by j on 21.11.2017.
 */

@Entity
@Table(name = "trainings")
public class Training extends AbstractNamedEntity {


    @Override
    public String toString() {
        return name;
    }
}
