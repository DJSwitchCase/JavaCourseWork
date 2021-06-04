CREATE TABLE IF NOT EXISTS levels
(
    id    BIGSERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    complexity varchar(200) not null,
    game_id bigint,
    constraint fk_game_id
        foreign key (game_id)
            references games(id)
);

CREATE TABLE IF NOT EXISTS games(
    id BIGSERIAL PRIMARY KEY,
    name varchar(200) NOT NULL,
    date varchar(200) NOT NULL
);

