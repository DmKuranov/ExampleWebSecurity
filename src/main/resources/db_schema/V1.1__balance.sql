CREATE TABLE user_balances (
  user_id int NOT NULL,
  balance DECIMAL(20, 2) NOT NULL
);

ALTER TABLE user_balances ADD CONSTRAINT user_id_UQ UNIQUE(user_id);
ALTER TABLE user_balances ADD CONSTRAINT user_fk FOREIGN KEY(user_id) REFERENCES users(id);