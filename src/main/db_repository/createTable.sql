'post table 생성 query'
CREATE TABLE `honeytip`.`post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_category` INT NOT NULL,
  `id_member` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `contents` VARCHAR(45) NOT NULL,
  `hits` INT NOT NULL,
  `likes` INT NOT NULL,
  `created_at` DATETIME NULL,
  `modified_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
