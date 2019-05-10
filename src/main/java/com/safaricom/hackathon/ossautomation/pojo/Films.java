package com.safaricom.hackathon.ossautomation.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "films", schema = "oss_automation")
public class Films extends AuditModel implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILMID")
    private Long filmID;

    @NotNull
    @Column(name = "TYPEID", insertable = false, updatable = false)
    private Long typeID;

    @NotNull
    @Size(max = 255)
    @Column(name = "FILM_NAME")
    private String  filmName;

    @ManyToOne
    @JoinColumn(name = "TYPEID", referencedColumnName = "TYPEID")
    private VideoType videoType;


    @Null
    @Size(max = 1024)
    private String description;

    public Films() {
    }

    public Films(@NotNull Long typeID, @NotNull @Size(max = 255) String filmName,
                 VideoType videoType, @Null @Size(max = 1024) String description) {
        this.typeID = typeID;
        this.filmName = filmName;
        this.videoType = videoType;
        this.description = description;
    }

    public Long getFilmID() {
        return filmID;
    }

    public void setFilmID(Long filmID) {
        this.filmID = filmID;
    }

    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Films)) return false;
        Films films = (Films) o;
        return getFilmID().equals(films.getFilmID()) &&
                getTypeID().equals(films.getTypeID()) &&
                getFilmName().equals(films.getFilmName()) &&
                getVideoType().equals(films.getVideoType()) &&
                getDescription().equals(films.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFilmID(), getTypeID(), getFilmName(), getVideoType(), getDescription());
    }
}
