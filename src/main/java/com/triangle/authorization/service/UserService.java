package com.triangle.authorization.service;

import com.triangle.authorization.exception.ConflictException;
import com.triangle.authorization.exception.NotFoundException;
import com.triangle.authorization.model.User;
import com.triangle.authorization.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для сущности пользователя {@link User}
 */
@Service
@Slf4j
public class UserService extends AbstractCrudService<User> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User create(User entity) {
        checkDuplicateUsername(entity);
        checkDuplicateEmail(entity);

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        User createdUser = super.create(entity);

        log.info("Создан пользователь с полями {}", createdUser);

        return createdUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<User> getAll(Pageable pageable) {
        Page<User> users = super.getAll(pageable);
        log.info("Всего пользователей найдено {}", users.getSize());
        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(Long id) throws RuntimeException {
        User foundUser = super.getById(id);
        log.info("Пользователь {} найден по id = {}", foundUser, id);
        return foundUser;
    }


    /**
     * Поиск пользователя по логину, если нет то выбрасываем ошибку
     *
     * @param username логин
     */
    public User getByUsername(String username) throws RuntimeException {
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(username));
        log.info("Пользователь {} найден по username = {}", foundUser, username);
        return foundUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User update(User entity) throws RuntimeException {
        checkDuplicateUsername(entity);
        checkDuplicateEmail(entity);

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        User updatedUser = super.update(entity);

        log.info("Обновлен пользователь с полями {}", updatedUser);

        return updatedUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        super.delete(id);
        log.info("Удален пользователь с id = {}", id);
    }

    /**
     * Проверяем пользователя на уникальность логина
     * Если пользователь с таким логином существует - исключение {@link ConflictException}
     *
     * @param user пользователь
     */
    private void checkDuplicateUsername(User user) {
        String username = user.getUsername();
        userRepository.findByUsername(username)
                      .ifPresent(found -> {
                          if (!found.getId().equals(user.getId())) {
                              throw new ConflictException(username);
                          }
                      });
    }

    /**
     * Проверяем пользователя на уникальность почты
     * Если пользователь с такой почтой существует - исключение {@link ConflictException}
     *
     * @param user пользователь
     */
    private void checkDuplicateEmail(User user) {
        String email = user.getEmail();
        userRepository.findByEmail(email)
                      .ifPresent(found -> {
                          if (!found.getId().equals(user.getId())) {
                              throw new ConflictException(email);
                          }
                      });
    }
}
