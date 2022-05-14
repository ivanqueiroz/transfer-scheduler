DROP TABLE IF EXISTS transfer;

CREATE TABLE transfer
(
    id                     INT AUTO_INCREMENT PRIMARY KEY,
    amount                 DECIMAL(10, 2) NOT NULL,
    tax                    DECIMAL(10, 2) NOT NULL,
    transfer_date          DATE           NOT NULL,
    schedule_date          DATE           NOT NULL,
    fk_source_account      varchar(20)    NOT NULL,
    fk_destination_account varchar(20)    NOT NULL,
    transfer_type          VARCHAR(1)     NOT NULL
);

ALTER TABLE transfer
    ADD FOREIGN KEY (fk_source_account) REFERENCES account (number);
ALTER TABLE transfer
    ADD FOREIGN KEY (fk_destination_account) REFERENCES account (number);
