USE [rest_with_spring_boot_udemy]
GO

CREATE TABLE dbo.person (
  [id] bigint NOT NULL IDENTITY,
  [address] varchar(100) NOT NULL,
  [first_name] varchar(80) NOT NULL,
  [gender] varchar(6) NOT NULL,
  [last_name] varchar(80) NOT NULL,
  PRIMARY KEY ([id])
)