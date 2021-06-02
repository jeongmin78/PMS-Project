-- 초기 데이터 : PMS 사용 셈플데이터 ########################################
-- 객실등록
--DELETE FROM PMS_ROOM;
INSERT INTO PMS_ROOM (ROOM_NUM, ROOM_TYP_CD, DND_YN, EB_YN, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
SELECT '101', 'SB', 'N', 'N', DATEADD('DAY',-1, NOW()), 'system', DATEADD('DAY',-1, NOW()), 'system' UNION ALL
SELECT '201', 'DB', 'N', 'N', DATEADD('DAY',-1, NOW()), 'system', DATEADD('DAY',-1, NOW()), 'system' UNION ALL
SELECT '301', 'DT', 'N', 'N', DATEADD('DAY',-1, NOW()), 'system', DATEADD('DAY',-1, NOW()), 'system';

-- 고객등록
--DELETE FROM PMS_GUEST;
INSERT INTO PMS_GUEST (GUEST_NM, GUEST_NM_ENG, GUEST_TEL, EMAIL, GENDER, LANG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
SELECT '신현우', 'david', '', 'okrsdavid@gmail.com', 'M', 'KO', DATEADD('DAY',-1, NOW()), 'system', DATEADD('DAY',-1, NOW()), 'system' UNION ALL
SELECT '전만호', 'mano', '', 'okarmano@gmail.com', 'M', 'KO', DATEADD('DAY',-1, NOW()), 'system', DATEADD('DAY',-1, NOW()), 'system';

-- 예약등록
--DELETE FROM PMS_CHK;
INSERT INTO PMS_CHK (RSV_DT, SNO, RSV_NUM, GUEST_ID, GUEST_NM, GUEST_NM_ENG, GUEST_TEL, EMAIL, GENDER, LANG_CD,
    ARR_DT, DEP_DT, NIGHT_CNT, ROOM_TYP_CD, ROOM_NUM, ADULT_CNT, CHLD_CNT,
    SALE_TYP_CD, STTUS_CD, SRC_CD, ADVN_YN,
    CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY
)
SELECT '2021-05-19', 1, 'R20210519001', 1, '신현우', 'david', '', 'okrsdavid@gmail.com', 'M', 'KO',
    '2021-05-20', '2021-05-22', 2, 'SB', '101', 2, 0, '01', 'RSV_01', '01', 'N',
    DATEADD('DAY',-1, NOW()), 'system', DATEADD('DAY',-1, NOW()), 'system' UNION ALL
SELECT '2021-05-27', 1, 'R20210527001', 1, '신현우', 'david', '', 'okrsdavid@gmail.com', 'M', 'KO',
    '2021-05-27', '2021-05-28', 1, 'DB', '201', 2, 0, '01', 'RSV_04', '01', 'N',
    DATEADD('DAY',-1, NOW()), 'system', DATEADD('DAY',-1, NOW()), 'system';

-- 예약메모
INSERT INTO PMS_CHK_MEMO (RSV_NUM, SNO, MEMO_CN, MEMO_DTTI, MEMO_MAN, DEL_YN, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
SELECT 'R20210519001', 1, '셈플메모1', DATEADD('DAY',-1, NOW()), '신현우', 'N', DATEADD('DAY',-1, NOW()), 'system', DATEADD('DAY',-1, NOW()), 'system' UNION ALL
SELECT 'R20210519001', 2, '셈플메모2', DATEADD('DAY',-1, NOW()), '신현우', 'N', DATEADD('DAY',-1, NOW()), 'system', DATEADD('DAY',-1, NOW()), 'system';
