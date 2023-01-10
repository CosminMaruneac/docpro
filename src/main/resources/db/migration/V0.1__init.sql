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
    PRIMARY KEY (id)
);

CREATE TABLE service
(
    id           bigint generated always as identity,
    service_type varchar,
    price        int,
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