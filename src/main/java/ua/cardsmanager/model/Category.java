package ua.cardsmanager.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by j on 06.11.2017.
 */

@NamedQueries({
        @NamedQuery(name = Category.ALL_SORTED, query = "SELECT c FROM Category c LEFT JOIN FETCH c.cardList where c.user.id=:userId order by c.id ASC"),
        @NamedQuery(name = Category.ALL_WITHOUT_CARDS, query = "SELECT c FROM Category c where c.user.id=:userId order by c.id ASC"),
        /*@NamedQuery(name = Category.ALL_BY_CARDSTATUS, query = "SELECT c FROM Category c left JOIN FETCH Card card WHERE c.user.id=:userId and card.done=:status ORDER BY c.date DESC"),*/
        @NamedQuery(name = Category.DELETE, query = "DELETE FROM Category c WHERE c.id=:id AND c.user.id=:userId"),
        /*@NamedQuery(name = Category.BY_NAME, query = "SELECT c FROM Category c WHERE c.id=:id"),*/
})

@Entity
@Table(name = "categories")
public class Category extends AbstractNamedEntity {

    public static final String ALL_SORTED = "Category.getAll";
    public static final String DELETE = "Category.delete";
    public static final String ALL_WITHOUT_CARDS = "category.getAllWithoutCards";
    /*public static final String ALL_BY_CARDSTATUS = "Category.getByCardStatus";*/
    /*public static final String BY_NAME = "Category.getByName";*/

    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    @NotNull
    private Date date = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Card> cardList = new ArrayList<>();

    public Category(Integer id, String name, List<Card> cardList) {
        super(id, name);
        this.cardList = cardList;
    }

    public Category() {
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return name;
    }

}
