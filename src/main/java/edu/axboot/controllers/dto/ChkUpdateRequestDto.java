package edu.axboot.controllers.dto;

import edu.axboot.domain.chk.Chk;
import edu.axboot.domain.chkmemo.ChkMemo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChkUpdateRequestDto {
    private String rsvDt;
    private Integer sno;
    private String rsvNum;
    private Long guestId;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String langCd;
    private String arrDt;
    private String arrTime;
    private String depDt;
    private String depTime;
    private Integer nightCnt;
    private String roomTypCd;
    private String roomNum;
    private Integer adultCnt;
    private Integer chldCnt;
    private String saleTypCd;
    private String sttusCd;
    private String srcCd;
    private String brth;
    private String gender;
    private String payCd;
    private String advnYn;
    private List<ChkMemo> memoList;
    private BigDecimal salePrc;
    private BigDecimal svcPrc;

    @Builder
    public ChkUpdateRequestDto(String rsvDt, Integer sno, String rsvNum, Long guestId, String guestNm,
                             String guestNmEng, String guestTel, String email, String langCd,
                             String arrDt, String arrTime, String depDt, String depTime,
                             Integer nightCnt, String roomTypCd, String roomNum,
                             Integer adultCnt, Integer chldCnt, String saleTypCd,
                             String sttusCd, String srcCd, String brth, String gender,
                             String payCd, String advnYn, BigDecimal salePrc, BigDecimal svcPrc,
                             List<ChkMemo> memoList) {
        this.rsvDt = rsvDt;
        this.sno = sno;
        this.rsvNum = rsvNum;
        this.guestId = guestId;
        this.guestNm = guestNm;
        this.guestNmEng = guestNmEng;
        this.guestTel = guestTel;
        this.email = email;
        this.langCd = langCd;
        this.arrDt= arrDt;
        this.arrTime = arrTime;
        this.depDt = depDt;
        this.depTime = depTime;
        this.nightCnt = nightCnt;
        this.roomTypCd = roomTypCd;
        this.roomNum = roomNum;
        this.adultCnt = adultCnt;
        this.chldCnt = chldCnt;
        this.saleTypCd = saleTypCd;
        this.sttusCd = sttusCd;
        this.srcCd = srcCd;
        this.brth = brth;
        this.gender = gender;
        this.payCd = payCd;
        this.advnYn = advnYn;
        this.salePrc = salePrc;
        this.svcPrc = svcPrc;
        this.memoList = memoList;
    }

    public Chk toEntity() {
        return Chk.builder()
                .rsvDt(rsvDt)
                .sno(sno)
                .rsvNum(rsvNum)
                .guestId(guestId)
                .guestNm(guestNm)
                .guestNmEng(guestNmEng)
                .guestTel(guestTel)
                .email(email)
                .langCd(langCd)
                .arrDt(arrDt)
                .arrTime(arrTime)
                .depDt(depDt)
                .nightCnt(nightCnt)
                .roomTypCd(roomTypCd)
                .roomNum(roomNum)
                .adultCnt(adultCnt)
                .chldCnt(chldCnt)
                .saleTypCd(saleTypCd)
                .sttusCd(sttusCd)
                .srcCd(srcCd)
                .brth(brth)
                .gender(gender)
                .payCd(payCd)
                .advnYn(advnYn)
                .salePrc(salePrc)
                .svcPrc(svcPrc)
                .memoList(memoList)
                .build();
    }
}
