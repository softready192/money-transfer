CREATE TABLE account (
	id int8 NOT NULL,
	account_number varchar(255) NULL,
	balance float8 NULL,
	created_date timestamp NULL,
	name varchar(255) NULL,
	updated_date timestamp NULL,
	"version" int8 NULL,
	CONSTRAINT account_pkey PRIMARY KEY (id),
	CONSTRAINT uk66gkcp94endmotfwb8r4ocxm9 UNIQUE (account_number)
);


CREATE TABLE transaction_details (
	id int8 NOT NULL,
	created_date timestamp NOT NULL,
	description varchar(255) NULL,
	transaction_amount float8 NULL,
	transaction_type varchar(255) NULL,
	account_id int8 NULL,
	CONSTRAINT transaction_details_pkey PRIMARY KEY (id)
);

ALTER TABLE transaction_details ADD CONSTRAINT fko3x1igfsdeatasw52igygbik6 FOREIGN KEY (account_id) REFERENCES public.account(id);

INSERT INTO account (id,account_number,balance,name) VALUES
	 (1,'1001',20.0,'TestUser1'),
	 (2,'1002',80.0,'TestUser2');