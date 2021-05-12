package edu.axboot.domain.chkmemo;

import edu.axboot.domain.SimpleJpaModel;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.chequer.axboot.core.annotations.Comment;
import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "PMS_CHK_MEMO")
@Comment(value = "")
@Alias("chkMemos")
public class ChkMemo extends SimpleJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@Comment(value = "ID")
	private Long id;

	@Column(name = "CHK_ID", precision = 19)
	@Comment(value = "투숙 ID")
	private Long chkId;

	@Column(name = "SNO", precision = 10, nullable = false)
	@Comment(value = "일련번호")
	private Integer sno;

	@Column(name = "MEMO_CN", length = 4000, nullable = false)
	@Comment(value = "메모 내용")
	private String memoCn;

	@Column(name = "MEMO_DTTI", nullable = false)
	@Comment(value = "메모 일시")
	private Timestamp memoDtti;

	@Column(name = "MEMO_MAN", length = 100, nullable = false)
	@Comment(value = "메모 자")
	private String memoMan;

	@Column(name = "DEL_YN", length = 1, nullable = false)
	@Comment(value = "삭제 여부")
	private String delYn;


    @Override
    public Long getId() {
        return id;
    }
}