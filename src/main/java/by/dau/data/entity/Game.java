package by.dau.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "DAU_GAME")
public class Game {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "set_id", nullable = false)
    private Set sett;

    @Column(name = "price", nullable = false)
    private float price;

    @JsonCreator
    public Game() {
    }

    public Game(Set sett, float price) {
        this.sett = sett;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set getSett() {
        return sett;
    }

    public void setSett(Set sett) {
        this.sett = sett;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}