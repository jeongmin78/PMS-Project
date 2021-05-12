package edu.axboot.controllers.dto;

import edu.axboot.domain.room.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomSaveRequestDto {
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusCd;
    private String svcSttusCd;
    @Builder
    public RoomSaveRequestDto(String roomNum, String roomTypCd, String dndYn, String ebYn,
                              String roomSttusCd, String clnSttusCd, String svcSttusCd){
        this.roomNum = roomNum;
        this.roomTypCd = roomTypCd;
        this.dndYn = dndYn;
        this.ebYn = ebYn;
        this.roomSttusCd = roomSttusCd;
        this.clnSttusCd = clnSttusCd;
        this.svcSttusCd = svcSttusCd;
    }
    public Room toEntity() {
        return Room.builder()
                .roomNum(roomNum)
                .roomTypCd(roomTypCd)
                .dndYn(dndYn)
                .ebYn(ebYn)
                .roomSttusCd(roomSttusCd)
                .clnSttusCd(clnSttusCd)
                .svcSttusCd(svcSttusCd)
                .build();
    }
}
