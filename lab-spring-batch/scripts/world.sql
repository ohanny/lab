--
-- Drop tables
--
drop table if exists city;
drop table if exists country_language;
drop table if exists country;

--
-- Table structure for table 'country'
--
create table country (
  code char(3) not null default '',
  name char(52) not null default '',
  continent enum('Asia','Europe','North America','Africa','Oceania','Antarctica','South America') not null default 'Asia',
  region char(26) not null default '',
  surface_area float(10,2) not null default '0.00',
  indep_year smallint(6) default null,
  population integer not null default '0',
  life_expectancy float(3,1) default null,
  primary key (code)
) engine=InnoDB;

--
-- Table structure for table 'city'
--
create table city (
  id integer not null auto_increment,
  name char(35) not null default '',
  country_code char(3) not null default '',
  district char(20) not null default '',
  population integer not null default '0',
  primary key (id),
  key country_code (country_code),
  constraint city_ibfk_1 foreign key (country_code) references country (code)
) engine=InnoDB;

--
-- Table structure for table country_language
--
create table country_language (
  country_code char(3) not null default '',
  language char(30) not null default '',
  is_official enum('T','F') not null default 'F',
  percentage float(4,1) not null default '0.0',
  primary key (country_code, language),
  key country_code (country_code),
  constraint country_language_ibfk_1 foreign key (country_code) references country (code)
) engine=InnoDB;


