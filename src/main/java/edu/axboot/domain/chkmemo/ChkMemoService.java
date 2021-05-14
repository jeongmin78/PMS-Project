package edu.axboot.domain.chkmemo;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.ChkMemoSaveRequestDto;
import edu.axboot.domain.chk.Chk;
import edu.axboot.domain.chk.ChkService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;

import javax.jdo.annotations.Transactional;

import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChkMemoService extends BaseService<ChkMemo, Long> {
    private static final Logger logger = LoggerFactory.getLogger(ChkService.class);

    private final ChkMemoRepository chkMemoRepository;

//    @Inject
//    public ChkMemoService(ChkMemoRepository chkMemoRepository) {
//        super(chkMemoRepository);
//        this.chkMemoRepository = chkMemoRepository;
//    }

    public List<ChkMemo> gets(RequestParams<ChkMemo> requestParams) {
        return findAll();
    }

    public List<ChkMemo> getList(RequestParams<ChkMemo> requestParams) {
        BooleanBuilder builder = new BooleanBuilder();

        List<ChkMemo> list = select()
                .from(qChkMemo)
                .where(builder)
                .orderBy(qChkMemo.id.asc())
                .fetch();

        return list;
    }

    @Transactional
    public String saveUsingJpa(ChkMemoSaveRequestDto requestDto) {
        ChkMemo entity = requestDto.toEntity();
        return chkMemoRepository.save(entity).getRsvNum();
    }
}