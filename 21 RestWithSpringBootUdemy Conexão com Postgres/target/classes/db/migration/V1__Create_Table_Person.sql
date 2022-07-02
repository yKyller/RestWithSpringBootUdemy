CREATE SEQUENCE person_seq;

CREATE TABLE IF NOT EXISTS person (
  id bigint NOT NULL DEFAULT NEXTVAL ('person_seq'),
  address varchar(100) NOT NULL,
  first_name varchar(80) NOT NULL,
  gender varchar(6) NOT NULL,
  last_name varchar(80) NOT NULL,
  PRIMARY KEY (id)
)