package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.chequer.axboot.core.utils.DateUtils;
import com.chequer.axboot.core.utils.ExcelUtils;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import edu.axboot.controllers.dto.GuestResponseDto;
import edu.axboot.controllers.dto.GuestSaveRequestDto;
import edu.axboot.controllers.dto.GuestUpdateRequestDto;
import edu.axboot.domain.chk.Chk;
import edu.axboot.utils.MiscUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.guest.Guest;
import edu.axboot.domain.guest.GuestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/guest")
public class GuestController extends BaseController {

    private final GuestService guestService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse list(RequestParams<Guest> requestParams) {
        List<Guest> list = guestService.getList(requestParams);
        Page<Guest> page = MiscUtils.toPage(list, requestParams.getPageable());
        return Responses.PageResponse.of(page);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public GuestResponseDto view(@PathVariable Long id) {
        return guestService.getOneById(id);

    }

    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody GuestSaveRequestDto requestDto) {
        guestService.save(requestDto);
        return ok();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse update(@PathVariable Long id, @RequestBody GuestUpdateRequestDto requestDto) {
        guestService.update(id, requestDto);
        return ok();
    }

    @ApiOperation(value = "엑셀다운로드", notes = "/resources/excel/guest.xlsx")
    @RequestMapping(value = "/exceldown", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public void excelDown(RequestParams<Guest> requestParams, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Guest> list = guestService.getList(requestParams);
        ExcelUtils.renderExcel("/excel/guest.xlsx", list, "guest_" + DateUtils.getYyyyMMddHHmmssWithDate(), request, response);
    }

}