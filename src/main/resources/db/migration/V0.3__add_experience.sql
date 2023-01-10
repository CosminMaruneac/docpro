CREATE TABLE user_experience
(
    id          bigint generated always as identity,
    date        date,
    description text,
    user_id     bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES account (id)
);