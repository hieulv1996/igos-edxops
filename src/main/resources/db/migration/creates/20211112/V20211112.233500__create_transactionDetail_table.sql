create table IF NOT EXISTS transactionDetails
(
    id              numeric NOT NULL,
    transactionId   numeric NOT NULL,
    cartId          numeric NOT NULL,

    status          boolean  default false,
    createdDateTime DATETIME,
    updatedDateTime DATETIME,

    CONSTRAINT transactionDetails_id_pk PRIMARY KEY (id),
    CONSTRAINT transactionDetails_transactionId_transactions FOREIGN KEY (transactionId)
        REFERENCES transactions (id),

    CONSTRAINT transactionDetails_cartId_carts FOREIGN KEY (cartId)
        REFERENCES carts (id)
);
