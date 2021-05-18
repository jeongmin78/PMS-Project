package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import edu.axboot.controllers.dto.GuestResponseDto;
import edu.axboot.controllers.dto.GuestSaveRequestDto;
import edu.axboot.controllers.dto.GuestUpdateRequestDto;
import edu.axboot.utils.MiscUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.guest.Guest;
import edu.axboot.domain.guest.GuestService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/guest")
public class GuestController extends BaseController {

    private final GuestService guestService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(name="guestNm", value="이름", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="guestTel", value="전화번호", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="email", value="이메일", dataType = "String", paramType = "query"),
    })
    public Responses.PageResponse list(RequestParams<GuestResponseDto> requestParams) {
        List<GuestResponseDto> list = guestService.getList(requestParams);
        Page<GuestResponseDto> page = MiscUtils.toPage(list, requestParams.getPageable());
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


}