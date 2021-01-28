insert into department(name) values
  ('経理部'),
  ('企画部'),
  ('開発部')
;

insert into asset(name, status_code) values
  ('coin2020',1)
;

insert into employee(name, password, dep_id, asset_quantity, status_code, priv_hex, pub_hex) values
--  ('admin@test','password',1,123,1,'f101537e319568c765b2cc89698325604991dca57b9716b58016b253506cab70','313a07e6384776ed95447710d15e59148473ccfc052a681317a72a69f2a49910'),
  ('test@test','password',1,500,1,'7e00405ece477bb6dd9b03a78eee4e708afc2f5bcdce399573a5958942f4a390','716fe505f69f18511a1b083915aa9ff73ef36e6688199f3959750db38b8f4bfc')
;