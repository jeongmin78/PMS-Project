package edu.axboot.domain.chkmemo;

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

    @Transactional
    public String saveUsingJpa(ChkMemoSaveRequestDto requestDto) {
        ChkMemo entity = requestDto.toEntity();
        return chkMemoRepository.save(entity).getRsvNum();
    }
}