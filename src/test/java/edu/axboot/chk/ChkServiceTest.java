package edu.axboot.chk;

import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.ChkMemoSaveRequestDto;
import edu.axboot.controllers.dto.ChkSaveRequestDto;
import edu.axboot.controllers.dto.GuestSaveRequestDto;
import edu.axboot.controllers.dto.RoomSaveRequestDto;
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
    public void test1_예약등록_저장하기() {
        //given
        Long id = null;
        String rsvNum = null;
        Integer sno = null;
        String memoCn = "A";
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        String memoMan = "B";
        String delYn = "Y";
        ChkMemo chkMemo = new ChkMemo(id, rsvNum, sno, memoCn, timestamp, memoMan, delYn);
        List<ChkMemo> memoList = new ArrayList<>();
        memoList.add(chkMemo);

        ChkSaveRequestDto saveRequestDto = ChkSaveRequestDto.builder()
                .id(null)
                .sno(null)
                .rsvNum(null)
                .rsvDt(null)
                .guestNm("guestNm")
                .arrDt("arrDt")
                .depDt("depDt")
                .nightCnt(2)
                .roomTypCd("roomTypCd")
                .adultCnt(2)
                .chldCnt(0)
                .saleTypCd("saleTypCd")
                .sttusCd("01")
                .srcCd("01")
                .advnYn("Y")
                .memoList(memoList)
                .build();

        //when
        testId = this.chkService.saveUsingJpa(saveRequestDto);
        logger.info("\n" + "ID ===============> " + testId);

        //then
        assertTrue(testId > 0);
    }

    @Test
    public void test2_예약등록_조회하기() {

        Chk chk = this.chkService.getOne(30L);
        logger.info("============================== guestId : " + chk.getGuest().getId());
        logger.info("============================== guestId : " + chk.getGuest().getGuestNm());

    }
}