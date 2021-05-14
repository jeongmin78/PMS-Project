package edu.axboot.controllers.dto;

import edu.axboot.domain.guest.Guest;
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
    public GuestUpdateRequestDto(Guest entity) {
        this.guestNm = entity.getGuestNm();
        this.guestNmEng = entity.getGuestNmEng();
        this.guestTel = entity.getGuestTel();
        this.email = entity.getEmail();
        this.brth = entity.getBrth();
        this.gender = entity.getGender();
        this.langCd = entity.getLangCd();
        this.rmk = entity.getRmk();
    }
//    public Guest toEntity() {
//        return Guest.builder()
//                .guestNm(guestNm)
//                .guestNmEng(guestNmEng)
//                .guestTel(guestTel)
//                .email(email)
//                .brth(brth)
//                .gender(gender)
//                .langCd(langCd)
//                .rmk(rmk)
//                .build();
//    }
}
