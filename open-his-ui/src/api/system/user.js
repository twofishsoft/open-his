import request from '@/utils/request'

// 查询用户列表
export function listUser(query) {
  return request({
    url: '/system/user/listForPage',
    method: 'get',
    params: query
  })
}

// 新增用户
export function addUser(data) {
  return request({
    url: '/system/user/addUser',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateUser(data) {
  return request({
    url: '/system/user/updateUser',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delUser(userId) {
  return request({
    url: '/system/user/deleteUserByIds/' + userId,
    method: 'delete'
  })
}

// 查询用户个人信息
export function getOne(userId) {
  return request({
    url: '/system/user/getOne/' + userId,
    method: 'get'
  })
}

// 用户密码重置
export function resetUserPwd(userIds, password) {
  const data = {
    userIds,
    password
  }
  return request({
    url: '/system/user/resetPass',
    method: 'put',
    data: data
  })
}

// 更新用户角色
export function updateRole(userIds, roleIds) {
  const data = {
    userIds,
    roleIds
  }
  return request({
    url: '/system/user/updateRole',
    method: 'put',
    data: data
  })
}

