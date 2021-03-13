package com.triangle.authorization.mapper;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Абстрактный маппер для преобрахование из dto в сущность и обратно
 */
public interface AbstractMapper<E, D> {

    /**
     * Преобразование в dto
     *
     * @param entity сущность
     */
    D toDto(E entity);

    /**
     * Преобразование в сущность
     *
     * @param dto dto
     */
    E toEntity(D dto);

    /**
     * Преобразование списка сущностей в список dto
     *
     * @param entitys список сущностей
     */
    List<D> toDtoList(Page<E> entitys);
}
