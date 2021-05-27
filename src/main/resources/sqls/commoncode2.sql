-- 초기 데이터 : PMS 사용 공통코드 ########################################
DELETE FROM COMMON_GROUP WHERE GROUP_CD LIKE 'PMS%';
INSERT INTO COMMON_GROUP (GROUP_ID, GROUP_CD, GROUP_NM, PARENT_ID, LEVEL, SORT, ROOT_CD, RMK, USE_YN, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
-- 객실정보 - 객실타입
SELECT null, 'PMS', 'PMS', null, 0, 0, 'SYSTEM_MANAGER',null,'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT null, 'PMS_ROOM_TYPE', '객실타입', 1, 1, 1, 'PMS',null, 'Y', sysdate(), 'system', sysdate(), 'system' UNION ALL
SELECT null, 'PMS_ROOM_STATUS', '객실상태', 1, 1, 2, 'PMS',null, 'Y', sysdate(), 'system', sysdate(), 'system';
