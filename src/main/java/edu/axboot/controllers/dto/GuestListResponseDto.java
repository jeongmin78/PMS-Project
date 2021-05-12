package edu.axboot.controllers.dto;

import edu.axboot.domain.guest.Guest;
import edu.axboot.domain.room.Room;
import lombok.Getter;

@Getter
public class GuestListResponseDto {
    private Long id;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String brth;
    private String gender;
    private String lang_Cd;
    private String rmk;

    public GuestListResponseDto(Guest entity) {
        this.id = entity.getId();
        this.guestNm = entity.getGuestNm();
        this.guestNmEng = entity.getGuestNmEng();
        this.guestTel = entity.getGuestTel();
        this.email = entity.getEmail();
        this.brth = entity.getBrth();
        this.gender = entity.getGender();
        this.lang_Cd = entity.getLangCd();
        this.rmk = entity.getRmk();
    }
}