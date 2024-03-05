--liquibase formatted sql

--changeset magenta:1_29.02.2024
CREATE TABLE IF NOT EXISTS "chat" (
    "id" SERIAL PRIMARY KEY
)

