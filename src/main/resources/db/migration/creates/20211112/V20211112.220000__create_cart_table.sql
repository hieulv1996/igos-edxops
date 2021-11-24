create table IF NOT EXISTS carts
(
    id              numeric     NOT NULL,
    identityId      varchar(50) NOT NULL,
    productId       numeric     NOT NULL,

    status          boolean  default false,
    createdDateTime DATETIME,
    updatedDateTime DATETIME,

    CONSTRAINT carts_id_pk PRIMARY KEY (id),
    CONSTRAINT carts_productId_products FOREIGN KEY (productId)
        REFERENCES products (id)
);
