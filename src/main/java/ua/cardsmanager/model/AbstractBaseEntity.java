package ua.cardsmanager.model;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;


@MappedSuperclass


@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity implements Persistable<Integer> {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private Integer id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 0 : getId();
    }
}
