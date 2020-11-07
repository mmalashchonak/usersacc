CREATE DATABASE usersacc;
USE usersacc;
CREATE TABLE IF NOT EXISTS user (
    userid INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(45), 
    sureName VARCHAR(45)
);
INSERT INTO user (name, sureName) VALUES ('Ivan', 'Ivanov');
INSERT INTO user (name, sureName) VALUES ('Peter', 'Petrov');
INSERT INTO user (name, sureName) VALUES ('Sidor', 'Sidorov');
INSERT INTO user (name, sureName) VALUES ('Gleb', 'Denisov');
INSERT INTO user (name, sureName) VALUES ('Artem', 'Savin');
INSERT INTO user (name, sureName) VALUES ('Victor', 'Loev');
INSERT INTO user (name, sureName) VALUES ('Alex', 'Minin');
INSERT INTO user (name, sureName) VALUES ('Sergei', 'Durov');
INSERT INTO user (name, sureName) VALUES ('Oleg', 'Sadov');
INSERT INTO user (name, sureName) VALUES ('Denis', 'Volkov');
CREATE TABLE IF NOT EXISTS account (
    accountId INT AUTO_INCREMENT PRIMARY KEY, 
    account INT, 
    userId INT
);
INSERT INTO account (account, userId) VALUES (52545, 1);
INSERT INTO account (account, userId) VALUES (14345, 2);
INSERT INTO account (account, userId) VALUES (276845, 3);
INSERT INTO account (account, userId) VALUES (552545, 4);
INSERT INTO account (account, userId) VALUES (252545, 5);
INSERT INTO account (account, userId) VALUES (652545, 6);
INSERT INTO account (account, userId) VALUES (652545, 7);
INSERT INTO account (account, userId) VALUES (252545, 8);
INSERT INTO account (account, userId) VALUES (852545, 9);
INSERT INTO account (account, userId) VALUES (752545, 10);