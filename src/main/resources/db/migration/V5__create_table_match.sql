CREATE TABLE match_table (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    home_club_id BIGINT NOT NULL,
    away_club_id BIGINT NOT NULL,
    home_goals INT NOT NULL,
    away_goals INT NOT NULL,
    stadium_id BIGINT NOT NULL,
    match_datetime TIMESTAMP NOT NULL,
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
);

CREATE INDEX fk_match_home_club_idx ON match_table (home_club_id);
CREATE INDEX fk_match_away_club_idx ON match_table (away_club_id);
CREATE INDEX fk_match_stadium_idx ON match_table (stadium_id);