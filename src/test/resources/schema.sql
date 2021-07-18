CREATE DATABASE card_cost_db;
CREATE USER card_cost_user WITH PASSWORD 'password';
GRANT ALL ON DATABASE card_cost_db TO card_cost_user;

\c card_cost_db;

SET ROLE card_cost_user;

CREATE TABLE IF NOT EXISTS clearing_costs (
    country VARCHAR(6) NOT NULL PRIMARY KEY,
    cost DECIMAL(3, 0) NOT NULL
);