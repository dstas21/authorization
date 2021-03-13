package com.triangle.authorization.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Сущность Роли
 */
@Entity
@Table(name = "roles", schema = "authentication")
@Getter
@Setter
@ToString
public class Role extends BaseEntity {

    /**
     * Имя
     */
    @Column(name = "name", unique = true)
    private String name;

    /**
     * Список пользователей, вспомагательное поле, для выстроение связи ManyToMany
     * между пользователями и их ролями
     */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
