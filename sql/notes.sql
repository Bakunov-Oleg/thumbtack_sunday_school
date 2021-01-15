DROP DATABASE IF EXISTS notes;
CREATE DATABASE `notes`;
USE `notes`;

CREATE TABLE `user`(
`id` INT(11)NOT NULL AUTO_INCREMENT,
`firstName` VARCHAR(50)NOT NULL,
`lastName` VARCHAR(50)NOT NULL,
`patronymic` VARCHAR(50),
`login` VARCHAR(50)NOT NULL,
`password` VARCHAR(50)NOT NULL,
`userStatus` ENUM('ACTIVE', 'DELETED') NOT NULL,
`rating` DOUBLE NOT NULL,
`createDate` DATETIME NOT NULL,
 UNIQUE KEY(`login`),
PRIMARY KEY(`id`))ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `section`(
`id` INT(11)NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50)NOT NULL,
`authorId` INT(11) NOT NULL,
FOREIGN KEY(`authorId`) REFERENCES `user`(`id`),
PRIMARY KEY(`id`))
ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `note` (
 `id` INT(11) NOT NULL AUTO_INCREMENT,
 `subject` VARCHAR(50) NOT NULL,
 `sectionId` INT(11) NOT NULL,
 `authorId` INT(11) NOT NULL,
 `rating` DOUBLE NOT NULL,
PRIMARY KEY(`id`),
FOREIGN KEY(`authorId`) REFERENCES `user`(`id`),
FOREIGN KEY(`sectionId`) REFERENCES `section`(`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `note_revision` (
 `noteId` INT(11) NOT NULL,
 `revisionId` INT(11) NOT NULL,
 `body` VARCHAR(50) NOT NULL,
 `created` datetime NOT NULL,
PRIMARY KEY(`noteId`),
FOREIGN KEY(`noteId`) REFERENCES `note`(`id`) ON delete CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `comment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `body` VARCHAR(50) NOT NULL,
  `noteId` INT(11) NOT NULL,
  `revisionId` INT(11) NOT NULL,
  `authorId` INT(11) NOT NULL,
  `created` datetime NOT NULL,
  FOREIGN KEY(`authorId`) REFERENCES `user`(`id`),
  FOREIGN KEY(`noteId`) REFERENCES `note`(`id`),
  /*FOREIGN KEY(`revisionId`) REFERENCES `note_revision`(`revisionId`) ON delete CASCADE,*/
  PRIMARY KEY(`id`)
  ) ENGINE=INNODB DEFAULT CHARSET=utf8;
  
/*
FOLLOWERS & FOLLOWING
*/
CREATE TABLE `follower`(
`idUser1` INT(11)NOT NULL,
`idUser2` INT(11)NOT NULL,
FOREIGN KEY(`idUser1`)REFERENCES `user`(`id`)ON delete CASCADE,
FOREIGN KEY(`idUser2`)REFERENCES `user`(`id`)ON delete CASCADE)
ENGINE = INNODB DEFAULT CHARSET = utf8;
/*
IGNORE & IGNORED
*/
CREATE TABLE `ignore`(
`idUser1` INT(11)NOT NULL,
`idUser2` INT(11)NOT NULL,
FOREIGN KEY(`idUser1`)REFERENCES `user`(`id`)ON delete CASCADE,
FOREIGN KEY(`idUser2`)REFERENCES `user`(`id`)ON delete CASCADE)
ENGINE = INNODB DEFAULT CHARSET = utf8;
/*
SUPER USER - ADMIN ACCOUNT
*/
CREATE TABLE `superuser`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`userId` INT(11)NOT NULL,
UNIQUE KEY(`userId`),
FOREIGN KEY(`userId`)REFERENCES `user`(`id`)ON delete CASCADE)
ENGINE = INNODB DEFAULT CHARSET = utf8;
/*
SESSIONS
*/
create TABLE `session`(
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`userId` INT(11)NOT NULL,
`token` CHAR(50)NOT NULL,
FOREIGN KEY(`userId`)REFERENCES `user`(`id`)ON delete CASCADE,
UNIQUE KEY(`userId`),
KEY(`token`))
ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE utf8_general_ci;
/*
INSERT DEFAULT DATA
*/
-- Admin account
insert into `user`(`id`, `firstName`, `lastName`, `patronymic`, `login`, `password`, `userStatus`, `rating`, `createDate`)
values(null, 'admin', 'admin', null, 'admin', 'admin', 'ACTIVE', 0, NOW());insert into `superuser`(`userId`)
values(last_insert_id());