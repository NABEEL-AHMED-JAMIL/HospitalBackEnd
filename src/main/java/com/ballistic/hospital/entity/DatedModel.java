package com.ballistic.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.sql.Timestamp;


@MappedSuperclass
public class DatedModel implements Serializable{

    @Column( name = "created_at" )
    private Timestamp createdAt;

    @Column( name = "updated_at" )
    private Timestamp updatedAt;

    public DatedModel() {super();}

    public DatedModel(Timestamp createdAt, Timestamp updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonIgnore
    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @JsonIgnore
    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    @PrePersist
    @PreUpdate
    public void preSave() { preSaveImpl(); }

    protected void preSaveImpl() {
        Timestamp now = new Timestamp(new DateTime().getMillis());
        if ( getCreatedAt() == null ) {
            setCreatedAt( now );
        }
        setUpdatedAt( now );
    }

    @Override
    public String toString() {
        return "DatedModel{" +
                "createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
