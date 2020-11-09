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
    url: '/system/role/selectAllRole',
    method: 'get'
  })
}

// 查询角色详细
export function getOne(roleId) {
  return request({
    url: '/system/role/getRoleById/' + roleId,
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
export function updateMenu(roleId, menuIds) {
  return request({
    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },
    url: '/system/role/saveRoleMenu/' + roleId + '/' + menuIds,
    method: 'post'
  })
}

// 获取用户角色
export function getUserRole(userId) {
  return request({
    url: '/system/role/getRoleIdsByUserId/' + userId,
    method: 'get'
  })
}
