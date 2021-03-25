package com.grocerybasket.release.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    protected Integer id;

    @Column(name = "creation_date")
    @CreationTimestamp
    protected Timestamp creationDate;

    @Version
    @Column(name = "last_updated")
    protected Timestamp lastUpdated;

    public Timestamp getCreationDate() {
        if (creationDate != null) {
            return new Timestamp(creationDate.getTime());
        }
        return null;
    }

    public Timestamp getLastUpdated() {
        if (lastUpdated != null) {
            return new Timestamp(lastUpdated.getTime());
        }
        return null;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate != null ? new Timestamp(creationDate.getTime()) : null;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated != null ? new Timestamp(lastUpdated.getTime()) : null;
    }
}
