CREATE TABLE status(
  id BIGSERIAL NOT NULL PRIMARY KEY,
  status status_appeal NOT NULL ,
  appeal_id bigint NOT NULL,
  date_create timestamp NOT NULL

);
