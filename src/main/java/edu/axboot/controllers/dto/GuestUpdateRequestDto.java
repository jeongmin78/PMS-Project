package edu.axboot.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestUpdateRequestDto {
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String brth;
    private String gender;
    private String langCd;
    private String rmk;

    @Builder
    public GuestUpdateRequestDto(String guestNm, String guestNmEng, String guestTel, String email,
                                 String brth, String gender, String langCd, String rmk) {
        this.guestNm = guestNm;
        this.guestNmEng = guestNmEng;
        this.guestTel = guestTel;
        this.email = email;
        this.brth = brth;
        this.gender = gender;
        this.langCd = langCd;
        this.rmk = rmk;
    }

}
