package com.twofish.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.domain.User;
import com.twofish.service.UserService;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户接口
 *
 * @author Ytyy.
 * @version 1.0
 * @date 2020/11/1 19:27
 */
@RestController
@Slf4j
@Api(value = "用户接口", tags = "用户接口")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 根据用户id查询
     *
     * @param userId 用户id
     * @return 单个用户信息
     */
    @GetMapping("/system/user/getUserById/{userId}")
    @ApiOperation(value = "根据用户ID查询用户信息", notes = "根据用户ID查询用户信息")
    public AjaxResult getUserById(@PathVariable("userId") Long userId) {
        if (userId == null || userId <= 0L) {
            log.error("未接收到参数用户id，或参数错误");
            return AjaxResult.error("参数错误");
        }
        AjaxResult ajax = AjaxResult.success();

        User one = userService.getOne(userId);
        log.info("根据id查询用户的id：" + userId + "; 数据：" + one);
        if (one != null) {
            ajax.put("data", one);
            return ajax;
        } else {
            return AjaxResult.error("用户不存在");
        }

    }
//
//    /**
//     * 根据用户手机号查询
//     *
//     * @param userPhone 用户id
//     * @return 单个用户信息
//     */
//    @GetMapping("/system/user/getUserByPhone/{userPhone}")
//    @ApiOperation(value = "根据用户ID查询用户信息", notes = "根据用户手机号查询用户信息")
//    public AjaxResult getUserByPhone(@PathVariable("userPhone") String userPhone) {
//        if (userPhone == null) {
//            log.error("未接收到参数用户Phone，或参数错误");
//            return AjaxResult.error("参数错误");
//        }
//        AjaxResult ajax = AjaxResult.success();
//
//        User one = userService.queryByPhone(userPhone);
//        log.debug("根据Phone查询用户的Phone：" + userPhone + "; 数据：" + one);
//        if (one != null) {
//            ajax.put("data", one);
//            return ajax;
//        } else {
//            return AjaxResult.error("用户不存在");
//        }
//    }

//    /**
//     * 分页查询用户
//     *
//     * @param pageNum  开始页
//     * @param pageSize 每页多少条
//     * @return 用户集合
//     */
//    @GetMapping("/system/user/listUserForPage")
//    @ApiOperation(value = "分页查询用户信息", notes = "分页查询用户信息")
//    public AjaxResult pageUser(Integer pageNum, Integer pageSize, User user, Date beginTime, Date endTime) {
//        if (pageNum == null || pageNum <= 0) {
//            return AjaxResult.error("参数错误");
//        }
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        AjaxResult ajax = AjaxResult.success();
//        DataGridView gridView = new DataGridView();
//
//        // TODO 接口没写明白还会传写啥
//
//        Page<User> userPage = new Page<>();
//        userPage.setCurrent(pageNum);
//
//        if (pageSize != null) {
//            userPage.setSize(pageSize);
//        }
//        List<User> userByPage = userService.getUserByPage(userPage);
//
//        // 总条数
//        gridView.setTotal(userPage.getTotal());
//        gridView.setData(userByPage);
//
//        ajax.put("data", gridView);
//
//        return ajax;
//    }
//
//    /**
//     * 用户添加
//     *
//     * @param user 用户提示
//     * @return AjaxResult
//     */
//    @PostMapping(value = "/system/user/addUser", produces = "application/json;charset=UTF-8")
//    @ApiOperation(value = "添加用户信息", notes = "添加用户信息")
//    public AjaxResult addUser(User user) {
//        if (user == null) {
//            return AjaxResult.error("参数错误");
//        }
//        // 判断手机号是否重复
//        User queryByPhone = userService.queryByPhone(user.getPhone());
//        if (queryByPhone != null){
//            return AjaxResult.fail("手机号已存在");
//        }
//
//
//        // TODO  未知的密码加密
//        Integer integer = userService.addUser(user);
//        return AjaxResult.toAjax(integer);
//    }
//
//    @DeleteMapping(value = "/system/user/deleteUserByIds/{userIds}")
//    @ApiOperation(value = "删除用户", notes = "删除用户")
//    public AjaxResult deleteUserById(@PathVariable("userIds") Integer userIds) {
//        if (userIds == null) {
//            return AjaxResult.error("参数错误");
//        }
//
//        Integer integer = userService.deleteUserById(userIds);
//        log.info("删除用户的id：" + userIds + "; 影响行数 : " + integer);
//        return AjaxResult.toAjax(integer);
//    }


}
