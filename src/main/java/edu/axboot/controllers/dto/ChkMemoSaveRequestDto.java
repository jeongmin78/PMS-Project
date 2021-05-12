package edu.axboot.controllers.dto;

import edu.axboot.domain.chkmemo.ChkMemo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ChkMemoSaveRequestDto {
    private Long id;
    private String rsvNum;
    private Integer sno;
    private String memoCn;
    private Timestamp memoDtti;
    private String memoMan;
    private String delYn;

    @Builder
    public ChkMemoSaveRequestDto(Long id, String rsvNum, Integer sno, String memoCn,
                                 Timestamp memoDtti, String memoMan, String delYn){
        this.id = id;
        this.rsvNum = rsvNum;
        this.sno = sno;
        this.memoCn = memoCn;
        this.memoDtti = memoDtti;
        this.memoMan = memoMan;
        this.delYn = delYn;
    }

    public ChkMemo toEntity() {
        return ChkMemo.builder()
                .id(id)
                .rsvNum(rsvNum)
                .sno(sno)
                .memoCn(memoCn)
                .memoDtti(memoDtti)
                .memoMan(memoMan)
                .delYn(delYn)
                .build();
    }
}
