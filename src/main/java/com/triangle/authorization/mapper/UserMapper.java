package com.triangle.authorization.mapper;

import com.triangle.authorization.dto.UserDto;
import com.triangle.authorization.model.Role;
import com.triangle.authorization.model.User;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Маппер для преобрахование из dto в сущность и обратно
 * для моделей пользователей{@link Role}
 */
@Component
@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper extends AbstractMapper<User, UserDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    UserDto toDto(User entity);

    /**
     * {@inheritDoc}
     */
    @Override
    User toEntity(UserDto dto);

    /**
     * {@inheritDoc}
     */
    @Override
    List<UserDto> toDtoList(Page<User> entitys);
}
