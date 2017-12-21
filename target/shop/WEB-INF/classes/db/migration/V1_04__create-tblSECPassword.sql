CREATE TABLE IF NOT EXISTS tblSECPassword (
  `UserId` INT NOT NULL,
  `Password` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`UserId`));

ALTER TABLE tblSECPassword
  ADD CONSTRAINT Password_UserId_FK
FOREIGN KEY (UserId)
REFERENCES tblSECUser(UserId)
  ON DELETE CASCADE
  ON UPDATE CASCADE;