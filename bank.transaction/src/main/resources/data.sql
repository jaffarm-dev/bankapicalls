DROP TABLE IF EXISTS account;

CREATE TABLE account (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  account_number VARCHAR(250) NOT NULL,
  balance decimal(10,2)  NOT NULL,
  last_update_timestamp  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
  trx_type VARCHAR(25) NOT NULL
);