package edu.axboot.controllers.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReportResponseDto {
    private String rsvDt;
    private Long roomCount;
    private BigDecimal salePrc;
    private BigDecimal svcPrc;


    public ReportResponseDto(String rsvDt, Long roomCount, BigDecimal salePrc, BigDecimal svcPrc) {
        this.rsvDt = rsvDt;
        this.roomCount = roomCount;
        this.salePrc = salePrc;
        this.svcPrc = svcPrc;
    }
}
