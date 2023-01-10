ALTER TABLE account
    ADD COLUMN description   varchar,
    ADD COLUMN time_schedule varchar;

ALTER TABLE service
    DROP COLUMN service_type,
    ADD COLUMN name varchar;