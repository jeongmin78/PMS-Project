package edu.axboot.chk;

import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.ChkSaveRequestDto;
import edu.axboot.domain.chk.ChkService;
import edu.axboot.domain.room.RoomService;
import lombok.extern.java.Log;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChkServiceTest {
    @Autowired
    private ChkService chkService;
    private static final Logger logger = LoggerFactory.getLogger(ChkService.class);
    public static long testId = 0;

    @Test
    public void test1_예약등록_저장하기() {
        //given
        ChkSaveRequestDto saveDto = ChkSaveRequestDto.builder()
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
        testId = this.chkService.saveUsingJpa(saveDto);
        logger.info("\n"+ "ID ===============> " + testId);
        //then
        assertTrue(testId > 0);

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
