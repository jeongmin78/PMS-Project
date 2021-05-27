package edu.axboot.domain.code.codegroup;

import com.chequer.axboot.core.annotations.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.axboot.domain.BaseJpaModel;
import edu.axboot.domain.code.CommonCode;
import edu.axboot.domain.program.menu.Menu;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "COMMON_GROUP")
public class CommonCodeGroup extends BaseJpaModel<Long> {

	@Id
	@Column(name = "GROUP_ID", precision = 19, nullable = false)
	@Comment(value = "GROUP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long groupId;

	@Column(name = "GROUP_CD", length = 100, nullable = false)
	@Comment(value = "GROUP_CD")
	private String groupCd;

	@Column(name = "GROUP_NM", length = 100, nullable = false)
	@Comment(value = "GROUP_NM")
	private String groupNm;

	@Column(name = "PARENT_ID", precision = 19)
	@Comment(value = "PARENT_ID")
	private Long parentId;

	@Column(name = "LEVEL", precision = 10, nullable = false)
	@Comment(value = "LEVEL")
	private Integer level;

	@Column(name = "SORT", precision = 10, nullable = false)
	@Comment(value = "SORT")
	private Integer sort;

	@Column(name = "ROOT_CD", length = 100, nullable = false)
	@Comment(value = "ROOT_CD")
	private String rootCd;

	@Column(name = "RMK", length = 255)
	@Comment(value = "비고 ")
	private String rmk;

	@Column(name = "USE_YN", length = 1, nullable = false)
	@Comment(value = "사용 유무 ")
	private String useYn;

    @Override
    public Long getId() {
        return groupId;
    }

	@JsonProperty("name")
	public String label() {
		return groupNm;
	}

	@JsonProperty("id")
	public Long id() {
		return groupId;
	}

	@JsonProperty("open")
	public boolean isOpen() {
		return open;
	}

	@Transient
	private boolean open = false;

	@Transient
	private List<CommonCodeGroup> children = new ArrayList<>();

	public void addChildren(CommonCodeGroup codeGroup) {
		children.add(codeGroup);
	}
	public CommonCodeGroup clone() {
		try {
			CommonCodeGroup group = (CommonCodeGroup) super.clone();
			group.setChildren(new ArrayList<>());
			return group;
		} catch (Exception e) {
			// ignore
		}
		return null;
	}
}