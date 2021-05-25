package edu.axboot.controllers.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ChkReportListResponseDto {
    private String arrDt;
    private Long roomCount;
    private BigDecimal salePrc;
    private BigDecimal svcPrc;


    public ChkReportListResponseDto(String arrDt, Long roomCount, BigDecimal salePrc, BigDecimal svcPrc) {
        this.arrDt = arrDt;
        this.roomCount = roomCount;
        this.salePrc = salePrc;
        this.svcPrc = svcPrc;
    }
}
