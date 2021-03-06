create table IF NOT EXISTS users
(
    id              numeric      NOT NULL,
    username        varchar(100) NOT NULL,
    password        varchar(200) NOT NULL,
    fullName        varchar(200),
    dob             DATETIME,
    address         varchar(255),
    status          boolean default true,
    createdDateTime DATETIME,
    updatedDateTime DATETIME,

    createdBy       varchar(200),
    lastModifiedBy  varchar(200),

    CONSTRAINT users_id_pk PRIMARY KEY (id),
    CONSTRAINT users_username_uq unique (username)
);
