package edu.axboot.chk;

import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.ChkMemoSaveRequestDto;
import edu.axboot.controllers.dto.ChkSaveRequestDto;
import edu.axboot.domain.chk.Chk;
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
    public static String testRsvNum;
    public static String testMemo;

    @Test
    public void test1_예약등록_저장하기() {
        //given
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
                .build();

        //when
//        Long id = null;
//        String rsvNum = saveRequestDto.getRsvNum();
//        String memoCn = "A";
//        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
//        String memoMan = "B";
//        String delYn = "Y";
//        Integer sno = 3;
//        ChkMemo chkMemo = new ChkMemo(id, rsvNum, sno, memoCn, timestamp, memoMan, delYn);

        ChkMemoSaveRequestDto memoSaveDto = ChkMemoSaveRequestDto.builder()
                .id(null)
                .rsvNum(saveRequestDto.getRsvNum())
                .sno(2)
                .memoCn("memoCn")
                .memoDtti(Timestamp.valueOf(LocalDateTime.now()))
                .memoMan("memoMan")
                .delYn("delYn")
                .build();

        testRsvNum = this.chkService.saveUsingJpa(saveRequestDto);
        testMemo = this.chkMemoService.saveUsingJpa(memoSaveDto);

        logger.info("\n"+ "ID ===============> " + testRsvNum);
        logger.info("\n"+ "ID ===============> " + testMemo);
        //then
        assertTrue(testRsvNum != null);
        assertTrue(testMemo != null);
    }

    @Test
    public void test2_예약등록_메모작성하기() {

        //given
        ChkMemoSaveRequestDto memoSaveDto = ChkMemoSaveRequestDto.builder()
                .id(33L)
                .rsvNum("R20210512002")
                .sno(2)
                .memoCn("memoCn")
                .memoDtti(Timestamp.valueOf(LocalDateTime.now()))
                .memoMan("memoMan")
                .delYn("delYn")
                .build();
        //when
        testRsvNum = this.chkMemoService.saveUsingJpa(memoSaveDto);
        logger.info("\n"+ "ID ===============> " + testRsvNum);
        //then
        assertTrue(testRsvNum != null);
    }

}


/*    @Test
    public void test1_객실_한건_저장하기() {
        //given
        RoomSaveRequestDto saveDto = RoomSaveRequestDto.builder()
                .roomNum("105")
                .roomTypCd("SB")
                .dndYn("N")
                .ebYn("N")
                .roomSttusCd("EMT")
                .clnSttusCd("VC")
                .svcSttusCd("OOO")
                .build();
        //when
        testId = this.roomService.save(saveDto);
        logger.info("\n"+ "ID ===============> " + testId);
        //then
        assertTrue(testId > 0);
    }*/

/*    @Test
    public void test2_객실_리스트_불러오기() {
        //given
        String roomTypCd = "";

        //when
        List<RoomListResponseDto> result = this.roomService.getListUsingQueryDsl();
        logger.info("\nlist: ============== " + result);

        //then
        assertTrue(result.size() > 0);
    }*/
