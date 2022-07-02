USE [rest_with_spring_boot_udemy]
GO

CREATE TABLE dbo.user_permission (
  [id_user] bigint NOT NULL,
  [id_permission] bigint NOT NULL,
  PRIMARY KEY ([id_user],[id_permission])
 ,
  CONSTRAINT [fk_user_permission] FOREIGN KEY ([id_user]) REFERENCES users ([id]),
  CONSTRAINT [fk_user_permission_permission] FOREIGN KEY ([id_permission]) REFERENCES permission ([id])
) 
GO

CREATE INDEX [fk_user_permission_permission] ON user_permission ([id_permission])