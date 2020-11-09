import request from '@/utils/request'

// 获取所有的菜单
export function getAllMenu(roleId) {
  return request({
    url: '/system/menu/getAllMenu/' + roleId,
    method: 'get'
  })
}
