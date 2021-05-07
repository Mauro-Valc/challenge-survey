-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema challenge
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema challenge
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `challenge` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `challenge` ;

-- -----------------------------------------------------
-- Table `challenge`.`survey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `challenge`.`survey` (
  `survey_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`survey_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `challenge`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `challenge`.`question` (
  `question_id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NOT NULL,
  `survey_id` INT NOT NULL,
  PRIMARY KEY (`question_id`),
  INDEX `fk_question_survey_id_idx` (`survey_id` ASC) VISIBLE,
  CONSTRAINT `fk_question_survey_id`
    FOREIGN KEY (`survey_id`)
    REFERENCES `challenge`.`survey` (`survey_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `challenge`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `challenge`.`answer` (
  `answer_id` INT NOT NULL AUTO_INCREMENT,
  `valor` VARCHAR(45) NOT NULL,
  `question_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  INDEX `fk_option_question_id_idx` (`question_id` ASC) VISIBLE,
  CONSTRAINT `fk_option_question_id`
    FOREIGN KEY (`question_id`)
    REFERENCES `challenge`.`question` (`question_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `challenge`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `challenge`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(200) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `create_date` DATETIME NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `challenge`.`user_answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `challenge`.`user_answer` (
  `user_answer_id` INT NOT NULL AUTO_INCREMENT,
  `answer_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`user_answer_id`),
  INDEX `fk_user_response_option_id_idx` (`answer_id` ASC) VISIBLE,
  INDEX `fk_user_response_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_answer_answer_id`
    FOREIGN KEY (`answer_id`)
    REFERENCES `challenge`.`answer` (`answer_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_response_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `challenge`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
