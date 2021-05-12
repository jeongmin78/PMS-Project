package edu.axboot.domain.guest;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.GuestListResponseDto;
import edu.axboot.controllers.dto.RoomListResponseDto;
import edu.axboot.domain.room.Room;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService extends BaseService<Guest, Long> {
    private GuestRepository guestRepository;

    @Inject
    public GuestService(GuestRepository guestRepository) {
        super(guestRepository);
        this.guestRepository = guestRepository;
    }

    public List<Guest> gets(RequestParams<Guest> requestParams) {
        return findAll();
    }

    public List<GuestListResponseDto> getListUsingQueryDsl(RequestParams<GuestListResponseDto> requestParams) {
        String guestNm = requestParams.getString("guestNm","");
        String guestTel = requestParams.getString("guestTel","");
        String email = requestParams.getString("email", "");
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(guestNm)) {
            builder.and(qGuest.guestNm.contains(guestNm));
        }
        if (isNotEmpty(guestTel)) {
            builder.and(qGuest.guestTel.contains(guestTel));
        }
        if (isNotEmpty(email)) {
            builder.and(qGuest.email.contains(email));
        }
        List<Guest> entitis = select()
                .from(qGuest)
                .where(builder)
                .orderBy(qGuest.guestNm.asc())
                .fetch();

        return entitis.stream()
                .map(GuestListResponseDto::new)
                .collect(Collectors.toList());
    }

}