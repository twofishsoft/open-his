package com.twofish.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 树
 * @author ww
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeVo {

    private Long id;

    private String label;

    private List<TreeVo> children;

    public TreeVo(Long id, String label) {
        this.id = id;
        this.label = label;
    }
}
