package twofish.service;

import com.twofish.domain.CareHistory;
import com.twofish.dto.CareHistoryDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【病例表】业务逻辑接口
 * @date 2020-11-17 16:30:29
 **/
public interface CareHistoryService {

    /**
     * 分页查询病例表数据
     * @param careHistoryDto
     * @return
     */
    DataGridView listPage(CareHistoryDto careHistoryDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<CareHistory> selectAll();

    /**
     * 添加
     * @param careHistoryDto
     * @return
     */
    int insert(CareHistoryDto careHistoryDto);

    /**
     * 修改
     * @param careHistoryDto
     * @return
     */
    int update(CareHistoryDto careHistoryDto);

    /**
     * 根据ID修改
     * @param careHistory
     * @return
     */
    int update(CareHistory careHistory);

    /**
     * 删除数据(可批量删除)
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

    /**
     * 根据ID查询数据
     * @param id
     * @return
     */
    CareHistory getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<CareHistory> findByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    CareHistory getOneByAttr(String attr, Object attrValue);
}
