package com.triangle.authorization.service;

import com.triangle.authorization.exception.ConflictException;
import com.triangle.authorization.exception.NotFoundException;
import com.triangle.authorization.model.Role;
import com.triangle.authorization.model.User;
import com.triangle.authorization.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для сущности роли {@link Role}
 */
@Service
@Slf4j
public class RoleService extends AbstractCrudService<Role> {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role create(Role entity) {
        checkDuplicateName(entity);
        Role createdRole = super.create(entity);
        log.info("Создана роль с полями {}", createdRole);
        return createdRole;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Role> getAll(Pageable pageable) {
        Page<Role> roles = super.getAll(pageable);
        log.info("Всего ролей найдено {}", roles.getSize());
        return roles;
    }

    public Page<Role> getByUser(User user, Pageable pageable) {
        return roleRepository.findByUsers(user, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role getById(Long id) throws RuntimeException {
        Role foundRole = super.getById(id);
        log.info("Роль {} найдена по id = {}", foundRole, id);
        return foundRole;
    }

    /**
     * Поиск роли по названию, если нет то выбрасываем ошибку
     *
     * @param name название
     */
    public Role getByName(String name) throws RuntimeException {
        Role foundRole = roleRepository.findByName(name).orElseThrow(() -> new NotFoundException(name));
        log.info("Роль {} найдена по name = {}", foundRole, name);
        return foundRole;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role update(Role entity) throws RuntimeException {
        checkDuplicateName(entity);
        Role updatedRole = super.update(entity);
        log.info("Обновлена роль с полями {}", updatedRole);
        return updatedRole;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        super.delete(id);
        log.info("Удалена роль с id = {}", id);
    }

    /**
     * Проверяем роль на уникальность названия
     * Если роль с таким названием существует - исключение {@link ConflictException}
     *
     * @param role роль
     */
    private void checkDuplicateName(Role role) {
        String name = role.getName();
        roleRepository.findByName(name)
                      .ifPresent(found -> {
                          if (!found.getId().equals(role.getId())) {
                              throw new ConflictException(name);
                          }
                      });
    }
}
