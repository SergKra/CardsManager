package ua.cardsmanager.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "trainings")
public class Training extends AbstractNamedEntity {


    @Override
    public String toString() {
        return name;
    }
}
