CREATE TABLE account (
	id int8 NOT NULL AUTO_INCREMENT,
	account_number varchar(255) NULL,
	balance float8 NULL,
	created_date timestamp NOT NULL,
	name varchar(255) NULL,
	updated_date timestamp NULL,
	version int8 NOT NULL DEFAULT 0,
	CONSTRAINT account_pkey PRIMARY KEY (id),
	CONSTRAINT uk66gkcp94endmotfwb8r4ocxm9 UNIQUE (account_number)
);


CREATE TABLE transaction_details (
	id int8 NOT NULL AUTO_INCREMENT,
	created_date timestamp NOT NULL,
	description varchar(255) NULL,
	transaction_amount float8 NULL,
	transaction_type varchar(255) NULL,
	account_id int8 NULL,
	CONSTRAINT transaction_details_pkey PRIMARY KEY (id)
);

ALTER TABLE transaction_details ADD CONSTRAINT fko3x1igfsdeatasw52igygbik6 FOREIGN KEY (account_id) REFERENCES public.account(id);

INSERT INTO account (id, account_number,balance,name,created_date) VALUES
	 (1,'1001',20.0,'TestUser1',now()),
	 (2,'1002',80.0,'TestUser2',now());

INSERT INTO transaction_details (account_id, transaction_type, transaction_amount,description,created_date) VALUES
	 (1,'Credit',20.0, 'Opening Balance',now()),
	 (2,'Credit',80.0, 'Opening Balance', now());