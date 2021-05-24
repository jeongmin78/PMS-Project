package edu.axboot.domain.guest;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.GuestResponseDto;
import edu.axboot.controllers.dto.GuestSaveRequestDto;
import edu.axboot.controllers.dto.GuestUpdateRequestDto;
import edu.axboot.domain.chk.ChkService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;

import javax.transaction.Transactional;

import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GuestService extends BaseService<Guest, Long> {
    private static final Logger logger = LoggerFactory.getLogger(ChkService.class);

    private final GuestRepository guestRepository;

    public List<Guest> gets(RequestParams<Guest> requestParams) {
        return findAll();
    }

    public List<Guest> getList(RequestParams<Guest> requestParams) {
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
        List<Guest> list = select()
                .from(qGuest)
                .where(builder)
                .orderBy(qGuest.guestNm.asc())
                .fetch();

        return list;
    }

    public GuestResponseDto getOneById(Long id) {
        Guest entity = guestRepository.findOne(id);
        if (entity == null) {
            throw new IllegalArgumentException("해당 고객이 없습니다. id=" + id);
        }
        return new GuestResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, GuestUpdateRequestDto requestDto) {
        Guest entity = guestRepository.findOne(id);
        if (entity == null) {
            throw new IllegalArgumentException("해당 고객이 없습니다. id=" + id);
        }
        entity.고객정보_수정하기(requestDto);

        return id;
    }

    @Transactional
    public Long save(GuestSaveRequestDto requestDto) {
        return guestRepository.save(requestDto.toEntity()).getId();
    }

}