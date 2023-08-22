CREATE TABLE appeals(
  id BIGSERIAL NOT NULL PRIMARY KEY,
  user_id bigint NOT NULL,
  employee_id bigint NOT NULL,
  status_id bigint NOT NULL ,
  date_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY(employee_id) REFERENCES users(id),
  FOREIGN KEY(status_id) REFERENCES statuses(id)
);
