package edu.axboot.domain.chk;

import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.axboot.controllers.dto.*;
import edu.axboot.domain.BaseService;
import edu.axboot.domain.chkmemo.ChkMemo;
import edu.axboot.domain.chkmemo.ChkMemoRepository;
import edu.axboot.domain.guest.Guest;
import edu.axboot.domain.guest.GuestRepository;
import edu.axboot.domain.guest.GuestService;
import edu.axboot.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChkService extends BaseService<Chk, Long> {
    private static final Logger logger = LoggerFactory.getLogger(ChkService.class);

    private final ChkRepository chkRepository;

    private final ChkMemoRepository chkmemoRepository;

    private final GuestRepository guestRepository;

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
            builder.and(qChk.sttusCd.eq(sttusCdArray[0]));
            for (int i=1; i<sttusCdArray.length; i++){
                builder.or(qChk.sttusCd.eq(sttusCdArray[i]));
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
    public long saveUsingJpa(ChkSaveRequestDto requestDto) {
        long id=0;
        Guest guest = Guest.builder()
                .id(requestDto.getGuestId())
                .guestNm(requestDto.getGuestNm())
                .guestNmEng(requestDto.getGuestNmEng())
                .guestTel(requestDto.getGuestTel())
                .email(requestDto.getEmail())
                .brth(requestDto.getBrth())
                .gender(requestDto.getGender())
                .langCd(requestDto.getLangCd())
                .build();

        Long guestId = guestRepository.save(guest).getId();

        Chk chk = null;
        if (requestDto.getId() == null) {
            chk = requestDto.toEntity();
            chk.투숙객번호갱신(guestId);

            int sno = 1;
            ChkResponseDto todayLastChk = getOneByDesc();
            if (todayLastChk != null) {
                String date = todayLastChk.getRsvDt();
                if (date.equals(String.valueOf(today))) {
                    sno = todayLastChk.getSno()+1;
                }
            }
            chk.예약일_예약번호_예약상태_생성(guestId, sno);
            id = chkRepository.save(chk).getId();
        }
        logger.info("rsvNum ==============> " + chk.getRsvNum());
        if (requestDto.getMemoList() != null)
            this.saveToMemo(chk.getRsvNum(), requestDto.getMemoList());

//            시리얼_넘버();
//            chk.예약일_예약번호_예약상태_생성(guestId, sequence);
//
//            this.saveToMemo(chk.getRsvNum(), requestDto.getMemoList());
//            if (chkEntity.getMemoList().size() > 0) {
//                List<ChkMemo> memoList = new ArrayList<>();
//                for (ChkMemo memo : chkEntity.getMemoList()) {
//                    ChkMemo memoEntity = memo.toEntity();
//                    memoEntity.메모_기본값_생성(chkEntity.getRsvNum());
//                    memoList.add(memoEntity);
//                }
//                chkEntity.메모리스트_생성(memoList);
//            }
//            return chkRepository.save(chk).getId();
//        }
        return id;
    }

    private void saveToMemo(String rsvNum, List<ChkMemoSaveRequestDto> memoDtoList) {
        for (ChkMemoSaveRequestDto memoDto : memoDtoList) {
            if (memoDto.is__created__()) {
                ChkMemo lastChkMemo = select().select(
                        Projections.fields(ChkMemo.class, qChkMemo.sno))
                        .from(qChkMemo)
                        .where(qChkMemo.rsvNum.eq(rsvNum))
                        .orderBy(qChkMemo.sno.desc())
                        .fetchFirst();

                int snoMemo = 1;
                if (lastChkMemo != null) {
                    snoMemo = lastChkMemo.getSno() + 1;
                }
                ChkMemo memoEntity = ChkMemo.builder()
                        .rsvNum(rsvNum)
                        .sno(snoMemo)
                        .memoCn(memoDto.getMemoCn())
                        .memoDtti(Timestamp.valueOf(LocalDateTime.now()))
                        .memoMan(SessionUtils.getCurrentLoginUserCd())
                        .delYn("N")
                        .build();
//                        memo.toEntity();
//                memoEntity.메모_기본값_생성(rsvNum);

                chkmemoRepository.save(memoEntity);
            } else if (memoDto.is__modified__()) {
                ChkMemo memo = chkmemoRepository.findOne(memoDto.getId());
                memo.update(memoDto.getMemoCn());
            } else if (memoDto.is__deleted__()) {
                ChkMemo memo = chkmemoRepository.findOne(memoDto.getId());
                memo.delete();
            }
        }
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

    @Transactional
    public Long update(Long id, ChkUpdateRequestDto requestDto) {
        Chk chkEntity = chkRepository.findOne(id);
        if (chkEntity == null) {
            throw new IllegalArgumentException("해당 예약내역이 없습니다. id=" + id);
        }

        chkEntity.예약정보_수정하기(requestDto);
        return id;
    }

    @Transactional
    public void updateSttus(Long id, String sttusCd) {
        Chk chkEntity = chkRepository.findOne(id);
        if (chkEntity == null) {
            throw new IllegalArgumentException("해당 예약내역이 없습니다. id=" + id);
        }

        chkEntity.예약상태_수정하기(sttusCd);
    }

    public List<ChkReportListResponseDto> getArrDtTotalCount(RequestParams<ChkReportListResponseDto> requestParams) {
        String arrDt = requestParams.getString("arrDt","");
        String arrDtEnd = requestParams.getString("arrDtEnd","");

        logger.info("arrDt ====> " + arrDt);
        logger.info("arrDtEnd ====> " + arrDtEnd);

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(arrDt)){
            if (isNotEmpty(arrDtEnd)){
                builder.and(qChk.arrDt.between(arrDt,arrDtEnd));
            }else
                builder.and(qChk.arrDt.between(arrDt, String.valueOf(today)));
        }

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<ChkReportListResponseDto> chkReportList = queryFactory.selectFrom(qChk)
                .where(builder)
                .groupBy(qChk.arrDt)
                .select(Projections.constructor(ChkReportListResponseDto.class, qChk.arrDt, qChk.rsvNum.count(), qChk.salePrc.sum(), qChk.svcPrc.sum()))
                .orderBy(qChk.rsvNum.count().desc())
                .fetch();

        long count=0;
        BigDecimal salePrc = BigDecimal.ZERO;
        BigDecimal svcPrc = BigDecimal.ZERO;
        for (ChkReportListResponseDto chkReport : chkReportList){
            logger.info("===============> " + chkReport.getSalePrc());
            logger.info("===============> " + chkReport.getSvcPrc());
            count += chkReport.getRoomCount();
            if (chkReport.getSalePrc() != null) salePrc = salePrc.add(chkReport.getSalePrc());
            if (chkReport.getSvcPrc() != null) svcPrc = svcPrc.add(chkReport.getSvcPrc());
        }
        ChkReportListResponseDto reportDto = new ChkReportListResponseDto("합계", count, salePrc,svcPrc);
        chkReportList.add(0, reportDto);
        return chkReportList;
    }
}