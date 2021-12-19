create table IF NOT EXISTS transactions
(
    id              numeric NOT NULL,
    userId          numeric NOT NULL,
    totalCart       decimal NOT NULL,

    status          boolean default false,
    createdDateTime DATETIME,
    updatedDateTime DATETIME,
    createdBy       varchar(200),
    lastModifiedBy  varchar(200),


    CONSTRAINT transactions_id_pk PRIMARY KEY (id),
    CONSTRAINT transactions_userId_users FOREIGN KEY (userId)
        REFERENCES users (id)
);
