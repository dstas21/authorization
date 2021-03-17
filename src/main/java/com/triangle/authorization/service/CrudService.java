package com.triangle.authorization.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Интерфес сервиса создания, чтения, обновления и удаления (CRUD) сущности
 *
 * @param <E> сущност
 */
public interface CrudService<E> {

    /**
     * Создаем сущность
     *
     * @param entity сущность
     */
    E create(E entity);

    /**
     * Получаем список всех сущностей
     *
     * @param pageable пагинация выдачи сущностей
     */
    Page<E> getAll(Pageable pageable);

    /**
     * Поиск сущности по id, если нет то выбрасываем ошибку
     *
     * @param id идентификатор сущности
     */
    E getById(Long id) throws RuntimeException;

    /**
     * Обновляем сущность
     *
     * @param entity сущность
     */
    E update(E entity) throws RuntimeException;

    /**
     * Удаляем сущность по id
     *
     * @param id идентификатор сущности
     */
    void delete(Long id);
}
