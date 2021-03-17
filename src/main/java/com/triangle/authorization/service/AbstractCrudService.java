package com.triangle.authorization.service;

import com.triangle.authorization.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Абстрактная реализация интерфейса сервиса создания, чтения, обновления и удаления (CRUD) сущности
 *
 * @param <E> сущност
 */
public abstract class AbstractCrudService<E> implements CrudService<E> {

    private final JpaRepository<E, Long> repository;

    protected AbstractCrudService(JpaRepository<E, Long> repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public E create(E entity) {
        return repository.save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<E> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E getById(Long id) throws RuntimeException {
        return repository.findById(id).orElseThrow(() -> notFound(id));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public E update(E entity) throws RuntimeException {
        return repository.save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * Искллючение при отсутсвии сущности в базе
     *
     * @param id идентификатор сущности
     */
    protected RuntimeException notFound(Long id) {
        return new NotFoundException(id);
    }
}
