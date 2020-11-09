import request from '@/utils/request'

// 查询字典数据详细
export function getDicts(dictType) {
  return request({
    url: '/system/dict/data/getDataByType/' + dictType,
    method: 'get'
  })
}
