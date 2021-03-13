CREATE TABLE IF NOT EXISTS authentication.roles
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(255)          NOT NULL,
    created     TIMESTAMP             NOT NULL  DEFAULT current_timestamp,
    updated     TIMESTAMP             NOT NULL  DEFAULT current_timestamp,
    status      VARCHAR(50)           NOT NULL  DEFAULT 'ACTIVE'
);

create unique index if not exists role_name_unique_ix on authentication.roles (name);

COMMENT ON TABLE authentication.roles IS 'Таблица с ролями';
COMMENT ON COLUMN authentication.roles.id IS 'Идентификатор роли';
COMMENT ON COLUMN authentication.roles.name IS 'Имя роли';
COMMENT ON COLUMN authentication.roles.created IS 'Дата создания роли';
COMMENT ON COLUMN authentication.roles.updated IS 'Дата обновления роли';
COMMENT ON COLUMN authentication.roles.status IS 'Статус роли';

insert into authentication.roles(name)
values ('user'),
       ('admin');
