package com.safaricom.hackathon.ossautomation.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy","dateCreated", "active", "updatedBy","dateUpdated"},
        allowGetters = false, allowSetters = true
)
public abstract class AuditModel implements Serializable {
    @NotNull
    @Size(max = 255)
    @Column(name = "CREATEDBY")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED", nullable = false, updatable = false)
    @CreatedDate
    private Date dateCreated;

    @Null
    @Size(max = 255)
    @Column(name = "UPDATEDBY")
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_UPDATED", nullable = false, updatable = false)
    @CreatedDate
    private Date dateUpdated;

    @NotNull
    @Column(name = "ACTIVE")
    private int active;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
