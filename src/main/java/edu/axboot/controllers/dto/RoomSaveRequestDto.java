package edu.axboot.controllers.dto;

import edu.axboot.domain.room.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomSaveRequestDto {
    private Long id;
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusCd;
    private String svcSttusCd;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    @Builder
//    public RoomSaveRequestDto(String roomNum, String roomTypCd, String dndYn, String ebYn,
//                              String roomSttusCd, String clnSttusCd, String svcSttusCd){
    public RoomSaveRequestDto(Room entity){
        this.id = entity.getId();
        this.roomNum = entity.getRoomNum();
        this.roomTypCd = entity.getRoomTypCd();
        this.dndYn = entity.getDndYn();
        this.ebYn = entity.getEbYn();
        this.roomSttusCd = entity.getRoomSttusCd();
        this.clnSttusCd = entity.getClnSttusCd();
        this.svcSttusCd = entity.getSvcSttusCd();
        this.__created__ = false;
        this.__modified__ = false;
        this.__deleted__ = false;
    }
    public Room toEntity() {
        return Room.builder()
                .id(id)
                .roomNum(roomNum)
                .roomTypCd(roomTypCd)
                .dndYn(dndYn)
                .ebYn(ebYn)
                .roomSttusCd(roomSttusCd)
                .clnSttusCd(clnSttusCd)
                .svcSttusCd(svcSttusCd)
                .isCreated(__created__)
                .isModified(__modified__)
                .isDeleted(__deleted__)
                .build();
    }
}
