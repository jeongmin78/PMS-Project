package edu.axboot.domain.chk;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChkRepository extends AXBootJPAQueryDSLRepository<Chk, Long> {
}
