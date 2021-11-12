create table IF NOT EXISTS products
(
    id              numeric       NOT NULL,
    productName     varchar(255)  NOT NULL,
    productPrice    decimal       NOT NULL,
    saleOff         decimal       NULL,
    categoryId      numeric       NOT NULL,
    description     varchar(4000) NOT NULL,
    quantity        numeric       NOT NULL,

    status          boolean  default true,
    createdDateTime DATETIME default now(),
    updatedDateTime DATETIME default now(),

    CONSTRAINT products_id_pk PRIMARY KEY (id),

    CONSTRAINT products_categoryId_categories FOREIGN KEY (categoryId)
        REFERENCES categories (id)
);
