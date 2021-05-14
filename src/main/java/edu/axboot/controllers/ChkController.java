package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.dto.ChkSaveRequestDto;
import edu.axboot.controllers.dto.RoomListResponseDto;
import edu.axboot.controllers.dto.RoomSaveRequestDto;
import edu.axboot.domain.chk.Chk;
import edu.axboot.domain.chk.ChkService;
import edu.axboot.domain.chkmemo.ChkMemo;
import edu.axboot.utils.MiscUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/chk")
public class ChkController extends BaseController {

    private final ChkService chkService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<Chk> requestParams) {
        List<Chk> list = chkService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

//    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
//    public ApiResponse save(@RequestBody List<Chk> request) {
//        chkService.save(request);
//        return ok();
//    }

    //    ------------------------------------------------------------

    @RequestMapping(value = "select", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list2(RequestParams<Chk> requestParams) {
        List<Chk> list = chkService.getList(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody ChkSaveRequestDto requestDto) {
        chkService.saveUsingJpa(requestDto);
        return ok();
    }
}