//package edu.axboot.guest;
//
//import com.chequer.axboot.core.parameter.RequestParams;
//import edu.axboot.AXBootApplication;
//import edu.axboot.controllers.dto.GuestResponseDto;
//import edu.axboot.controllers.dto.GuestSaveRequestDto;
//import edu.axboot.controllers.dto.GuestUpdateRequestDto;
//import edu.axboot.domain.chk.ChkService;
//import edu.axboot.domain.guest.Guest;
//import edu.axboot.domain.guest.GuestService;
//import lombok.extern.java.Log;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//
//@Log
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AXBootApplication.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class GuestServiceTest {
//    @Autowired
//    private GuestService guestService;
//
//    private static final Logger logger = LoggerFactory.getLogger(ChkService.class);
//    public static long testId = 0;
//
//    @Test
//    public void test1_고객정보_저장하기() {
//        GuestSaveRequestDto saveRequestDto = GuestSaveRequestDto.builder()
//                .id(null)
//                .guestNm("김미숙")
//                .guestNmEng("kim mi suk")
//                .guestTel("01055667788")
//                .email("aa@bb.com")
//                .brth("19600321")
//                .gender("female")
//                .langCd("KO")
//                .rmk(null)
//                .build();
//
//        testId = this.guestService.save(saveRequestDto);
//        logger.info("\n" + "ID ===============> " + testId);
//
//        assertTrue(testId>0);
//    }
//
//    @Test
//    public void test2_고객정보_조회하기() {
//        Guest guest = this.guestService.findOne(testId);
//        logger.info("============================== guestNm : " + guest.getGuestNm());
//    }
//
//    @Test
//    public void test3_고객정보_수정하기() {
//        GuestUpdateRequestDto updateRequestDto = GuestUpdateRequestDto.builder()
//                .guestNm("김미숙")
//                .guestNmEng("kim-misuk")
//                .guestTel("01055667788")
//                .email("aa@bb.com")
//                .brth("19600321")
//                .gender("female")
//                .langCd("KO")
//                .rmk("수정하기")
//                .build();
//
//        testId = this.guestService.update(testId,updateRequestDto);
//        logger.info("\n" + "ID ===============> " + testId);
//    }
//
//    @Test
//    public void test4_고객정보_조회하기() {
//        Guest guest = this.guestService.findOne(testId);
//        logger.info("============================== guestNm : " + guest.getGuestNm());
//        logger.info("============================== rmk : " + guest.getRmk());
//    }
//
////    @Test
////    public void test4_고객정보_삭제하기() {
////    }
//
//}
