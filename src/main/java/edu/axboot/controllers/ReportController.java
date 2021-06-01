package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.dto.ReportResponseDto;
import edu.axboot.domain.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/report")
public class ReportController extends BaseController {

    private final ReportService reportService;

    @RequestMapping(value = "total", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse view2(RequestParams<ReportResponseDto> requestParams) {
        List<ReportResponseDto> list = reportService.getRsvDtTotalCount(requestParams);
        return Responses.ListResponse.of(list);
    }
}
