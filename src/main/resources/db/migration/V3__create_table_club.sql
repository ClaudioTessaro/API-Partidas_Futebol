CREATE TABLE club (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(100) NOT NULL,
    state_id BIGINT NOT NULL,
    creation_date DATE NOT NULL,
    active BOOLEAN NOT NULL,
    CONSTRAINT fk_club_state
                  FOREIGN KEY (state_id)
                  REFERENCES state (id)
                  ON DELETE NO ACTION
                  ON UPDATE NO ACTION
);

CREATE INDEX fk_club_state_idx ON club (state_id);