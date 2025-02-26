INSERT INTO user (id, user_name, last_name, first_name, email, password, date_created, date_updated, created_by, last_modified_by, role) VALUES
(1, 'admin123', 'Nguyen', 'Admin', 'admin@gmail.com', 'admin123', NOW(), NOW(), 'System', 'System', 'ADMIN'),
(2, 'user123', 'Nguyen', 'User', 'user@gmail.com', 'user123', NOW(), NOW(), 'System', 'System', 'USER');
