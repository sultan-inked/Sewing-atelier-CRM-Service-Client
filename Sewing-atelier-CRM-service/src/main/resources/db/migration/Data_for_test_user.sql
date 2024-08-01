-- Loadin data for the test
INSERT INTO crm_user.t_roles (name)
VALUES
('ROLE_USER'), ('ROLE_ADMIN');

-- password = 100
INSERT INTO crm_user.t_users (username, password, email)
VALUES
('test_user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'test_user@mail.com'),
('test_admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'test_admin@mail.com');

INSERT INTO crm_user.t_users_roles (user_id, role_id)
VALUES
(1, 1),
(2, 2);

UPDATE crm_user.t_users
   SET password = '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'
 WHERE id = 1;

-- Load data of orders and customers
INSERT INTO crm_user.t_customers (name, phone_number, description)
VALUES
('Александра', '+77753426574', 'Спокойная'),
('Виталий', '+77716550303', 'Требовательный');

INSERT INTO crm_user.t_orders (name, description, price, payment, created_at, dead_line_date, payment_at)
VALUES
('Подгонка брюк', 
 'Классические черные брюки', 
 '3000', 
 '3000', 
 '2024-07-28 13:06:56.462863', 
 '2024-07-28', 
 '2024-07-28 17:14:32.462863'),
 ('Ушить', 
 'Ушить рубашку, в клетку', 
 '8000', 
 '8000', 
 '2024-07-28 14:26:56.462863', 
 '2024-07-29', 
 '2024-07-28 14:26:56.462863');


















