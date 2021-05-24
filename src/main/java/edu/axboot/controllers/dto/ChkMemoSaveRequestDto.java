package edu.axboot.controllers.dto;

import edu.axboot.domain.chkmemo.ChkMemo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ChkMemoSaveRequestDto {
    private Long id;
    private String memoCn;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    @Builder
    public ChkMemoSaveRequestDto(Long id, String memoCn, boolean __created__, boolean __modified__, boolean __deleted__) {
        this.id = id;
        this.memoCn = memoCn;
        this.__created__ = __created__;
        this.__modified__ = __modified__;
        this.__deleted__ = __deleted__;
    }

    public ChkMemo toEntity() {
        return ChkMemo.builder()
                .id(id)
                .memoCn(memoCn)
                .isCreated(__created__)
                .isModified(__modified__)
                .isDeleted(__deleted__)
                .build();
    }
}
