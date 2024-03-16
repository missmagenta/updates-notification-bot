--liquibase formatted sql

--changeset magenta:1_29.02.2024
CREATE TABLE IF NOT EXISTS "link" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "last_update_date" TIMESTAMP NOT NULL
)

