-- src/main/resources/db/migration/V1__Create_categories_table.sql
CREATE TABLE categories (
    uuid UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);