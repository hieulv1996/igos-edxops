create table IF NOT EXISTS productImages
(
    id              numeric       NOT NULL,
    productId       numeric       NOT NULL,
    imageUrl        varchar(4000) NOT NULL,

    status          boolean  default true,
    createdDateTime DATETIME,
    updatedDateTime DATETIME,

    CONSTRAINT productImages_id_pk PRIMARY KEY (id),
    CONSTRAINT productImages_productId_product FOREIGN KEY (productId)
        REFERENCES products (id)
);
