import request from '@/utils/request'

// 查询所有的部门数据
export function allDept() {
  return request({
    url: '/system/dept/allDept',
    method: 'get',
    params: null
  })
}
