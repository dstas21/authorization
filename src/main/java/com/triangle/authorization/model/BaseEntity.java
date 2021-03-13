package com.triangle.authorization.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Базовая сущность, предназначена для вынесения общих полей для всех сущностей
 */
@MappedSuperclass
@Data
public class BaseEntity {

    /**
     * Идентификатор сущности
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Дата создания сущности
     */
    @CreatedDate
    @Column(name = "created")
    private Date created;

    /**
     * Дата обновления сущности
     */
    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;

    /**
     * Статус сущности
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
