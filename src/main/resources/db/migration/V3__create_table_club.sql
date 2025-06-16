CREATE TABLE club (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    state_id BIGINT(20) NOT NULL,
    creation_date DATE NOT NULL,
    active TINYINT(1) NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_club_state_idx (state_id ASC),
    CONSTRAINT fk_club_state
                  FOREIGN KEY (state_id)
                  REFERENCES state (id)
                  ON DELETE NO ACTION
                  ON UPDATE NO ACTION
) ENGINE = InnoDB;