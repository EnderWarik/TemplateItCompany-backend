CREATE TABLE statuses(
  id BIGSERIAL NOT NULL PRIMARY KEY,
  status status_appeal NOT NULL ,
  appeal_id bigint NOT NULL,
  is_deleted boolean NOT NULL,
  date_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);
