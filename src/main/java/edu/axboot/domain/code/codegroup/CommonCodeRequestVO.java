package edu.axboot.domain.code.codegroup;

import lombok.Data;

import java.util.List;

@Data
public class CommonCodeRequestVO {
    private List<CommonCodeGroup> list;
    private List<CommonCodeGroup> deletedList;
}
