CREATE TABLE messages(
  id BIGSERIAL NOT NULL PRIMARY KEY,
  appeal_id bigint NOT NULL,
  owner_id bigint NOT NULL,
  content varchar(255) NOT NULL ,
  date_create timestamp NOT NULL ,
  FOREIGN KEY(appeal_id) REFERENCES appeals(id),
  FOREIGN KEY(owner_id) REFERENCES users(id)

);
