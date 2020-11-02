package com.twofish.vo;

import com.twofish.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 用户角色权限装载类
 *
 * @author ccy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivierUser implements Serializable {

    /**
     * 用户
     */
    private User user;
    /**
     * 角色
     */
    private List<String> roles = Collections.EMPTY_LIST;

    /**
     * 权限
     */
    private List<String> permissions = Collections.EMPTY_LIST;


}
