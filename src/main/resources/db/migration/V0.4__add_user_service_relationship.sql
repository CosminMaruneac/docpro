CREATE TABLE user_service
(
    user_id    bigint,
    service_id bigint,
    FOREIGN KEY (user_id) REFERENCES account (id),
    FOREIGN KEY (service_id) REFERENCES service (id)
);