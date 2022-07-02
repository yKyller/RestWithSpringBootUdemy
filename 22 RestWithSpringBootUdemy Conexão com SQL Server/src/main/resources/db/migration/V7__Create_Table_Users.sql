USE [rest_with_spring_boot_udemy]
GO

CREATE TABLE dbo.users (
  [id] bigint NOT NULL IDENTITY,
  [user_name] varchar(255) DEFAULT NULL,
  [full_name] varchar(255) DEFAULT NULL,
  [password] varchar(255) DEFAULT NULL,
  [account_non_expired] BIT NOT NULL DEFAULT 'TRUE',
  [account_non_locked] BIT NOT NULL DEFAULT 'TRUE',
  [credentials_non_expired] BIT NOT NULL DEFAULT 'TRUE',
  [enabled] BIT NOT NULL DEFAULT 'TRUE',
  PRIMARY KEY ([id]),
  CONSTRAINT [uk_user_name] UNIQUE  ([user_name])
)  