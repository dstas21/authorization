package com.triangle.authorization.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Сущность Пользователь
 */
@Entity
@Table(name = "users", schema = "authentication")
@Getter
@Setter
@ToString
public class User extends BaseEntity {

    /**
     * Уникальный логин
     */
    @Column(name = "username", unique = true)
    private String username;

    /**
     * Имя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Почта
     */
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Пароль
     */
    @Column(name = "password")
    private String password;

    /**
     * Связь между пользователями и их ролями, через связь ManyToMany, создается таблица user_roles,
     * где хранятся связи
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;
}
