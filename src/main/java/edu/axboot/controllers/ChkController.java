package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.chequer.axboot.core.utils.DateUtils;
import com.chequer.axboot.core.utils.ExcelUtils;
import com.querydsl.core.Tuple;
import com.wordnik.swagger.annotations.ApiOperation;
import edu.axboot.controllers.dto.*;
import edu.axboot.domain.chk.Chk;
import edu.axboot.domain.chk.ChkService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/chk")
public class ChkController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ChkService.class);

    private final ChkService chkService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public ChkResponseDto view(@PathVariable Long id){
        return chkService.getOneById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<Chk> requestParams) {
        List<Chk> list = chkService.getList(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(value = "max", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public ChkResponseDto view() {
        return chkService.getOneByDesc();
    }

    @RequestMapping(value = "total", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse view2(RequestParams<ChkReportListResponseDto> requestParams) {
        List<ChkReportListResponseDto> list = chkService.getArrDtTotalCount(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody ChkSaveRequestDto requestDto) {
        chkService.saveUsingJpa(requestDto);
        return ok();
    }
    @RequestMapping(value = "/{id}", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse update(@PathVariable Long id, @RequestBody ChkUpdateRequestDto requestDto) {
        chkService.update(id, requestDto);
        return ok();
    }

    @RequestMapping(value = "/sttus", method = RequestMethod.POST, produces = APPLICATION_JSON)
    public ApiResponse update2(@RequestParam(value = "ids") List<Long> ids, @RequestParam(value = "sttusCd") String sttusCd) {
        logger.info("=========================> " + ids);
        logger.info("=========================> " + sttusCd);

        for(Long id: ids){
            logger.info("=========================> " + id);
            chkService.updateSttus(id, sttusCd);
        }
        return ok();
    }

    @ApiOperation(value = "엑셀다운로드", notes = "/resources/excel/reservation.xlsx")
    @RequestMapping(value = "/exceldown", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public void excelDown(RequestParams<Chk> requestParams, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Chk> list = chkService.getList(requestParams);
        ExcelUtils.renderExcel("/excel/reservation.xlsx", list, "reservation_" + DateUtils.getYyyyMMddHHmmssWithDate(), request, response);
    }

}