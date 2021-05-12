package edu.axboot.domain.chkmemo;

import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class ChkMemoService extends BaseService<ChkMemo, Long> {
    private ChkMemoRepository chkMemoRepository;

    @Inject
    public ChkMemoService(ChkMemoRepository chkMemoRepository) {
        super(chkMemoRepository);
        this.chkMemoRepository = chkMemoRepository;
    }

    public List<ChkMemo> gets(RequestParams<ChkMemo> requestParams) {
        return findAll();
    }
}