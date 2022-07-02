USE [rest_with_spring_boot_udemy]
GO

CREATE TABLE dbo.permission (
  [id] bigint NOT NULL IDENTITY,
  [description] varchar(255) DEFAULT NULL,
  PRIMARY KEY ([id])
)  