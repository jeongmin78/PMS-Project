package edu.axboot.chk;

import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.*;
import edu.axboot.domain.chk.Chk;
import edu.axboot.domain.chk.ChkRepository;
import edu.axboot.domain.chk.ChkService;
import edu.axboot.domain.chkmemo.ChkMemo;
import edu.axboot.domain.chkmemo.ChkMemoService;
import edu.axboot.domain.room.RoomService;
import lombok.extern.java.Log;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.internal.verification.Times;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChkServiceTest {
    @Autowired
    private ChkService chkService;
    @Autowired
    private ChkMemoService chkMemoService;

    private static final Logger logger = LoggerFactory.getLogger(ChkService.class);
    public static long testId = 0;

    @Test
    public void test1_예약_등록하기_신규투숙객() {
        //given
        ChkSaveRequestDto saveDto = ChkSaveRequestDto.builder()
                .arrDt("2021-05-25")
                .depDt("2021-05-26")
                .nightCnt(1)
                .roomTypCd("SB")
                .adultCnt(2)
                .chldCnt(0)
                .saleTypCd("01")
                .srcCd("CMS")
                .salePrc(new BigDecimal(126000))
                .svcPrc(new BigDecimal(20000))
                .advnYn("N")
                .guestNm("전만호")
                .guestNmEng("michael")
                .guestTel("01073342143")
                .email("okaymano@gmail.com")
                .build();

        //when
        testId = chkService.saveUsingJpa(saveDto);

        //then
        assertTrue(testId > 0);
    }

    @Test
    public void test1_예약등록_저장하기() {
        //given
        Long id = null;
        String rsvNum = null;
        Integer sno = null;
        String memoCn = "A";
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        String memoMan = "B";
        String delYn = "Y";
        ChkMemo chkMemo = new ChkMemo(id, rsvNum, sno, memoCn, timestamp, memoMan, delYn, true, false, false);
        List<ChkMemo> memoList = new ArrayList<>();
        memoList.add(chkMemo);

        ChkSaveRequestDto saveRequestDto = ChkSaveRequestDto.builder()
                .id(null)
                .sno(null)
                .rsvNum(null)
                .rsvDt(null)
                .guestNm("박형근")
                .arrDt("2021-05-21")
                .depDt("2021-05-24")
                .nightCnt(3)
                .roomTypCd("roomTypCd")
                .adultCnt(2)
                .chldCnt(0)
                .saleTypCd("saleTypCd")
                .sttusCd("01")
                .srcCd("01")
                .advnYn("Y")
                .build();

        //when
        testId = this.chkService.saveUsingJpa(saveRequestDto);
        logger.info("\n" + "ID ===============> " + testId);

        //then
        assertTrue(testId > 0);
    }

    @Test
    public void test2_예약등록_조회하기() {

        ChkResponseDto chk = this.chkService.getOneById(156L);
    }

    @Test
    public void test3_최근_예약등록_조회하기() {

        ChkResponseDto entity = this.chkService.getOneByDesc();
        logger.info("============================== RsvNum : " + entity.getRsvNum());
    }

    @Test
    public void test3_예약_숙박메모_등록하기() {
        //given
        List<ChkMemoSaveRequestDto> memoList = new ArrayList<ChkMemoSaveRequestDto>();
        memoList.add(ChkMemoSaveRequestDto.builder()
                .memoCn("테스트입력")
                .__created__(true)
                .__modified__(false)
                .__deleted__(false)
                .build());

        ChkSaveRequestDto saveDto = ChkSaveRequestDto.builder()
                .arrDt("2021-05-21")
                .depDt("2021-05-22")
                .nightCnt(1)
                .roomTypCd("SB")
                .adultCnt(2)
                .chldCnt(0)
                .saleTypCd("01")
                .srcCd("CMS")
                .salePrc(new BigDecimal(50000))
                .advnYn("N")
                .guestId(1L)
                .guestNm("전천호")
                .guestNmEng("okaymano")
                .guestTel("010-1111-2222")
                .email("manojun@naver.com")
                .memoList(memoList)
                .build();

        //when
        testId = chkService.saveUsingJpa(saveDto);

        //then
        assertTrue(testId > 0);
    }

    @Test
    public void test4() {

        BigDecimal arr[] = new BigDecimal[4];
        arr[0] = new BigDecimal(10);
        arr[1] = new BigDecimal(30);
        arr[2] = null;
        arr[3] = new BigDecimal(20);

        BigDecimal b = new BigDecimal(0);

        for (BigDecimal a: arr) {
            if (a != null)
                b = b.add(a);
            logger.info("BigDecimal===>" + b);
        }
        logger.info("BigDecimal===>" + b);

//        chkService.getArrDtTotalCount();
    }
}