package com.safaricom.hackathon.ossautomation.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "reviews", schema = "oss_automation")
public class Reviews extends AuditModel implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEWID")
    private Long reviewID;

    @NotNull
    @Size(max = 255)
    @Column(name = "REVIEWER", insertable = false, updatable = false)
    private String reviewer;

    @NotNull
    @Column(name = "FILMID", insertable = false, updatable = false)
    private Long filmID;

    @Null
    @Column(name = "WATCHED")
    private int watched;

    @NotNull
    @Size(max = 255)
    @Column(name = "RATING")
    private String rating;

    @Null
    @Column(name = "REVIEW")
    private String review;

    @ManyToOne
    @JoinColumn(name = "REVIEWER", referencedColumnName = "USER_CODE")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "FILMID", referencedColumnName = "FILMID")
    private Films films;

    public Reviews() {
    }

    public Reviews(@NotNull @Size(max = 255) String reviewer, @NotNull Long filmID, @Null int watched,
                   @NotNull @Size(max = 255) String rating, @Null String review, Users users, Films films) {
        this.reviewer = reviewer;
        this.filmID = filmID;
        this.watched = watched;
        this.rating = rating;
        this.review = review;
        this.users = users;
        this.films = films;
    }

    public Long getReviewID() {
        return reviewID;
    }

    public void setReviewID(Long reviewID) {
        this.reviewID = reviewID;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Long getFilmID() {
        return filmID;
    }

    public void setFilmID(Long filmID) {
        this.filmID = filmID;
    }

    public int getWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Films getFilms() {
        return films;
    }

    public void setFilms(Films films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reviews)) return false;
        Reviews reviews = (Reviews) o;
        return getReviewID() == reviews.getReviewID() &&
                getFilmID() == reviews.getFilmID() &&
                getWatched() == reviews.getWatched() &&
                getReviewer().equals(reviews.getReviewer()) &&
                getRating().equals(reviews.getRating()) &&
                getReview().equals(reviews.getReview()) &&
                getUsers().equals(reviews.getUsers()) &&
                getFilms().equals(reviews.getFilms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReviewID(), getReviewer(), getFilmID(), getWatched(),
                getRating(), getReview(), getUsers(), getFilms());
    }
}
