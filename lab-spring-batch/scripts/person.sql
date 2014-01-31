create table person (
	id integer not null auto_increment,
	first_name varchar(10) not null,
	last_name varchar(10) not null,
	age integer not null,
	primary key(id)) engine=InnoDB;
