CREATE TABLE review
(
    id             bigint generated always as identity,
    creator_id     bigint,
    facilitator_id bigint,
    description    text,
    stars          integer,
    created_at     date,
    PRIMARY KEY (id),
    FOREIGN KEY (creator_id) REFERENCES account (id),
    FOREIGN KEY (facilitator_id) REFERENCES account (id)
);