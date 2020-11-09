import request from '@/utils/request'

// 查询角色列表
export function listRole(query) {
  return request({
    url: '/system/role/listForPage',
    method: 'get',
    params: query
  })
}

// 查询所有角色数据
export function allRole() {
  return request({
    url: '/system/role/allRole',
    method: 'get'
  })
}

// 查询角色详细
export function getRole(roleId) {
  return request({
    url: '/system/role/' + roleId,
    method: 'get'
  })
}

// 新增角色
export function addRole(data) {
  return request({
    url: '/system/role',
    method: 'post',
    data: data
  })
}

// 修改角色
export function updateRole(data) {
  return request({
    url: '/system/role',
    method: 'put',
    data: data
  })
}

// 角色数据权限
export function dataScope(data) {
  return request({
    url: '/system/role/dataScope',
    method: 'put',
    data: data
  })
}

// 删除角色
export function delRole(roleId) {
  return request({
    url: '/system/role/' + roleId,
    method: 'delete'
  })
}

// 获取用户角色
export function getUserRole(userId) {
  return request({
    url: '/system/role/getUserRole/' + userId,
    method: 'get'
  })
}
