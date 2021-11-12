create table IF NOT EXISTS categories
(
    id              numeric      NOT NULL,
    categoryName    varchar(255) NOT NULL,
    categoryType    varchar(255) NOT NULL,

    status          boolean  default true,
    createdDateTime DATETIME default now(),
    updatedDateTime DATETIME default now(),

    CONSTRAINT categories_id_pk PRIMARY KEY (id)
);
