create table UserConnection (
	userId varchar(255) not null,
    providerId varchar(255) not null,
    providerUserId varchar(255),
    rank int not null,
    displayName varchar(255),
    profileUrl varchar(512),
    imageUrl varchar(512),
    accessToken varchar(255) not null,
    secret varchar(255),
    refreshToken varchar(255),
    expireTime bigint,
    primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);


CREATE TABLE course (
  id integer NOT NULL IDENTITY,
  course_topic varchar(250) NOT NULL
);



CREATE TABLE lesson (
  id integer NOT NULL IDENTITY,
  course_id integer NOT NULL,
  lesson_name varchar(250) NOT NULL,
  CONSTRAINT lesson_course_fk_id FOREIGN KEY (course_id) REFERENCES course (id)
);


CREATE TABLE lesson_step (
  id integer NOT NULL IDENTITY,
  lesson_id integer NOT NULL,
  content varchar(250) NOT NULL,
  CONSTRAINT lesson_step_lesson_fk_id FOREIGN KEY (lesson_id) REFERENCES lesson (id)
);



CREATE TABLE user (
  id integer NOT NULL IDENTITY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  sign_in_provider VARCHAR(250) NULL,
  password VARCHAR(250) NULL,
  granted_authorities varchar(250) NULL
);




CREATE TABLE foo (
  id integer NOT NULL IDENTITY,
  name VARCHAR(250) NULL,
  description VARCHAR(250) NULL
);

