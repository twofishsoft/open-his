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
export function getOne(roleId) {
  return request({
    url: '/system/role/getOne/' + roleId,
    method: 'get'
  })
}

// 新增角色
export function addRole(data) {
  return request({
    url: '/system/role/addRole',
    method: 'post',
    data: data
  })
}

// 修改角色
export function updateRole(data) {
  return request({
    url: '/system/role/updateRole',
    method: 'put',
    data: data
  })
}

// 删除角色
export function delRole(roleId) {
  return request({
    url: '/system/role/deleteRoleByIds/' + roleId,
    method: 'delete'
  })
}

// 更新角色菜单
export function updateMenu(roleIds, menuIds) {
  const data = {
    roleIds,
    menuIds
  }
  return request({
    url: '/system/role/updateMenu',
    method: 'put',
    data: data
  })
}

// 获取用户角色
export function getUserRole(userId) {
  return request({
    url: '/system/role/getUserRole/' + userId,
    method: 'get'
  })
}
