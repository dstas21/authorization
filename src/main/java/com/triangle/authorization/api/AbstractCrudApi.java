package com.triangle.authorization.api;

import com.triangle.authorization.mapper.AbstractMapper;
import com.triangle.authorization.model.BaseEntity;
import com.triangle.authorization.service.CrudService;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Абстрактная реализация интерфес api создания, чтения, обновления и удаления (CRUD) сущности
 *
 * @param <E> сущность
 * @param <D> dto сущности
 */
public abstract class AbstractCrudApi<E extends BaseEntity, D> implements CrudApi<D> {

    private final CrudService<E> service;
    private final AbstractMapper<E, D> mapper;

    public AbstractCrudApi(CrudService<E> service,
                           AbstractMapper<E, D> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public D create(D dto) {
        return mapper.toDto(service.create(mapper.toEntity(dto)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<D> getAll(Pageable pageable) {
        return mapper.toDtoList(service.getAll(pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D getById(Long id) {
        return mapper.toDto(service.getById(id));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public D update(Long id, D dto) {
        service.getById(id);
        E entity = mapper.toEntity(dto);
        entity.setId(id);
        return mapper.toDto(service.update(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
