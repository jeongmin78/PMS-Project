package edu.axboot.domain.chkmemo;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChkMemoRepository extends AXBootJPAQueryDSLRepository<ChkMemo, Long> {
}
