CREATE TABLE persona (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    gender VARCHAR(255),
    age INT,
    identification VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255)
);

CREATE TABLE cliente (
    id BIGINT PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL,
    CONSTRAINT fk_cliente_persona FOREIGN KEY (id) REFERENCES persona(id)
);


CREATE TABLE Account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(50) NOT NULL UNIQUE,
    account_type VARCHAR(50) NOT NULL,
    initial_balance DECIMAL(15, 2) NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE Transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_date DATE NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL,
    account_id BIGINT,
    FOREIGN KEY (account_id) REFERENCES Account(id)
);