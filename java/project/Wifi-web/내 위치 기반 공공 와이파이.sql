CREATE TABLE `tb_public_wifi` (
	`x_swifi_merg_no`	VARCHAR(20)	NOT NULL	COMMENT '관리번호',
	`x_swifi_wrdofc`	VARCHAR(10)	NOT NULL	DEFAULT ''	COMMENT '자치구',
	`x_swifi_main_nm`	VARCHAR(50)	NOT NULL	DEFAULT ''	COMMENT '와이파이명',
	`x_swifi_adres1`	VARCHAR(100)	NOT NULL	DEFAULT ''	COMMENT '도로명주소',
	`x_swifi_adres2`	VARCHAR(100)	NOT NULL	DEFAULT ''	COMMENT '상세주소',
	`x_swifi_instl_floor`	VARCHAR(20)	NOT NULL	DEFAULT ''	COMMENT '설치위치',
	`x_swifi_instl_ty`	VARCHAR(50)	NOT NULL	DEFAULT ''	COMMENT '설치유형',
	`x_swifi_instl_mby`	VARCHAR(20)	NOT NULL	DEFAULT ''	COMMENT '설치기관',
	`x_swifi_svc_se`	VARCHAR(20)	NOT NULL	DEFAULT ''	COMMENT '서비스 구분',
	`x_swifi_cmcwr`	VARCHAR(20)	NOT NULL	DEFAULT ''	COMMENT '망종류',
	`x_swifi_cnstc_year`	INT UNSIGNED	NOT NULL	DEFAULT 0	COMMENT '설치년도',
	`x_swifi_inout_door`	CHAR(2)	NOT NULL	DEFAULT ''	COMMENT '실내외구분',
	`x_swifi_remars3`	VARCHAR(100)	NOT NULL	DEFAULT ''	COMMENT '와이파이접속환경',
	`lnt`	DECIMAL(10,6)	NOT NULL	DEFAULT 0.00000	COMMENT 'x좌표',
	`lat`	DECIMAL(10,6)	NOT NULL	DEFAULT 0.00000	COMMENT 'y좌표',
	`work_dttm`	DATETIME(6)	NOT NULL	COMMENT '작업일자'
);

CREATE TABLE `group_bookmark` (
	`id`	INT(32)	NOT NULL	COMMENT '순번',
	`order`	INT UNSIGNED	NULL,
	`name`	VARCHAR(50)	NOT NULL	DEFAULT ''	COMMENT '북마크그룹이름',
	`updated_at`	DATETIME(6)	NOT NULL	COMMENT '수정일자',
	`created_at`	DATETIME(6)	NOT NULL	COMMENT '등록일자'
);

CREATE TABLE `bookmark` (
	`id`	INT(32)	NOT NULL,
	`group_bookmark_id`	INT(32)	NOT NULL	COMMENT '순번',
	`x_swifi_main_nm`	VARCHAR(50)	NOT NULL	DEFAULT ''	COMMENT '와이파이명',
	`created_at`	DATETIME(6)	NOT NULL	COMMENT '등록일자'
);

CREATE TABLE `location_history` (
	`id`	INT(32)	NOT NULL	COMMENT '순번',
	`lnt`	DECIMAL(10,6)	NOT NULL	DEFAULT 0.00000	COMMENT 'x좌표',
	`lat`	DECIMAL(10,6)	NOT NULL	DEFAULT 0.00000	COMMENT 'y좌표',
	`created_at`	DATETIME(6)	NOT NULL	COMMENT '생성일자'
);

ALTER TABLE `tb_public_wifi` ADD CONSTRAINT `PK_TB_PUBLIC_WIFI` PRIMARY KEY (
	`x_swifi_merg_no`
);

ALTER TABLE `group_bookmark` ADD CONSTRAINT `PK_GROUP_BOOKMARK` PRIMARY KEY (
	`id`
);

ALTER TABLE `bookmark` ADD CONSTRAINT `PK_BOOKMARK` PRIMARY KEY (
	`id`,
	`group_bookmark_id`
);

ALTER TABLE `location_history` ADD CONSTRAINT `PK_LOCATION_HISTORY` PRIMARY KEY (
	`id`
);

ALTER TABLE `bookmark` ADD CONSTRAINT `FK_group_bookmark_TO_bookmark_1` FOREIGN KEY (
	`group_bookmark_id`
)
REFERENCES `group_bookmark` (
	`id`
);

