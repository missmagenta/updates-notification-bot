--liquibase formatted sql

--changeset magenta:1_29.02.2024
CREATE TABLE IF NOT EXISTS "chat_link" (
    "chat_id" INTEGER NOT NULL,
    "link_id" INTEGER NOT NULL,
    CONSTRAINT pk_chat_link PRIMARY KEY ("chat_id", "link_id"),
    FOREIGN KEY ("chat_id") REFERENCES "chat" ("id"),
    FOREIGN KEY ("link_id") REFERENCES "link" ("id")
)


