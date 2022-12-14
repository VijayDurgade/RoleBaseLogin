CREATE TABLE users (
  id BIGINT AUTO_INCREMENT NOT NULL,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   date_of_birth date,
   email VARCHAR(255),
   mobile_number VARCHAR(255),
   user_name VARCHAR(255),
   password VARCHAR(255),
   physician_name VARCHAR(255),
   CONSTRAINT pk_users PRIMARY KEY (id)
);