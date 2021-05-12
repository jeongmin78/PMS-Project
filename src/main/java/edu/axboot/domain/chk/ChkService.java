package edu.axboot.domain.chk;

import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import edu.axboot.domain.BaseService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ChkService extends BaseService<Chk, Long> {
    private ChkRepository chkRepository;

    @Inject
    public ChkService(ChkRepository chkRepository) {
        super(chkRepository);
        this.chkRepository = chkRepository;
    }

    public List<Chk> gets(RequestParams<Chk> requestParams) {
        return findAll();
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
    public void saveUsingQueryDsl(Chk request) {
        if (request.getId() == null || request.getId() == 0) {
            this.chkRepository.save(request);
        }
    }
}