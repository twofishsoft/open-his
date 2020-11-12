package com.twofish.controller.system;

import com.twofish.config.shiro.ShiroProperties;
import com.twofish.domain.Dept;
import com.twofish.domain.SimpleUser;
import com.twofish.domain.User;
import com.twofish.dto.UserDto;
import com.twofish.service.DeptService;
import com.twofish.service.DictDataService;
import com.twofish.service.UserService;
import com.twofish.utils.Md5;
import com.twofish.utils.ShiroSecurityUtils;
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
import java.util.Arrays;
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
        data.stream().forEach(user -> {
            user.setStatusName(this.findDictDate("sys_normal_disable", user.getStatus()));
            user.setBackgroundName(this.findDictDate("sys_user_background", user.getBackground()));
            user.setLevelName(this.findDictDate("sys_user_level", user.getUserRank()));
            user.setSexName(this.findDictDate("sys_user_sex", user.getSex()));
            user.setSchedulingFlagName(this.findDictDate("his_scheduling_flag", user.getSchedulingFlag()));
            Dept dept = deptService.getOne(user.getDeptId());
            if (null != dept) {
                user.setDept(dept);
            }
        });
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    /**
     * 查询所有可用用户信息
     * @return
     */
    @GetMapping("selectAllUser")
    @ApiOperation(value = "查询所有可用用户信息", notes = "查询所有可用用户信息")
    public AjaxResult selectAllUser() {
        return AjaxResult.success(this.userService.selectAll());
    }

    /**
     * 查询需要排班的医生信息
     * @return
     */
    @GetMapping("getUsersNeedScheduling")
    @ApiOperation(value = "查询需要排班的医生信息", notes = "查询需要排班的医生信息")
    public AjaxResult getUsersNeedScheduling(Long deptId) {
        return AjaxResult.success(this.userService.getUsersNeedScheduling(deptId));
    }

    /**
     * 添加用户数据
     * @param userDto
     * @return
     */
    @PostMapping("addUser")
    @ApiOperation(value = "添加用户数据", notes = "添加用户数据")
    public AjaxResult addDictData(@RequestBody UserDto userDto){
        // 判断手机号是否重复
        User user = userService.querybyphone(userDto.getPhone());
        if (user != null) {
            return AjaxResult.fail("手机号已存在");
        }
        //设置添加人
        SimpleUser currentSimpleUser = ShiroSecurityUtils.getCurrentSimpleUser();
        userDto.setSimpleUser(currentSimpleUser);
        userDto.setCreateBy(currentSimpleUser.getUserName());
        String hashAlgorithmName = shiroProperties.getHashAlgorithmName();
        Integer hashIterations = shiroProperties.getHashIterations();
        String psw = shiroProperties.getPsw();
        String salt = Md5.getSalt();
        userDto.setSalt(salt);
        userDto.setPassword(Md5.getPswd(hashAlgorithmName, hashIterations, psw, salt));
        return AjaxResult.toAjax(this.userService.insert(userDto));
    }

    /**
     * 更新用户数据
     * @param userDto
     * @return
     */
    @PutMapping("updateUser")
    @ApiOperation(value = "更新用户数据", notes = "更新用户数据")
    public AjaxResult updateUser(@RequestBody UserDto userDto){
        // 判断手机号是否重复
        User user = userService.querybyphone(userDto.getPhone());
        if (user != null && !user.getUserId().equals(userDto.getUserId())) {
            return AjaxResult.fail("手机号已存在");
        }
        //设置修改人
        SimpleUser currentSimpleUser = ShiroSecurityUtils.getCurrentSimpleUser();
        userDto.setSimpleUser(currentSimpleUser);
        userDto.setUpdateBy(currentSimpleUser.getUserName());
        return AjaxResult.toAjax(this.userService.update(userDto));
    }

    /**
     * 删除用户数据(可批量删除)
     * @param userIds 用户数据ID数组
     * @return
     */
    @DeleteMapping("deleteUserByIds/{userIds}")
    @ApiOperation(value = "根据ID删除用户数据", notes = "根据ID删除用户数据")
    public AjaxResult deleteUserByIds(@PathVariable @Validated @NotEmpty(message = "用户ID不能为空") Long[] userIds){
        return AjaxResult.toAjax(this.userService.deleteByIds(userIds));
    }

    /**
     * 根据ID查询用户数据
     * @param userId 用户ID
     * @return
     */
    @GetMapping("getUserById/{userId}")
    @ApiOperation(value = "根据ID查询用户数据", notes = "根据ID查询用户数据")
    public AjaxResult getOne(@PathVariable @Validated @NotNull(message = "用户ID不能为空") Long userId){
        return AjaxResult.success("查询成功", this.userService.getOne(userId));
    }

    /**
     * 修改用户密码
     * @param userDto
     * @return
     */
    @PutMapping("resetPwd")
    @ApiOperation(value = "更新用户密码", notes = "更新用户密码")
    public AjaxResult resetPwd(@RequestBody UserDto userDto) {
        String hashAlgorithmName = shiroProperties.getHashAlgorithmName();
        Integer hashIterations = shiroProperties.getHashIterations();
        Long[] userIds = userDto.getUserIds();
        final int[] result = {-1};
        if (null != userIds && userIds.length != 0) {
            Arrays.stream(userIds).forEach(userId -> {
                User user = this.userService.getOne(userId);
                if (null != user) {
                    String psw = Md5.getPsw(hashAlgorithmName, hashIterations, userDto.getPassword(), user.getSalt());
                    user.setPassword(psw);
                    //设置修改人
                    user.setUpdateBy(ShiroSecurityUtils.getCurrentSimpleUser().getUserName());
                    result[0] = this.userService.resetPwd(user);
                }
            });
        }
        return AjaxResult.toAjax(result[0]);
    }

    /**
     * 更新用户角色关系
     * @param userDto
     * @return
     */
    @PostMapping("saveUserRole")
    @ApiOperation(value = "更新用户角色关系", notes = "更新用户角色关系")
    public AjaxResult updateRole(@RequestBody UserDto userDto) {
        return AjaxResult.toAjax(userService.updateRole(userDto));
    }

    /**
     * 根据用户手机号查询
     *
     * @param userPhone 用户id
     * @return 单个用户信息
     */
    @GetMapping("/getUserByPhone/{userPhone}")
    @ApiOperation(value = "根据用户ID查询用户信息", notes = "根据用户手机号查询用户信息")
    public AjaxResult getUserByPhone(@PathVariable("userPhone") String userPhone) {
        // 判断是否位空
        if (userPhone == null) {
            log.error("未接收到参数用户Phone，或参数错误");
            return AjaxResult.fail("参数错误");
        }
        AjaxResult ajax = AjaxResult.success();
        // 查询不为空返回，为空返回错误信息
        User one = userService.querybyphone(userPhone);
        log.debug("根据Phone查询用户的Phone：" + userPhone + "; 数据：" + one);
        if (one != null) {
            ajax.put("data", one);
            return ajax;
        } else {
            return AjaxResult.fail("查询的用户不存在");
        }
    }

    public String findDictDate(String dictType, String dictValue) {
        return dictDataService.queryDataByTypeAndValue(dictType, dictValue);
    }

}
