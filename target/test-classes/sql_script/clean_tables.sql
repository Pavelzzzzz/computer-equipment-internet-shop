ALTER TABLE tblSECPassword
    DROP CONSTRAINT Password_UserId_FK;

ALTER TABLE tblSECUserRole
    DROP CONSTRAINT UserRole_UserId_FK;
ALTER TABLE tblSECUserRole
    DROP CONSTRAINT UserRole_RoleId_FK;

ALTER TABLE tblSECProduct
    DROP CONSTRAINT Product_CategoryId_FK;

ALTER TABLE tblSECOrder
    DROP CONSTRAINT Order_ProductId_FK;

ALTER TABLE tblSECOrder
    DROP CONSTRAINT Order_UserId_FK;

TRUNCATE TABLE tblSECPassword;

TRUNCATE TABLE tblSECUserRole;

TRUNCATE TABLE tblSECUser;

TRUNCATE TABLE tblSECRole;

TRUNCATE TABLE tblSECProduct;

TRUNCATE TABLE tblSECCategory;

TRUNCATE TABLE tblSECOrder;

ALTER TABLE tblSECUserRole
    ADD CONSTRAINT UserRole_UserId_FK
FOREIGN KEY (UserId)
REFERENCES tblSECUser(UserId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE tblSECUserRole
    ADD CONSTRAINT UserRole_RoleId_FK
FOREIGN KEY (RoleId)
REFERENCES tblSECRole(RoleId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE tblSECPassword
    ADD CONSTRAINT Password_UserId_FK
FOREIGN KEY (UserId)
REFERENCES tblSECUser(UserId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE tblSECProduct
    ADD CONSTRAINT Product_CategoryId_FK
FOREIGN KEY (CategoryId)
REFERENCES tblSECCategory(CategoryId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE tblSECOrder
    ADD CONSTRAINT Order_ProductId_FK
FOREIGN KEY (ProductId)
REFERENCES tblSECProduct(ProductId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE tblSECOrder
    ADD CONSTRAINT Order_UserId_FK
FOREIGN KEY (UserId)
REFERENCES tblSECUser(UserId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
