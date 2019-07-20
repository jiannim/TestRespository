create table mvc_user(
	user_id char(100) primary key,
	user_pw char(100) not null,
	user_name char(120) not null,
	nuer_reg_date timestamp default now()
);

-- 컬럼 추가
alter table mvc_user add column session_id char(50) not null default 'none';
alter tablemvc_user add column session_limit timestamp;