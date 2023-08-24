CREATE TABLE appeals(
  id BIGSERIAL NOT NULL PRIMARY KEY,
  user_creator_id bigint NOT NULL,
  user_employee_id bigint,
  status_id bigint NOT NULL ,
  title varchar NOT NULL ,
  is_deleted boolean NOT NULL,
  date_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(user_creator_id) REFERENCES users(id),
  FOREIGN KEY(user_employee_id) REFERENCES users(id),
  FOREIGN KEY(status_id) REFERENCES statuses(id)
);
