package edu.axboot.domain.guest;

import com.chequer.axboot.core.annotations.ColumnPosition;
import com.chequer.axboot.core.annotations.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.axboot.controllers.dto.GuestUpdateRequestDto;
import edu.axboot.domain.BaseJpaModel;
import edu.axboot.domain.chk.Chk;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "PMS_GUEST")
public class Guest extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ColumnPosition(1)
	private Long id;

	@Column(name = "GUEST_NM", length = 100, nullable = false)
	@Comment(value = "투숙객 명")
	@ColumnPosition(2)
	private String guestNm;

	@Column(name = "GUEST_NM_ENG", length = 100)
	@Comment(value = "투숙객 명 영어")
	@ColumnPosition(3)
	private String guestNmEng;

	@Column(name = "GUEST_TEL", length = 18)
	@Comment(value = "투숙객 전화")
	@ColumnPosition(4)
	private String guestTel;

	@Column(name = "EMAIL", length = 100)
	@Comment(value = "이메일")
	@ColumnPosition(5)
	private String email;

	@Column(name = "BRTH", length = 10)
	@Comment(value = "생일")
	@ColumnPosition(6)
	private String brth;

	@Column(name = "GENDER", length = 20)
	@Comment(value = "성별")
	@ColumnPosition(7)
	private String gender;

	@Column(name = "LANG_CD", length = 20)
	@Comment(value = "언어 CD")
	@ColumnPosition(8)
	private String langCd;

	@Column(name = "RMK", length = 500)
	@Comment(value = "비고")
	@ColumnPosition(9)
	private String rmk;

	@OneToMany
	@JoinColumn(name="ID")
	private List<Chk> chks = new ArrayList<>();

	@Override
    public Long getId() {
        return id;
    }

    @Builder
	public Guest(Long id, String guestNm, String guestNmEng, String guestTel, String email,
				 String brth, String gender, String langCd, String rmk) {
    	this.id = id;
    	this.guestNm = guestNm;
    	this.guestNmEng = guestNmEng;
    	this.guestTel = guestTel;
    	this.email = email;
    	this.brth = brth;
    	this.gender = gender;
    	this.langCd = langCd;
    	this.rmk = rmk;
	}

	public void 고객정보_수정하기(GuestUpdateRequestDto requestDto) {
		this.guestNm = requestDto.getGuestNm();
		this.guestNmEng = requestDto.getGuestNmEng();
		this.guestTel = requestDto.getGuestTel();
		this.email = requestDto.getEmail();
		this.brth = requestDto.getBrth();
		this.gender = requestDto.getGender();
		this.langCd = requestDto.getLangCd();
		this.rmk = requestDto.getRmk();
    }
}