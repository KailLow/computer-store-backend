package com.otters.computerstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Boolean isStopped = false;

//    @Column(name = "createddate")
//    @CreatedDate
//    private Date createdDate;
//
//    @Column(name = "modifieddate")
//    @LastModifiedDate
//    private Date modifiedDate;
//
//    @Column(name = "createdby")
//    @CreatedBy
//    private String createdBy;
//
//    @Column(name = "modifiedby")
//    @LastModifiedBy
//    private String modifiedBy;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
//                ", createdDate=" + createdDate +
//                ", modifiedDate=" + modifiedDate +
//                ", createdBy='" + createdBy + '\'' +
//                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}
