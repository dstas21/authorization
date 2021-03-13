package com.triangle.authorization.api;

import com.triangle.authorization.dto.UserDto;
import com.triangle.authorization.mapper.UserMapper;
import com.triangle.authorization.model.User;
import com.triangle.authorization.service.UserService;
import org.springframework.stereotype.Component;

/**
 * Api для сущности пользователь {@link User}
 */
@Component
public class UserApi extends AbstractCrudApi<User, UserDto> {

    public UserApi(UserService service,
                   UserMapper mapper) {
        super(service, mapper);
    }
}
