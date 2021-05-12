package edu.axboot.domain.room;


import com.chequer.axboot.core.annotations.ColumnPosition;
import com.chequer.axboot.core.annotations.Comment;
import edu.axboot.domain.BaseJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "PMS_ROOM")
public class Room extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ColumnPosition(1)
	private Long id;

	@Column(name = "ROOM_NUM", length = 10, nullable = false)
	@Comment(value = "객실 번호")
	@ColumnPosition(2)
	private String roomNum;

	@Column(name = "ROOM_TYP_CD", length = 20, nullable = false)
	@Comment(value = "객실 타입 CD")
	@ColumnPosition(3)
	private String roomTypCd;

	@Column(name = "DND_YN", length = 1, nullable = false)
	@Comment(value = "DND 여부")
	@ColumnPosition(4)
	private String dndYn;

	@Column(name = "EB_YN", length = 1, nullable = false)
	@Comment(value = "EB 여부")
	@ColumnPosition(5)
	private String ebYn;

	@Column(name = "ROOM_STTUS_CD", length = 20)
	@Comment(value = "객실 상태 CD")
	@ColumnPosition(6)
	private String roomSttusCd;

	@Column(name = "CLN_STTUS_CD", length = 20)
	@Comment(value = "청소 상태 CD")
	@ColumnPosition(7)
	private String clnSttusCd;

	@Column(name = "SVC_STTUS_CD", length = 20)
	@Comment(value = "서비스 상태 CD")
	@ColumnPosition(8)
	private String svcSttusCd;

	@Override
	public Long getId() {
	return id;
	}

	@Builder
	public Room(Long id, String roomNum, String roomTypCd, String dndYn, String ebYn,
				String roomSttusCd, String clnSttusCd, String svcSttusCd,
				Boolean isCreated, Boolean isModified, Boolean isDeleted){
		this.id = id;
		this.roomNum = roomNum;
		this.roomTypCd = roomTypCd;
		this.dndYn = dndYn;
		this.ebYn = ebYn;
		this.roomSttusCd = roomSttusCd;
		this.clnSttusCd = clnSttusCd;
		this.svcSttusCd = svcSttusCd;
		this.__created__ = isCreated;
		this.__modified__ = isModified;
		this.__deleted__ = isDeleted;
	}

	public Room toEntity() {
		return Room.builder()
				.id(id)
				.roomNum(roomNum)
				.roomTypCd(roomTypCd)
				.dndYn(dndYn)
				.ebYn(ebYn)
				.roomSttusCd(roomSttusCd)
				.clnSttusCd(clnSttusCd)
				.svcSttusCd(svcSttusCd)
				.isCreated(__created__)
				.isModified(__modified__)
				.isDeleted(__deleted__)
				.build();
	}
}