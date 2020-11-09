package com.twofish.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 构造菜单返回前台的vo
 * @author ccy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeVo {

    private String id;//菜单id

    private String serPath;//菜单路径url

    private boolean show = true;//是否显示

    public MenuTreeVo(String id, String serPath) {
        this.id = id;
        this.serPath = serPath;
    }
}
