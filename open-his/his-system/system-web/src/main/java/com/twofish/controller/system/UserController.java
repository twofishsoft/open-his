package com.twofish.controller.system;

import com.twofish.annotation.CurrUser;
import com.twofish.config.shiro.ShiroProperties;
import com.twofish.domain.User;
import com.twofish.dto.UserDto;
import com.twofish.service.UserService;
import com.twofish.utils.Md5;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Arrays;

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
    public AjaxResult addDictData(@CurrUser UserDto userDto){
        // 判断手机号是否重复
        User user = userService.queryByPhone(userDto.getPhone());
        if (user != null) {
            return AjaxResult.fail("手机号已存在");
        }
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
    public AjaxResult updateUser(@CurrUser(name = "updateBy") UserDto userDto){
        // 判断手机号是否重复
        User user = userService.queryByPhone(userDto.getPhone());
        if (user != null && !user.getUserId().equals(userDto.getUserId())) {
            return AjaxResult.fail("手机号已存在");
        }
        return AjaxResult.toAjax(this.userService.update(userDto));
    }

    /**
     * 删除用户数据(可批量删除)
     * @param userIds 用户数据ID数组
     * @return
     */
    @DeleteMapping("deleteUserByIds/{userIds}")
    @ApiOperation(value = "根据ID删除用户数据", notes = "根据ID删除用户数据")
    public AjaxResult deleteUserByIds(@PathVariable Long[] userIds){
        return AjaxResult.toAjax(this.userService.deleteByIds(userIds));
    }

    /**
     * 根据ID查询用户数据
     * @param userId 用户ID
     * @return
     */
    @GetMapping("getUserById/{userId}")
    @ApiOperation(value = "根据ID查询用户数据", notes = "根据ID查询用户数据")
    public AjaxResult getOne(@PathVariable Long userId){
        return AjaxResult.success("查询成功", this.userService.getOneById(userId));
    }

    /**
     * 修改用户密码
     * @param userDto
     * @return
     */
    @PutMapping("resetPwd")
    @ApiOperation(value = "更新用户密码", notes = "更新用户密码")
    public AjaxResult resetPwd(@CurrUser(name = "updateBy") UserDto userDto) {
        String hashAlgorithmName = shiroProperties.getHashAlgorithmName();
        Integer hashIterations = shiroProperties.getHashIterations();
        Long[] userIds = userDto.getUserIds();
        final int[] result = {-1};
        if (null != userIds && userIds.length != 0) {
            Arrays.stream(userIds).forEach(userId -> {
                User user = this.userService.getOneById(userId);
                if (null != user) {
                    user.setPassword(Md5.getPsw(hashAlgorithmName, hashIterations, userDto.getPassword(), user.getSalt()));
                    result[0] = this.userService.updateById(user);
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
     * @param userPhone 用户手机
     * @return 单个用户信息
     */
    @GetMapping("/getUserByPhone/{userPhone}")
    @ApiOperation(value = "根据用户ID查询用户信息", notes = "根据用户手机号查询用户信息")
    public AjaxResult getUserByPhone(@PathVariable String userPhone) {
        return AjaxResult.success(userService.queryByPhone(userPhone));
    }

}
