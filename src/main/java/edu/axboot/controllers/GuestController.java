package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.dto.GuestListResponseDto;
import edu.axboot.utils.MiscUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.guest.Guest;
import edu.axboot.domain.guest.GuestService;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/guest")
public class GuestController extends BaseController {

    @Inject
    private GuestService guestService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse list(RequestParams<GuestListResponseDto> requestParams) {
        List<GuestListResponseDto> list = guestService.getListUsingQueryDsl(requestParams);
        Page<GuestListResponseDto> page = MiscUtils.toPage(list, requestParams.getPageable());
        return Responses.PageResponse.of(page);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<Guest> request) {
        guestService.save(request);
        return ok();
    }
}