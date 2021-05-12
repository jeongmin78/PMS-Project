package edu.axboot.domain.room;

import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.GuestListResponseDto;
import edu.axboot.controllers.dto.RoomListResponseDto;
import edu.axboot.controllers.dto.RoomSaveRequestDto;
import edu.axboot.domain.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoomService extends BaseService<Room, Long> {

    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @Inject
    public RoomService(RoomRepository roomRepository) {
        super(roomRepository);
        this.roomRepository = roomRepository;
    }

    public List<Room> gets(RequestParams<Room> requestParams) {
        return findAll();
    }

    @Transactional
    public Long save(RoomSaveRequestDto requestDto) {
        return roomRepository.save(requestDto.toEntity()).getId();
    }

    public List<RoomListResponseDto> getListUsingQueryDsl(RequestParams<RoomListResponseDto> requestParams) {
        String roomTypCd = requestParams.getString("roomTypCd","");

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(roomTypCd)) {
            builder.and(qRoom.roomTypCd.eq(roomTypCd));
        }
        List<Room> entitis = select()
                .from(qRoom)
                .where(builder)
                .orderBy(qRoom.roomNum.asc())
                .fetch();

        return entitis.stream()
                .map(RoomListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveUsingQueryDsl(List<Room> requests) {
        for (Room room: requests) {
            if (room.isCreated()) {
                save(room);
        } else if(room.isModified()) {
                update(qRoom)
                        .set(qRoom.roomNum, room.getRoomNum())
                        .set(qRoom.roomTypCd, room.getRoomTypCd())
                        .set(qRoom.dndYn, room.getDndYn())
                        .set(qRoom.ebYn, room.getEbYn())
                        .set(qRoom.roomSttusCd, room.getRoomSttusCd())
                        .set(qRoom.clnSttusCd, room.getClnSttusCd())
                        .set(qRoom.svcSttusCd, room.getSvcSttusCd())
                        .where(qRoom.id.eq(room.getId()))
                        .execute();
            } else if (room.isDeleted()){
                delete(qRoom)
                        .where(qRoom.id.eq(room.getId()))
                        .execute();
            }
        }
    }

}