CREATE TABLE `transactions` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `userId` INT NOT NULL,
    FOREIGN KEY (userId) REFERENCES user(id)
);

CREATE TABLE `transaction_details` (
    `transactionId` INT NOT NULL,
    `productId` INT NOT NULL,
    `quantity` INT NOT NULL,
    PRIMARY KEY(transactionId, productId),
    FOREIGN KEY (transactionId) REFERENCES transactions(id),
    FOREIGN KEY (productId) REFERENCES product(id)
);