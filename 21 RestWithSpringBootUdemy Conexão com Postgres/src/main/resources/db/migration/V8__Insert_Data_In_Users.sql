﻿INSERT INTO users (user_name, full_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES
    ('felippe', 'Felippe Amaral', '$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS', TRUE, TRUE, TRUE, TRUE),
    ('kyller', 'Kyller', '$2a$16$h4yDQCYTy62R6xrtFDWONeMH3Lim4WQuU/aj8hxW.dJJoeyvtEkhK', TRUE, TRUE, TRUE, TRUE);
    
ALTER SEQUENCE users_seq RESTART WITH 3;