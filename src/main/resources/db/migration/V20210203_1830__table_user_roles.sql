CREATE TABLE IF NOT EXISTS authentication.user_roles
(
    user_id     BIGINT NOT NULL,
    role_id     BIGINT NOT NULL,
    primary key (user_id, role_id)
);

COMMENT ON TABLE authentication.user_roles IS 'Таблица связывает пользователей с ролями';
COMMENT ON COLUMN authentication.user_roles.user_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN authentication.user_roles.role_id IS 'Идентификатор роли';