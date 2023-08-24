CREATE TABLE users(
     id BIGSERIAL NOT NULL PRIMARY KEY,
     email varchar(20) UNIQUE NOT NULL,
     password varchar(80) NOT NULL,
     role user_role  NOT NULL,
     first_name varchar(20) NOT NULL,
     last_name  varchar(20) NOT NULL,
     third_name varchar(20),
     address varchar(25),
     phone_number  varchar(15),
     inn varchar(10),
     organization_name varchar(30),
     is_deleted boolean NOT NULL,
     date_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);
