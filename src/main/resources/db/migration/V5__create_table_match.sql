CREATE TABLE `match` (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    home_club_id BIGINT(20) NOT NULL,
    away_club_id BIGINT(20) NOT NULL,
    home_goals INT NOT NULL,
    away_goals INT NOT NULL,
    stadium_id BIGINT(20) NOT NULL,
    match_datetime DATETIME NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_match_home_club_idx (home_club_id ASC),
    INDEX fk_match_away_club_idx (away_club_id ASC),
    INDEX fk_match_stadium_idx (stadium_id ASC),
    CONSTRAINT fk_match_home_club
                   FOREIGN KEY (home_club_id)
                   REFERENCES club (id)
                   ON DELETE NO ACTION
                   ON UPDATE NO ACTION,
    CONSTRAINT fk_match_away_club
                   FOREIGN KEY (away_club_id)
                   REFERENCES club (id)
                   ON DELETE NO ACTION
                   ON UPDATE NO ACTION,
    CONSTRAINT fk_match_stadium
                   FOREIGN KEY (stadium_id)
                   REFERENCES stadium (id)
                   ON DELETE NO ACTION
                   ON UPDATE NO ACTION
) ENGINE = InnoDB;