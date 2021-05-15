package edu.axboot.domain.chk;

import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.ChkMemoSaveRequestDto;
import edu.axboot.controllers.dto.ChkSaveRequestDto;
import edu.axboot.controllers.dto.GuestSaveRequestDto;
import edu.axboot.domain.BaseService;
import edu.axboot.domain.chkmemo.ChkMemo;
import edu.axboot.domain.chkmemo.ChkMemoService;
import edu.axboot.domain.guest.Guest;
import edu.axboot.domain.guest.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChkService extends BaseService<Chk, Long> {
    private static final Logger logger = LoggerFactory.getLogger(ChkService.class);

    private final ChkRepository chkRepository;

    private final GuestRepository guestRepository;

    private static int sequence;

    public List<Chk> gets(RequestParams<Chk> requestParams) {
        return findAll();
    }

    public Chk getOne(Long id) {
        Chk chk = chkRepository.findOne(id);
        return chk;
    }
    public List<Chk> getList(RequestParams<Chk> requestParams) {
        String rsvNum = requestParams.getString("rsvNum","");
        String rsvDt = requestParams.getString("rsvDt","");
        String roomTypCd = requestParams.getString("roomTypCd","");
        String arrDt = requestParams.getString("arrDt","");
        String depDt = requestParams.getString("depDt","");
        String sttusCd = requestParams.getString("sttusCd","");
        String filter = requestParams.getFilter();

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(rsvNum)){
            builder.and(qChk.rsvNum.eq(rsvNum));
        }
        if (isNotEmpty(rsvDt)){
            builder.and(qChk.rsvDt.eq(rsvDt));
        }
        if (isNotEmpty(roomTypCd)){
            builder.and(qChk.roomTypCd.eq(roomTypCd));
        }
        if (isNotEmpty(arrDt)){
            builder.and(qChk.arrDt.eq(arrDt));
        }
        if (isNotEmpty(depDt)){
            builder.and(qChk.depDt.eq(depDt));
        }
        if (isNotEmpty(sttusCd)){
            builder.and(qChk.sttusCd.eq(sttusCd));
        }
        if (isNotEmpty(filter)){
            builder.and(qChk.guestNm.contains(filter)
                    .or(qChk.guestNmEng.contains(filter)));
        }
        List<Chk> list = select()
                .from(qChk)
                .where(builder)
                .orderBy(qChk.rsvNum.asc())
                .fetch();

        return list;
    }

    @Transactional
    public Long saveUsingJpa(ChkSaveRequestDto requestDto) {
        GuestSaveRequestDto guestDto = new GuestSaveRequestDto(null,
                requestDto.getGuestNm(), requestDto.getGuestNmEng(), requestDto.getGuestTel(),
                requestDto.getEmail(), requestDto.getBrth(), requestDto.getGender(),
                requestDto.getLangCd(), null);
        Guest guestEntity = guestDto.toEntity();
        Long guestId = guestRepository.save(guestEntity).getId();

        Chk chkEntity = requestDto.toEntity();
        chkEntity.예약일_예약번호_예약상태_생성(guestId, sequence++);

        if (chkEntity.getMemoList().size() > 0) {
            List<ChkMemo> memoList = new ArrayList<>();
            for(ChkMemo memo : chkEntity.getMemoList()) {
                ChkMemo memoEntity = memo.toEntity();
                memoEntity.메모_기본값_생성(chkEntity.getRsvNum());
                memoList.add(memoEntity);
            }
            chkEntity.메모리스트_생성(memoList);
        }
        return chkRepository.save(chkEntity).getId();
    }
}