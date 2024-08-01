create schema if not exists crm_user;

create table crm_user.t_customers
(
	          id BIGSERIAL,
	        name VARCHAR(100) NOT NULL UNIQUE,
	phone_number VARCHAR(80) NOT NULL,
	 description VARCHAR(500),
                 PRIMARY KEY (id)
);

create table crm_user.t_orders
(
	id	bigserial,
	name	varchar(200) not null,
	description	varchar(500),
	price	int,
	payment	int,
	created_at	timestamp,
	dead_line_date	date,
	payment_at	timestamp,
	primary key (id)
);

create table crm_user.orders_users_customers
(
	order_id	bigserial not null,
	user_id		int not null,
	customer_id	bigserial not null,
	primary key (order_id, user_id, customer_id),
	foreign key (order_id) 		references crm_user.t_orders (id),
	foreign key (user_id) 		references crm_user.t_users (id),
	foreign key (customer_id) 	references crm_user.t_customers (id)
);





