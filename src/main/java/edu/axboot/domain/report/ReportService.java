package edu.axboot.domain.report;

import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.axboot.controllers.dto.ReportResponseDto;
import edu.axboot.domain.BaseService;
import edu.axboot.domain.chk.Chk;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ReportService extends BaseService<Chk, Long> {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate today = LocalDate.now();

    public List<ReportResponseDto> getRsvDtTotalCount(RequestParams<ReportResponseDto> requestParams) {
        String rsvDt = requestParams.getString("rsvDt","");
        String rsvDtEnd = requestParams.getString("rsvDtEnd","");

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(rsvDt)){
            if (isNotEmpty(rsvDtEnd)){
                builder.and(qChk.rsvDt.between(rsvDt,rsvDtEnd));
            }else
                builder.and(qChk.rsvDt.between(rsvDt, String.valueOf(today)));
        }

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<ReportResponseDto> chkReportList = queryFactory.selectFrom(qChk)
                .where(builder)
                .groupBy(qChk.rsvDt)
                .select(Projections.constructor(ReportResponseDto.class, qChk.rsvDt, qChk.rsvNum.count(), qChk.salePrc.sum(), qChk.svcPrc.sum()))
                .orderBy(qChk.rsvNum.count().desc())
                .fetch();

        long count=0;
        BigDecimal salePrc = BigDecimal.ZERO;
        BigDecimal svcPrc = BigDecimal.ZERO;
        for (ReportResponseDto chkReport : chkReportList){
            count += chkReport.getRoomCount();
            if (chkReport.getSalePrc() != null) salePrc = salePrc.add(chkReport.getSalePrc());
            if (chkReport.getSvcPrc() != null) svcPrc = svcPrc.add(chkReport.getSvcPrc());
        }
        ReportResponseDto reportDto = new ReportResponseDto("합계", count, salePrc,svcPrc);
        chkReportList.add(0, reportDto);
        return chkReportList;
    }

}
