package com.example.login_spring.model;



import javax.persistence.*;
import java.util.Objects;


@Table(name = "quote")
@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER) //kak sdes
    @JoinColumn(name = "author")
    private User author;

    public Quote(String text, User author) {
        this.text = text;
        this.author = author;
    }

    public Quote() {

    }

    public Long getId() {
        return id;
    }

    public Quote setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Quote setText(String text) {
        this.text = text;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Quote setAuthor(User author) {
        this.author = author;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(getId(), quote.getId()) &&
                Objects.equals(getText(), quote.getText()) &&
                Objects.equals(getAuthor(), quote.getAuthor());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getText(), getAuthor());
    }
}
