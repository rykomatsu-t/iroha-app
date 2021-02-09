create table department (
  id serial primary key,
  name varchar(200) not null
);

create table employee (
  id serial primary key,
  name varchar(200) not null,
  password varchar(200) not null,
  dep_id int references department(id),
  asset_quantity int,
  status_code int,
  priv_hex varchar(200),
  pub_hex varchar(200)
);

create table asset (
  id serial primary key,
  name varchar(200) not null,
  status_code int not null
);

create table history (
  id serial primary key,
  send_time timestamp not null,
  message varchar(200),
  asset_id int not null references asset(id),
  send_id int not null references employee(id),
  receive_id int not null references employee(id),
  val real not null
);