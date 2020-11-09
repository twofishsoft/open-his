package com.twofish.controller.system;

import com.twofish.config.shiro.ShiroProperties;
import com.twofish.domain.Dept;
import com.twofish.domain.User;
import com.twofish.dto.UserDto;
import com.twofish.service.DeptService;
import com.twofish.service.DictDataService;
import com.twofish.service.UserService;
import com.twofish.utils.ShiroSecurityUtils;
import com.twofish.utils.Md5;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户数据接口
 * @author ww
 */
@RestController
@Log4j2
@Api(value = "用户数据接口",tags = "用户数据接口")
@RequestMapping("/system/user/")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private DictDataService dictDataService;
    @Resource
    private DeptService deptService;
    @Autowired
    private ShiroProperties shiroProperties;

    /**
     * 分页查询用户数据
     * @param userDto
     * @return
     */
    @GetMapping("listForPage")
    @ApiOperation(value = "分页查询用户数据", notes = "用户数据分页")
    public AjaxResult listForPage(UserDto userDto){
        DataGridView dataGridView = this.userService.listPage(userDto);
        List<User> data = (List<User>) dataGridView.getData();
        for (int i = 0; i < data.size(); i++) {
            User user = data.get(i);
            user.setStatusName(this.findDictDate("sys_normal_disable", user.getStatus()));
            user.setBackgroundName(this.findDictDate("sys_user_background", user.getBackground()));
            user.setLevelName(this.findDictDate("sys_user_level", user.getUserRank()));
            user.setSexName(this.findDictDate("sys_user_sex", user.getSex()));
            user.setSchedulingFlagName(this.findDictDate("his_scheduling_flag", user.getSchedulingFlag()));
            Dept dept = deptService.getOne(user.getDeptId());
            if (null != dept) {
                user.setDept(dept);
            }
        }
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    /**
     * 添加用户数据
     * @param userDto
     * @return
     */
    @PostMapping("addUser")
    @ApiOperation(value = "添加用户数据", notes = "添加用户数据")
    public AjaxResult addDictData(@RequestBody UserDto userDto){
        //设置添加人
        userDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        String hashAlgorithmName = shiroProperties.getHashAlgorithmName();
        Integer hashIterations = shiroProperties.getHashIterations();
        String psw = shiroProperties.getPsw();
        String salt = Md5.getSalt();
        userDto.setSalt(salt);
        userDto.setPassword(Md5.getPsw(hashAlgorithmName, hashIterations, psw, salt));
        return AjaxResult.toAjax(this.userService.insertUser(userDto));
    }

    /**
     * 更新用户数据
     * @param userDto
     * @return
     */
    @PutMapping("updateUser")
    @ApiOperation(value = "更新用户数据", notes = "更新用户数据")
    public AjaxResult updateUser(@RequestBody UserDto userDto){
        //设置修改人
        userDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.userService.updateUser(userDto));
    }

    /**
     * 删除用户数据(可批量删除)
     * @param userIds 用户数据ID数组
     * @return
     */
    @DeleteMapping("deleteUserByIds/{userIds}")
    @ApiOperation(value = "根据ID删除用户数据", notes = "根据ID删除用户数据")
    public AjaxResult deleteUserByIds(@PathVariable @Validated @NotEmpty(message = "用户ID不能为空") Long[] userIds){
        return AjaxResult.toAjax(this.userService.deleteUserByIds(userIds));
    }

    /**
     * 根据ID查询用户数据
     * @param userId 用户ID
     * @return
     */
    @GetMapping("getOne/{userId}")
    @ApiOperation(value = "根据ID查询用户数据", notes = "根据ID查询用户数据")
    public AjaxResult getOne(@PathVariable @Validated @NotNull(message = "用户ID不能为空") Long userId){
        return AjaxResult.success("查询成功", this.userService.getOne(userId));
    }

    /**
     * 修改用户密码
     * @param userDto
     * @return
     */
    @PutMapping("resetPass")
    @ApiOperation(value = "更新用户密码", notes = "更新用户密码")
    public AjaxResult resetPwd(@RequestBody UserDto userDto) {
        String hashAlgorithmName = shiroProperties.getHashAlgorithmName();
        Integer hashIterations = shiroProperties.getHashIterations();
        Long[] userIds = userDto.getUserIds();
        int result = -1;
        if (null != userIds && userIds.length != 0) {
            for (Long userId : userIds) {
                User user = this.userService.getOne(userId);
                if (null != user) {
                    String psw = Md5.getPsw(hashAlgorithmName, hashIterations, userDto.getPassword(), user.getSalt());
                    user.setPassword(psw);
                    //设置修改人
                    user.setUpdateBy(ShiroSecurityUtils.getCurrentSimpleUser().getUserName());
                    result = this.userService.resetPwd(user);
                }
            }
        }
        return AjaxResult.toAjax(result);
    }

    /**
     * 更新用户角色关系
     * @param userDto
     * @return
     */
    @PutMapping("updateRole")
    @ApiOperation(value = "更新用户角色关系", notes = "更新用户角色关系")
    public AjaxResult updateRole(@RequestBody UserDto userDto) {
        return AjaxResult.toAjax(userService.updateRole(userDto));
    }

    public String findDictDate(String dictType, String dictValue) {
        return dictDataService.queryDataByTypeAndValue(dictType, dictValue);
    }
}
