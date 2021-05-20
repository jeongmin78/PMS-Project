package edu.axboot.domain.chk;

import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.axboot.controllers.dto.*;
import edu.axboot.domain.BaseService;
import edu.axboot.domain.chkmemo.ChkMemo;
import edu.axboot.domain.chkmemo.ChkMemoRepository;
import edu.axboot.domain.chkmemo.ChkMemoService;
import edu.axboot.domain.guest.Guest;
import edu.axboot.domain.guest.GuestRepository;
import edu.axboot.domain.guest.GuestService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.CDATA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import system.DateTime;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChkService extends BaseService<Chk, Long> {
    private static final Logger logger = LoggerFactory.getLogger(ChkService.class);

    private final ChkRepository chkRepository;

    private final ChkMemoRepository chkmemoRepository;

    private final GuestRepository guestRepository;

    private final GuestService guestService;

    private static int sequence;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate today = LocalDate.now();

    public List<Chk> gets(RequestParams<Chk> requestParams) {
        return findAll();
    }
    public ChkResponseDto getOneById(Long id) {
        Chk entity = chkRepository.findOne(id);
        if (entity == null){
            throw new IllegalArgumentException("해당 예약내역이 없습니다. id=" + id);
        }
        return new ChkResponseDto(entity);
    }
    public List<Chk> getList(RequestParams<Chk> requestParams) {
        String filter = requestParams.getFilter();
        String rsvNum = requestParams.getString("rsvNum","");
        String rsvDt = requestParams.getString("rsvDt","");
        String rsvDtEnd = requestParams.getString("rsvDtEnd","");
        String roomTypCd = requestParams.getString("roomTypCd","");
        String arrDt = requestParams.getString("arrDt","");
        String arrDtEnd = requestParams.getString("arrDtEnd","");
        String depDt = requestParams.getString("depDt","");
        String depDtEnd = requestParams.getString("depDtEnd","");
        String sttusCd = requestParams.getString("sttusCd","");
        String sttusCdArray[] = sttusCd.split(",");

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(rsvNum)){
            builder.and(qChk.rsvNum.eq(rsvNum));
        }
        if (isNotEmpty(rsvDt)){
            if (isNotEmpty(rsvDtEnd)){
                builder.and(qChk.rsvDt.between(rsvDt,rsvDtEnd));
            }else
                builder.and(qChk.rsvDt.eq(rsvDt));
        }
        if (isNotEmpty(roomTypCd)){
            builder.and(qChk.roomTypCd.eq(roomTypCd));
        }
        if (isNotEmpty(arrDt)){
            if (isNotEmpty(arrDtEnd)) {
                builder.and(qChk.arrDt.between(arrDt, arrDtEnd));
            }else
                builder.and(qChk.arrDt.eq(arrDt));
        }
        if (isNotEmpty(depDt)){
            if (isNotEmpty(depDtEnd)){
                builder.and(qChk.depDt.between(depDt,depDtEnd));
            }else
                builder.and(qChk.depDt.eq(depDt));
        }
        if (isNotEmpty(sttusCd)){
            for (int i=0; i<sttusCdArray.length; i++){
                builder.and(qChk.sttusCd.eq(sttusCdArray[i]));
            }
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
        if (requestDto.is__created__()) {
            Long guestId;
            if (requestDto.getGuestId() == null || requestDto.getGuestId() == 0) {
                Guest guest = new GuestSaveRequestDto(null,
                        requestDto.getGuestNm(), requestDto.getGuestNmEng(), requestDto.getGuestTel(),
                        requestDto.getEmail(), requestDto.getBrth(), requestDto.getGender(),
                        requestDto.getLangCd(), null).toEntity();
                guestId = guestRepository.save(guest).getId();
            } else {
                guestId = requestDto.getGuestId();
                GuestUpdateRequestDto guestDto = new GuestUpdateRequestDto(requestDto.getGuestNm(),
                        requestDto.getGuestNmEng(), requestDto.getGuestTel(), requestDto.getEmail(),
                        requestDto.getBrth(), requestDto.getGender(),
                        requestDto.getLangCd(), null);
                guestService.update(guestId, guestDto);
            }
            Chk chkEntity = requestDto.toEntity();
            시리얼_넘버();
            chkEntity.예약일_예약번호_예약상태_생성(guestId, sequence);

            if (chkEntity.getMemoList().size() > 0) {
                List<ChkMemo> memoList = new ArrayList<>();
                for (ChkMemo memo : chkEntity.getMemoList()) {
                    ChkMemo memoEntity = memo.toEntity();
                    memoEntity.메모_기본값_생성(chkEntity.getRsvNum());
                    memoList.add(memoEntity);
                }
                chkEntity.메모리스트_생성(memoList);
            }
            return chkRepository.save(chkEntity).getId();
        }
        return null;
    }

    public ChkResponseDto getOneByDesc() {
        JPAQueryFactory query = new JPAQueryFactory(em);

        Chk chk = query.selectFrom(qChk)
                .orderBy(qChk.id.desc())
                .fetchFirst();
        if (chk != null) {
            return new ChkResponseDto(chk);
        }
        else
            return null;
    }

    public void 시리얼_넘버() {
        ChkResponseDto chkResponseDto = getOneByDesc();
        if (chkResponseDto != null) {
            String date = chkResponseDto.getRsvDt();
            if (date.equals(String.valueOf(today))) {
                sequence = chkResponseDto.getSno()+1;
            } else {
                sequence = 1;
            }
        } else {
            sequence = 1;
        }
    }

    @Transactional
    public Long update(Long id, ChkUpdateRequestDto requestDto) {
        Chk chkEntity = chkRepository.findOne(id);
        if (chkEntity == null) {
            throw new IllegalArgumentException("해당 예약내역이 없습니다. id=" + id);
        }

        if (requestDto.getMemoList().size() > 0) {
            List<ChkMemo> memoList = new ArrayList<>();
            for (ChkMemo memo : requestDto.getMemoList()) {
                if (memo.is__created__()){
                    ChkMemo memoEntity = memo.toEntity();
                    memoEntity.메모_기본값_생성(chkEntity.getRsvNum());
                    memoList.add(memoEntity);
                    chkmemoRepository.save(memoEntity);
                }
            }
            chkEntity.메모리스트_생성(memoList);

        }
        chkEntity.예약정보_수정하기(requestDto);
        return id;
    }
}