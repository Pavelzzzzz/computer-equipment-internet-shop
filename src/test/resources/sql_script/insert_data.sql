INSERT INTO tblSECUser (`UserId`, `Login`, `Email`, `IsActive`)
    VALUES ('1', 'user', 'user@mail.ru', '1');

INSERT INTO tblSECRole (`RoleId`, `Role`)
    VALUES ('1', 'Admin');

INSERT INTO tblSECUserRole (`UserId`, `RoleId`)
    VALUES ('1', '1');
--
INSERT INTO tblSECPassword (`UserId`, `Password`)
    VALUES ('1', '123456789');

INSERT INTO tblSECCategory (`CategoryId`, `Category`)
VALUES ( '1', 'Phone');

INSERT INTO tblSECProduct (`ProductId`, `CategoryId`, `Title`, `CostInteger`, `CostFractional`, `Text`)
VALUES ('1', '1', 'Title',  '15', '10', 'Some very interesting text');