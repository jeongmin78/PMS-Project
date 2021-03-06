INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (200, 'SYSTEM_MANAGER', '예약', '{"ko":"예약","en":"Reservation"}',  null, 0, 99, null, sysdate(), 'system', sysdate(), 'system');
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('reservation-registration', '예약등록', '/jsp/reservation/reservation-registration.jsp', '_self', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (201, 'SYSTEM_MANAGER', '예약등록', '{"ko":"예약등록","en":"Reservation Registration"}',  200, 1, 1, 'reservation-registration', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '201',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH, DEL_AH )
VALUES ('reservation-current-state', '예약현황', '/jsp/reservation/reservation-current-state.jsp', '_self', 'Y', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (202, 'SYSTEM_MANAGER', '예약현황', '{"ko":"예약현황","en":"Reservation Current State"}',  200, 1, 2, 'reservation-current-state', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, DEL_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '202',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system');
--

INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (300, 'SYSTEM_MANAGER', '프론트', '{"ko":"프론트","en":"Front"}',  null, 0, 99, null, sysdate(), 'system', sysdate(), 'system');
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('check-in', '체크인', '/jsp/front/check-in.jsp', '_self', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (301, 'SYSTEM_MANAGER', '체크인', '{"ko":"체크인","en":"Check-In"}',  300, 1, 1, 'check-in', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '301',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH, DEL_AH )
VALUES ('in-house', '인하우스', '/jsp/front/in-house.jsp', '_self', 'Y', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (302, 'SYSTEM_MANAGER', '인하우스', '{"ko":"인하우스","en":"In-House"}',  300, 1, 2, 'in-house', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, DEL_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '302',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system');
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH, DEL_AH )
VALUES ('check-out', '체크아웃', '/jsp/front/check-out.jsp', '_self', 'Y', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (303, 'SYSTEM_MANAGER', '체크아웃', '{"ko":"체크아웃","en":"Check-Out"}',  300, 1, 3, 'check-out', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, DEL_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '303',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system');

---------------------

INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (400, 'SYSTEM_MANAGER', '기준정보', '{"ko":"기준정보","en":"Information"}',  null, 0, 99, null, sysdate(), 'system', sysdate(), 'system');
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('Room Info', '객실정보', '/jsp/information/room-information.jsp', '_self', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (401, 'SYSTEM_MANAGER', '객실정보', '{"ko":"객실정보","en":"Room Info"}',  400, 1, 1, 'Room Info', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '401',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH, DEL_AH )
VALUES ('Guest Info', '투숙객정보', '/jsp/information/guest-information.jsp', '_self', 'Y', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (402, 'SYSTEM_MANAGER', '투숙객정보', '{"ko":"투숙객정보","en":"Guest Info"}',  400, 1, 2, 'Guest Info', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, DEL_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '402',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system');

---------------------

INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (500, 'SYSTEM_MANAGER', '보고서', '{"ko":"보고서","en":"report"}',  null, 0, 99, null, sysdate(), 'system', sysdate(), 'system');
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH, DEL_AH )
VALUES ('sales-status', '매출현황', '/jsp/report/sales-status.jsp', '_self', 'Y', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (501, 'SYSTEM_MANAGER', '매출현황', '{"ko":"매출현황","en":"sales-status"}',  500, 1, 1, 'sales-status', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, DEL_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '501',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system');

---------------------

INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (600, 'SYSTEM_MANAGER', '공통코드2', '{"ko":"공통코드2","en":"common-code2"}',  null, 0, 99, null, sysdate(), 'system', sysdate(), 'system');
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH, DEL_AH )
VALUES ('common-code2', '공통코드2', '/jsp/common-code/common-code.jsp', '_self', 'Y', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (601, 'SYSTEM_MANAGER', '공통코드2', '{"ko":"공통코드2","en":"common-code2"}',  600, 1, 1, 'common-code2', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, DEL_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '601',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system');
