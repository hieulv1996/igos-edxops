create table IF NOT EXISTS transactions
(
    id              numeric NOT NULL,
    userId          numeric NOT NULL,
    totalCart       decimal NOT NULL,

    status          boolean  default false,
    createdDateTime DATETIME default now(),
    updatedDateTime DATETIME default now(),

    CONSTRAINT transactions_id_pk PRIMARY KEY (id),
    CONSTRAINT transactions_userId_users FOREIGN KEY (userId)
        REFERENCES users (id)
);
