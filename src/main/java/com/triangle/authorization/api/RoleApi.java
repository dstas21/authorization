package com.triangle.authorization.api;

import com.triangle.authorization.dto.RoleDto;
import com.triangle.authorization.mapper.RoleMapper;
import com.triangle.authorization.model.Role;
import com.triangle.authorization.service.RoleService;
import org.springframework.stereotype.Component;

/**
 * Api для сущности роли {@link Role}
 */
@Component
public class RoleApi extends AbstractCrudApi<Role, RoleDto> {

    public RoleApi(RoleService service,
                   RoleMapper mapper) {
        super(service, mapper);
    }
}
