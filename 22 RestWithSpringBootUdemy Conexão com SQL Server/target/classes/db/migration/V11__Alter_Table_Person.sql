USE [rest_with_spring_boot_udemy]
GO

ALTER TABLE dbo.person
    ADD [enabled] BIT NOT NULL DEFAULT 'TRUE';