package ua.cardsmanager.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@NamedQueries({
        @NamedQuery(name = Card.ALL_SORTED, query = "SELECT c FROM Card c WHERE c.user.id=:userId ORDER BY c.id DESC"),
        @NamedQuery(name = Card.STATUS_SORTED, query = "SELECT c FROM Card c WHERE c.user.id=:userId AND c.done=:status ORDER BY c.id DESC"),
        @NamedQuery(name = Card.ALL_SORTED_FROM_CATEGORY, query = "SELECT c FROM Card c WHERE c.user.id=:userId AND c.category.id=:categoryId ORDER BY c.id DESC"),
        @NamedQuery(name = Card.ALL_STATUS_SORTED_FROM_CATEGORY, query = "SELECT c FROM Card c WHERE c.user.id=:userId AND c.category.id=:categoryId AND c.done=:status ORDER BY c.id DESC"),
        @NamedQuery(name = Card.BY_NAME, query = "SELECT c FROM Card c WHERE c.engName=:name AND c.user.id=:userId"),
        @NamedQuery(name = Card.DELETE, query = "DELETE FROM Card c WHERE c.id=:id AND c.user.id=:userId"),
        @NamedQuery(name = Card.DELETE_ALL, query = "DELETE FROM Card c WHERE c.user.id=:userId"),
        @NamedQuery(name = Card.DELETE_FROM_CATEGORY, query = "DELETE FROM Card c WHERE c.category.id=:categoryId AND c.user.id=:userId"),
})
@Entity
@Table(name = "cards")
public class Card extends AbstractBaseEntity {

    public static final String ALL_SORTED = "Card.getAll";
    public static final String DELETE = "Card.delete";
    public static final String DELETE_ALL = "Card.deleteAll";
    public static final String DELETE_FROM_CATEGORY = "Card.deleteFromCategory";
    public static final String BY_NAME = "Card.getByName";
    public static final String ALL_SORTED_FROM_CATEGORY = "Card.getAllFromCategory";
    public static final String STATUS_SORTED = "Card.getALLByStatus";
    public static final String ALL_STATUS_SORTED_FROM_CATEGORY = "Cards.getAllFromCategoryWithStatus";

    @NotBlank
    @Column(name = "engname", nullable = false)
    private String engName;

    @NotBlank
    @Column(name = "runame", nullable = false)
    private String rusName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cat_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Category category;

    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    private Date date = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "done", nullable = false, columnDefinition = "bool default false")
    private boolean done = false;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "card")
    private List<Status> statusList;

    public Card(Integer id, String engName, String rusName, Category category, Date date) {
        super(id);
        this.engName = engName;
        this.rusName = rusName;
        this.category = category;
        this.date = date;
    }

    public Card() {
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String ruName) {
        this.rusName = ruName;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    @Override
    public String toString() {
        return "Card{" +
                "engName='" + engName + '\'' +
                ", rusName='" + rusName + '\'' +
                ", category=" + category +
                ", date=" + date +
                ", done=" + done +
                '}';
    }
}

