package com.safaricom.hackathon.ossautomation.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "video_type", schema = "oss_automation")
public class VideoType extends AuditModel implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TYPEID")
    private Long typeID;

    @NotNull
    @Size(max = 255)
    @Column(name = "TYPENAME")
    private String typeName;


    public VideoType() {
    }

    public VideoType(@NotNull @Size(max = 255) String typeName) {
        this.typeName = typeName;
    }

    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoType)) return false;
        VideoType videoType = (VideoType) o;
        return getTypeID() == videoType.getTypeID() &&
                getTypeName().equals(videoType.getTypeName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTypeID(), getTypeName());
    }
}
