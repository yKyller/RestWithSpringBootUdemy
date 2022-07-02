USE [rest_with_spring_boot_udemy]
GO

CREATE TABLE dbo.books (
  [id] int NOT NULL IDENTITY,
  [author] varchar(max),
  [launch_date] datetime2(6) NOT NULL,
  [price] decimal(38,2) NOT NULL,
  [title] varchar(max),
  PRIMARY KEY ([id])
) 