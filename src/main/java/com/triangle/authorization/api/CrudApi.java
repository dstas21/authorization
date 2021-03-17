package com.triangle.authorization.api;

import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Интерфес api создания, чтения, обновления и удаления (CRUD) сущности
 *
 * @param <D> dto сущности
 */
public interface CrudApi<D> {

    /**
     * Создание сущности
     *
     * @param dto dto сущности
     */
    D create(D dto);

    /**
     * Получение всех сущностей
     *
     * @param pageable пагинация выдачи сущностей
     */
    List<D> getAll(Pageable pageable);

    /**
     * Получение сущности по id
     *
     * @param id идентификатор
     */
    D getById(Long id);

    /**
     * Обновление сущности
     *
     * @param id  идентификатор
     * @param dto dto с обновленными полями сущности
     */
    D update(Long id, D dto);

    /**
     * Удаление сущности по id
     *
     * @param id идентификатор
     */
    void delete(Long id);
}
