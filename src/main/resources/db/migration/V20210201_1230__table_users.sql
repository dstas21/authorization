CREATE SCHEMA IF NOT EXISTS authentication;

COMMENT ON SCHEMA authentication IS 'Схема для сервиса авторизации';


CREATE TABLE IF NOT EXISTS authentication.user
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    username   VARCHAR(80)           NOT NULL,
    first_name VARCHAR(80)           NOT NULL,
    last_name  VARCHAR(80)           NOT NULL,
    email      VARCHAR(255)          NOT NULL,
    password   VARCHAR(255)          NOT NULL,
    created    TIMESTAMP             NOT NULL DEFAULT current_timestamp,
    updated    TIMESTAMP             NOT NULL DEFAULT current_timestamp,
    status     VARCHAR(50)           NOT NULL DEFAULT 'ACTIVE'
);

create unique index if not exists user_username_unique_ix on authentication.user (username);
create unique index if not exists user_email_unique_ix on authentication.user (email);

COMMENT ON TABLE authentication.user IS 'Таблица с пользователями';
COMMENT ON COLUMN authentication.user.id IS 'Идентификатор пользователя';
COMMENT ON COLUMN authentication.user.username IS 'Имя пользователя';
COMMENT ON COLUMN authentication.user.first_name IS 'Имя пользователя';
COMMENT ON COLUMN authentication.user.last_name IS 'Фамилия пользователя';
COMMENT ON COLUMN authentication.user.email IS 'Почта пользователя';
COMMENT ON COLUMN authentication.user.password IS 'Пароль пользователя';
COMMENT ON COLUMN authentication.user.created IS 'Дата создания пользователя';
COMMENT ON COLUMN authentication.user.updated IS 'Дата обновления пользователя';
COMMENT ON COLUMN authentication.user.status IS 'Статус пользователя';
