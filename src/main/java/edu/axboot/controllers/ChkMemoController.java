package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.domain.chk.Chk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.chkmemo.ChkMemo;
import edu.axboot.domain.chkmemo.ChkMemoService;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/chkmemos")
public class ChkMemoController extends BaseController {

//    @Inject
    private final ChkMemoService chkMemoService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<ChkMemo> requestParams) {
        List<ChkMemo> list = chkMemoService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<ChkMemo> request) {
        chkMemoService.save(request);
        return ok();
    }

    @RequestMapping(value = "select", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list2(RequestParams<ChkMemo> requestParams) {
        List<ChkMemo> list = chkMemoService.getList(requestParams);
        return Responses.ListResponse.of(list);
    }
}