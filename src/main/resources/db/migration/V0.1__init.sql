CREATE TABLE account
(
    id                  bigint generated always as identity,
    first_name          varchar,
    last_name           varchar,
    address             varchar,
    phone_number        varchar,
    user_type           varchar,
    user_specialization varchar,
    password            varchar,
    email               varchar,
    profile_image_url   varchar,
    description         varchar,
    time_schedule       varchar,
    PRIMARY KEY (id)
);

CREATE TABLE service
(
    id           bigint generated always as identity,
    price        int,
    name         varchar,
    PRIMARY KEY (id)
);

CREATE TABLE appointment
(
    id           bigint generated always as identity,
    date         timestamp without time zone,
    phone_number varchar,
    price        int,
    service_id   bigint,
    creator_id   bigint,
    doctor_id    bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (service_id) REFERENCES service (id),
    FOREIGN KEY (creator_id) REFERENCES account (id),
    FOREIGN KEY (doctor_id) REFERENCES account (id)

);

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

CREATE TABLE user_experience
(
    id          bigint generated always as identity,
    date        date,
    description text,
    user_id     bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES account (id)
);

CREATE TABLE user_service
(
    user_id    bigint,
    service_id bigint,
    FOREIGN KEY (user_id) REFERENCES account (id),
    FOREIGN KEY (service_id) REFERENCES service (id)
);