-- 기본 게시판 테이블
CREATE TABLE mvc_board (
	board_no INT AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(200) NOT NULL,
	content TEXT NULL,
	writer VARCHAR(50) NOT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	view_cnt INT DEFAULT 0
);

-- 게시판에 댓글 수 컬럼 추가
alter table mvc_board add column reply_cnt int default 0;





