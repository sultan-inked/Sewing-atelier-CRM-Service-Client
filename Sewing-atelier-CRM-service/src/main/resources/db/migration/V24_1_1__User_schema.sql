create schema if not exists crm_user;

create table crm_user.t_users
(
	id			bigserial,
	username	varchar(30) not null unique,
	password	varchar(80) not null,
	email		varchar(50) unique,
	primary key (id)
);

create table crm_user.t_roles
(
	id		serial,
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














