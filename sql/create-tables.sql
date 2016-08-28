-- -----------------------------------------------------
-- Table `dbcassem`.`car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbcassem`.`car` (
  `car_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `price` DECIMAL NOT NULL,
  PRIMARY KEY (`car_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbcassem`.`part`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbcassem`.`part` (
  `part_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `stock` INT NOT NULL,
  `max_stock` VARCHAR(45) NOT NULL,
  `provider` VARCHAR(100) NOT NULL,
  `price` REAL NOT NULL,
  `category` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`part_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbcassem`.`car_has_part`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbcassem`.`car_has_part` (
  `car_has_part_id` INT NOT NULL AUTO_INCREMENT,
  `car_id` INT NOT NULL,
  `part_id` INT NOT NULL,
  PRIMARY KEY (`car_has_part_id`),
  INDEX `fk_car_has_part_part1_idx` (`part_id` ASC),
  INDEX `fk_car_has_part_car_idx` (`car_id` ASC),
  CONSTRAINT `fk_car_has_part_car`
    FOREIGN KEY (`car_id`)
    REFERENCES `dbcassem`.`car` (`car_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_car_has_part_part1`
    FOREIGN KEY (`part_id`)
    REFERENCES `dbcassem`.`part` (`part_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbcassem`.`auth`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbcassem`.`auth` (
  `username` VARCHAR(25) NOT NULL,
  `password` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbcassem`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbcassem`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(100) NOT NULL,
  `username` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_user_auth1_idx` (`username` ASC),
  CONSTRAINT `fk_user_auth1`
    FOREIGN KEY (`username`)
    REFERENCES `dbcassem`.`auth` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbcassem`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbcassem`.`client` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`client_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbcassem`.`sale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbcassem`.`sale` (
  `sale_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `client_id` INT NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`sale_id`, `user_id`, `client_id`),
  INDEX `fk_sale_buyer1_idx` (`client_id` ASC),
  INDEX `fk_sale_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_sale_buyer1`
    FOREIGN KEY (`client_id`)
    REFERENCES `dbcassem`.`client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sale_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `dbcassem`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbcassem`.`sale_has_car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbcassem`.`sale_has_car` (
  `individual_sale_id` INT NOT NULL AUTO_INCREMENT,
  `car_id` INT NOT NULL,
  `sale_id` INT NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`individual_sale_id`, `car_id`, `sale_id`),
  INDEX `fk_individual_sale_car1_idx` (`car_id` ASC),
  INDEX `fk_individual_sale_sale1_idx` (`sale_id` ASC),
  CONSTRAINT `fk_individual_sale_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `dbcassem`.`car` (`car_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_individual_sale_sale1`
    FOREIGN KEY (`sale_id`)
    REFERENCES `dbcassem`.`sale` (`sale_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbcassem`.`employee_decrease_part`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbcassem`.`employee_decrease_part` (
  `decrease_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `part_id` INT NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`decrease_id`),
  INDEX `fk_employee_has_part_part1_idx` (`part_id` ASC),
  INDEX `fk_employee_decrease_part_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_employee_has_part_part1`
    FOREIGN KEY (`part_id`)
    REFERENCES `dbcassem`.`part` (`part_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_decrease_part_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `dbcassem`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
