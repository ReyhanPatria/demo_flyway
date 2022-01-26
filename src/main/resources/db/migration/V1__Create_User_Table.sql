CREATE TABLE `user` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255),
    `password` VARCHAR(255),
    `name` VARCHAR(255)
);

INSERT INTO user (
    `email`, 
    `password`, 
    `name`
) VALUES (
    "reyhan@email.com",
    "password",
    "Reyhan"
);