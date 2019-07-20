create table mvc_board_reply(
	reply_no int primary key auto_increment,
	board_no int not null default 0,
	reply_text varchar(1000) not null,
	reply_writer varchar(50) not null,
	reg_date timestamp not null default now(),
	update_date timestamp not null default now()
);

-- 댓글 참조키 설정
alter table mvc_board_reply add CONSTRAINT fk_board
foreign key (board_no) references mvc_board (board_no);

select * from mvc_board where board_no = 2998;

select * from mvc_board_reply where board_no = 2998 order by reply_no desc;