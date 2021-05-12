package edu.axboot.domain.chk;

import com.chequer.axboot.core.annotations.ColumnPosition;
import edu.axboot.domain.BaseJpaModel;
import edu.axboot.domain.chkmemo.ChkMemo;
import edu.axboot.domain.guest.Guest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@SequenceGenerator(
		name="SNO_SEQ_GENERATOR",
		sequenceName = "SNO",
		initialValue = 1,
		allocationSize = 50)
@NoArgsConstructor
@Table(name = "PMS_CHK")
public class Chk extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ColumnPosition(1)
	private Long id;

	@Column(name = "RSV_DT", length = 10, nullable = false)
	@ColumnPosition(2)
	private String rsvDt;

	@Column(name = "SNO", precision = 10, nullable = false)
	@ColumnPosition(3)
	private Integer sno;

	@Column(name = "RSV_NUM", length = 20, nullable = false)
	@ColumnPosition(4)
	private String rsvNum;

	@Column(name = "GUEST_ID", precision = 19)
	@ColumnPosition(5)
	private Long guestId;

	@Column(name = "GUEST_NM", length = 100)
	@ColumnPosition(6)
	private String guestNm;

	@Column(name = "GUEST_NM_ENG", length = 200)
	@ColumnPosition(7)
	private String guestNmEng;

	@Column(name = "GUEST_TEL", length = 18)
	@ColumnPosition(8)
	private String guestTel;

	@Column(name = "EMAIL", length = 100)
	@ColumnPosition(9)
	private String email;

	@Column(name = "LANG_CD", length = 20)
	@ColumnPosition(10)
	private String langCd;

	@Column(name = "ARR_DT", length = 10, nullable = false)
	@ColumnPosition(11)
	private String arrDt;

	@Column(name = "ARR_TIME", length = 8)
	@ColumnPosition(12)
	private String arrTime;

	@Column(name = "DEP_DT", length = 10, nullable = false)
	@ColumnPosition(13)
	private String depDt;

	@Column(name = "DEP_TIME", length = 8)
	@ColumnPosition(14)
	private String depTime;

	@Column(name = "NIGHT_CNT", precision = 10, nullable = false)
	@ColumnPosition(15)
	private Integer nightCnt;

	@Column(name = "ROOM_TYP_CD", length = 20, nullable = false)
	@ColumnPosition(16)
	private String roomTypCd;

	@Column(name = "ROOM_NUM", length = 10)
	@ColumnPosition(17)
	private String roomNum;

	@Column(name = "ADULT_CNT", precision = 10, nullable = false)
	@ColumnPosition(18)
	private Integer adultCnt;

	@Column(name = "CHLD_CNT", precision = 10, nullable = false)
	@ColumnPosition(19)
	private Integer chldCnt;

	@Column(name = "SALE_TYP_CD", length = 20, nullable = false)
	@ColumnPosition(20)
	private String saleTypCd;

	@Column(name = "STTUS_CD", length = 20, nullable = false)
	@ColumnPosition(21)
	private String sttusCd;

	@Column(name = "SRC_CD", length = 20, nullable = false)
	@ColumnPosition(22)
	private String srcCd;

	@Column(name = "BRTH", length = 10)
	@ColumnPosition(23)
	private String brth;

	@Column(name = "GENDER", length = 20)
	@ColumnPosition(24)
	private String gender;

	@Column(name = "PAY_CD", length = 20)
	@ColumnPosition(25)
	private String payCd;

	@Column(name = "ADVN_YN", length = 1, nullable = false)
	@ColumnPosition(26)
	private String advnYn;

	@Column(name = "SALE_PRC", precision = 18, scale = 0)
	@ColumnPosition(27)
	private BigDecimal salePrc;

	@Column(name = "SVC_PRC", precision = 18, scale = 0)
	@ColumnPosition(28)
	private BigDecimal svcPrc;

    @Override
    public Long getId() {
        return id;
    }

	@Transient
	private List<Long> fileIdList = new ArrayList<>();

    @Builder
	public Chk(Long id, String rsvDt, Integer sno, String rsvNum, Long guestId, String guestNm,
			   String guestNmEng, String guestTel, String email, String langCd,
			   String arrDt, String arrTime, String depDt, String depTime,
			   Integer nightCnt, String roomTypCd, String roomNum,
			   Integer adultCnt, Integer chldCnt, String saleTypCd,
			   String sttusCd, String srcCd, String brth, String gender,
			   String payCd, String advnYn, BigDecimal salePrc, BigDecimal svcPrc,
			   boolean isCreated, boolean isModified, boolean isDeleted) {
    	this.id = id;
    	this.rsvDt = rsvDt;
    	this.sno = sno;
    	this.rsvNum = rsvNum;
    	this.guestId = guestId;
    	this.guestNm = guestNm;
    	this.guestNmEng = guestNmEng;
    	this.guestTel = guestTel;
    	this.email = email;
    	this.langCd = langCd;
    	this.arrDt= arrDt;
    	this.arrTime = arrTime;
    	this.depDt = depDt;
    	this.depTime = depTime;
    	this.nightCnt = nightCnt;
    	this.roomTypCd = roomTypCd;
    	this.roomNum = roomNum;
    	this.adultCnt = adultCnt;
    	this.chldCnt = chldCnt;
    	this.saleTypCd = saleTypCd;
    	this.sttusCd = sttusCd;
    	this.srcCd = srcCd;
    	this.brth = brth;
    	this.gender = gender;
    	this.payCd = payCd;
    	this.advnYn = advnYn;
    	this.salePrc = salePrc;
    	this.svcPrc = svcPrc;
		this.__created__ = isCreated;
		this.__modified__ = isModified;
		this.__deleted__ = isDeleted;
	}
	private static final Logger logger = LoggerFactory.getLogger(ChkService.class);

	public void  예약일_예약번호_예약상태_생성(int sequence) {
		LocalDate today = LocalDate.now();
		logger.info("\n===============================" + today);
		String leftpad = StringUtils.leftPad(String.valueOf(sequence),3,"0");
		DateTimeFormatter numbering = DateTimeFormatter.ofPattern("yyyyMMdd");

		this.rsvDt = String.valueOf(today);
    	this.sno = sequence;
    	this.rsvNum = "R" + today.format(numbering) + leftpad;
    	this.sttusCd = "RSV_01";
	}

	@OneToMany(mappedBy = "chk")
	private List<ChkMemo> memoList = new ArrayList<ChkMemo>();

}