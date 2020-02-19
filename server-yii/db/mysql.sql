CREATE TABLE `admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `ent_types` INT NOT NULL,
  `ent_status` INT NOT NULL,
  `creater` INT NOT NULL,
  `updater` INT NOT NULL,
  `create_date` DATETIME NOT NULL,
  `update_date` DATETIME NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `auth_key` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

  INSERT INTO `enuocms_test`.`admin`
(`id`,
`name`,
`ent_types`,
`ent_status`,
`creater`,
`updater`,
`create_date`,
`update_date`,
`password`,
`auth_key`)
VALUES
(1,
'admin',
1,
1,
1,
1,
now(),
now(),
'admin',
'');


CREATE TABLE `article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `ent_types` INT NOT NULL,
  `ent_status` INT NOT NULL,
  `creater` INT NOT NULL,
  `updater` INT NOT NULL,
  `create_date` DATETIME NOT NULL,
  `update_date` DATETIME NOT NULL,
  `intro` VARCHAR(500) NULL,
  `pic_url` VARCHAR(500) NULL,
  `content` TEXT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `ent_types` INT(11) NOT NULL,
  `ent_status` INT(11) NOT NULL,
  `creater` INT(11) NOT NULL,
  `updater` INT(11) NOT NULL,
  `create_date` DATETIME NOT NULL,
  `update_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`));
