package com.bookstore.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AbstractEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @CreatedDate
    @Column(name = "creation_tsmp")
    private Instant creationTsmp;

    @LastModifiedDate
    @Column(name = "modification_tsmp")
    private Instant modificationTsmp;

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 64)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "modified_by", length = 64)
    private String modifiedBy;
}
