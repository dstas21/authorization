package com.triangle.authorization.mapper;

import com.triangle.authorization.dto.RoleDto;
import com.triangle.authorization.model.Role;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Маппер для преобрахование из dto в сущность и обратно
 * для моделей ролей {@link Role}
 */
@Component
@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface RoleMapper extends AbstractMapper<Role, RoleDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    RoleDto toDto(Role entity);

    /**
     * {@inheritDoc}
     */
    @Override
    Role toEntity(RoleDto dto);

    /**
     * {@inheritDoc}
     */
    @Override
    List<RoleDto> toDtoList(Page<Role> entitys);
}
