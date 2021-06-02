-- 초기 데이터 : PMS 사용 공통코드 ########################################
--DELETE FROM COMMON_CODE_M WHERE GROUP_CD LIKE 'PMS_%';
INSERT INTO COMMON_CODE_M (GROUP_CD, GROUP_NM, CODE, NAME, SORT, USE_YN, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
-- 객실정보 - 객실타입
SELECT 'PMS_ROOM_TYPE', '객실타입',  'SB', 'Single Bed', 1, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_ROOM_TYPE', '객실타입',  'DB', 'Double Bed', 2, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_ROOM_TYPE', '객실타입',  'DT', 'Double Twin Bed', 3, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
-- 객실정보 - 객실상태
SELECT 'PMS_ROOM_STATUS', '객실상태',  'EMT', '공실', 1, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_ROOM_STATUS', '객실상태',  'IN', '재실', 2, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_ROOM_STATUS', '객실상태',  'OUT', '외출', 3, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
-- 객실정보 - 청소상태
SELECT 'PMS_CLEAN_STATUS', '청소상태',  'VD', '청소대기', 1, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_CLEAN_STATUS', '청소상태',  'VW', '청소중', 2, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_CLEAN_STATUS', '청소상태',  'VC', '청소완료', 3, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
-- 객실정보 - 서비스상태
SELECT 'PMS_SVC_STATUS', '서비스상태',  'OOO', 'Out Of Order', 1, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_SVC_STATUS', '서비스상태',  'OOS', 'Out Of Service', 2, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL

-- 예약등록 - 판매유형
SELECT 'PMS_SALE_TYPE', '판매유형',  '01', '부킹예약', 1, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_SALE_TYPE', '판매유형',  '02', '워크인', 2, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_SALE_TYPE', '판매유형',  '03', '대실', 3, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_SALE_TYPE', '판매유형',  '04', '컴프', 4, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_SALE_TYPE', '판매유형',  '05', '하우스유즈', 5, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
-- 예약등록 - 예약경로
SELECT 'PMS_RESERVATION_ROUTE', '예약경로',  '01', '홈페이지', 1, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_RESERVATION_ROUTE', '예약경로',  '02', '메일', 2, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_RESERVATION_ROUTE', '예약경로',  '03', '전화', 3, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_RESERVATION_ROUTE', '예약경로',  '04', '팩스', 4, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_RESERVATION_ROUTE', '예약경로',  '05', '방문', 5, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_RESERVATION_ROUTE', '예약경로',  '06', '온라인', 6, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_RESERVATION_ROUTE', '예약경로',  '07', '기타', 7, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
-- 예약등록 - 결재방법
SELECT 'PMS_PAY_METHOD', '결재방법',  'CASH', '현금', 1, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_PAY_METHOD', '결재방법',  'CREDIT', '신용카드', 2, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_PAY_METHOD', '결재방법',  'BANK', '계좌이체', 3, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_PAY_METHOD', '결재방법',  'EXTERNAL_POST_PAYMENT', '대외후불', 4, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL

-- 예약등록 - 상태
SELECT 'PMS_STAY_STATUS', '투숙상태',  'RSV_01', '예약', 1, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_STAY_STATUS', '투숙상태',  'RSV_02', '예약대기', 2, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_STAY_STATUS', '투숙상태',  'RSV_03', '예약확정', 3, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_STAY_STATUS', '투숙상태',  'RSV_04', '예약취소', 4, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_STAY_STATUS', '투숙상태',  'RSV_05', '노쇼', 5, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_STAY_STATUS', '투숙상태',  'CHK_01', '체크인', 6, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_STAY_STATUS', '투숙상태',  'CHK_02', '체크아웃', 7, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_STAY_STATUS', '투숙상태',  'CHK_03', '체크인취소', 8, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL

-- 투숙객정보 - 언어
SELECT 'PMS_LANG', '언어',  'KO', '한국어', 1, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_LANG', '언어',  'EN', '영어', 2, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_LANG', '언어',  'CH', '중국어', 3, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'PMS_LANG', '언어',  'JP', '일본어', 4, 'Y', sysdate(), 'system', sysdate(), 'system';

-- LITE PMS
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH, EXL_AH )
-- 기준정보
SELECT 'base.room', '객실정보', '/jsp/base/room.jsp', '_self', 'Y', 'Y', 'Y', 'N' UNION ALL
SELECT 'base.guest', '투숙객정보', '/jsp/base/guest.jsp', '_self', 'Y', 'Y', 'Y', 'Y' UNION ALL
-- 예약
SELECT 'reservation.registration', '예약등록', '/jsp/reservation/registration.jsp', '_self', 'Y', 'Y', 'Y', 'N' UNION ALL
SELECT 'reservation.status', '예약현황', '/jsp/reservation/status.jsp', '_self', 'Y', 'Y', 'Y', 'Y' UNION ALL
-- 프론트
SELECT 'front.check-in', '체크인', '/jsp/front/check-in.jsp', '_self', 'Y', 'Y', 'Y', 'Y' UNION ALL
SELECT 'front.in-house', '인하우스', '/jsp/front/in-house.jsp', '_self', 'Y', 'Y', 'Y', 'Y' UNION ALL
SELECT 'front.check-out', '체크아웃', '/jsp/front/check-out.jsp', '_self', 'Y', 'Y', 'Y', 'Y' UNION ALL
-- 보고서
SELECT 'report.sales-status', '매출현황', '/jsp/report/sales-status.jsp', '_self', 'Y', 'Y', 'Y', 'Y';

-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
-- 기준정보
SELECT 1100, 'SYSTEM_MANAGER', '기준정보', '{"ko":"기준정보","en":"Base Info"}',  null, 0, 100, null, sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 1101, 'SYSTEM_MANAGER', '객실정보', '{"ko":"객실정보","en":"객실정보"}',  1100, 1, 1, 'base.room', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 1102, 'SYSTEM_MANAGER', '투숙객정보', '{"ko":"투숙객정보","en":"투숙객정보"}',  1100, 1, 2, 'base.guest', sysdate(), 'system', sysdate(), 'system' UNION ALL
-- 예약
SELECT 1200, 'SYSTEM_MANAGER', '예약', '{"ko":"예약","en":"Reservation"}',  null, 0, 200, null, sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 1201, 'SYSTEM_MANAGER', '예약등록', '{"ko":"예약등록","en":"예약등록"}',  1200, 1, 1, 'reservation.registration', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 1202, 'SYSTEM_MANAGER', '예약현황', '{"ko":"예약현황","en":"예약현황"}',  1200, 1, 2, 'reservation.status', sysdate(), 'system', sysdate(), 'system' UNION ALL
-- 프론트
SELECT 1300, 'SYSTEM_MANAGER', '프론트', '{"ko":"프론트","en":"Front"}',  null, 0, 300, null, sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 1301, 'SYSTEM_MANAGER', '체크인', '{"ko":"체크인","en":"체크인"}',  1300, 1, 1, 'front.check-in', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 1302, 'SYSTEM_MANAGER', '인하우스', '{"ko":"인하우스","en":"인하우스"}',  1300, 1, 2, 'front.in-house', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 1303, 'SYSTEM_MANAGER', '체크아웃', '{"ko":"체크아웃","en":"체크아웃"}',  1300, 1, 3, 'front.check-out', sysdate(), 'system', sysdate(), 'system' UNION ALL
-- 보고서
SELECT 1400, 'SYSTEM_MANAGER', '보고서', '{"ko":"보고서","en":"Report"}',  null, 0, 400, null, sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 1401, 'SYSTEM_MANAGER', '매출현황', '{"ko":"매출현황","en":"매출현황"}',  1400, 1, 1, 'report.sales-status', sysdate(), 'system', sysdate(), 'system';

-- 메뉴 권한 할당 - 개발 테스트용
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, EXL_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
-- 기준정보
SELECT 'S0001', '1101',  'Y', 'Y', 'N', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'S0001', '1102',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
-- 예약
SELECT 'S0001', '1201',  'Y', 'Y', 'N', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'S0001', '1202',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
-- 프론트
SELECT 'S0001', '1301',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'S0001', '1302',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT 'S0001', '1303',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
-- 보고서
SELECT 'S0001', '1401',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system';
