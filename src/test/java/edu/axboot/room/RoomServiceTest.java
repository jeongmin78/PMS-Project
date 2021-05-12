package edu.axboot.room;

import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.RoomListResponseDto;
import edu.axboot.controllers.dto.RoomSaveRequestDto;
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

import java.util.List;

import static org.junit.Assert.assertTrue;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;
    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

    public static long testId = 0;

    @Test
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
    }
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
}
