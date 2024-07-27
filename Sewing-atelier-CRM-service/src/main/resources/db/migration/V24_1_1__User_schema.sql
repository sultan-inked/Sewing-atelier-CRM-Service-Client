create schema if not exists crm_user;

create table crm_user.t_users
(
	id	bigserial,
	username	varchar(30) not null unique,
	password	varchar(80) not null,
	email	varchar(50) unique,
	primary key (id)
);

create table crm_user.t_roles
(
	id	serial,
	name	varchar(50) not null,
	primary key (id)
);

create table crm_user.t_users_roles
(
	user_id		bigint not null,
	role_id		int not null,
	primary key (user_id, role_id),
	foreign key (user_id) references crm_user.t_users (id),
	foreign key (role_id) references crm_user.t_roles (id)
);

-- Loadin data for the test
insert into crm_user.t_roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

-- password = 100
insert into crm_user.t_users (username, password, email)
values
('test_user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'test_user@mail.com'),
('test_admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'test_admin@mail.com');

insert into crm_user.t_users_roles (user_id, role_id)
values
(1, 1),
(2, 2);

update crm_user.t_users
set password = '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'
where id = 1;












