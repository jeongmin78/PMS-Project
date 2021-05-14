package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.dto.RoomListResponseDto;
import edu.axboot.controllers.dto.RoomSaveRequestDto;
import edu.axboot.domain.room.Room;
import edu.axboot.domain.room.RoomService;
import edu.axboot.utils.MiscUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/room")
public class RoomController extends BaseController {

    private final RoomService roomService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<Room> requestParams) {
        List<Room> list = roomService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<Room> request) {
        roomService.saveUsingQueryDsl(request);
        return ok();
    }

//    ------------------------------------------------------------

    @RequestMapping(value = "select", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse list2(RequestParams<RoomListResponseDto> requestParams) {
        List<RoomListResponseDto> list = roomService.getListUsingQueryDsl(requestParams);
        Page<RoomListResponseDto> page = MiscUtils.toPage(list, requestParams.getPageable());
        return Responses.PageResponse.of(page);
    }

    @RequestMapping(value = "add", method = RequestMethod.PUT, produces = APPLICATION_JSON)
    public ApiResponse save2(@RequestBody RoomSaveRequestDto requestDto) {
        roomService.save(requestDto);
        return ok();
    }

}