package ftn.isamrs.tim5.model;



import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    private String text;
    @Column
    private int score;
    @Column
    private Date date;

    @ManyToOne()
    private Cineter cineter;

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Cineter getCineter() {
        return cineter;
    }

    public void setCineter(Cineter cineter) {
        this.cineter = cineter;
    }
}
