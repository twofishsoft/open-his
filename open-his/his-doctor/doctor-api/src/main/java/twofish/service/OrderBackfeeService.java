package twofish.service;

import com.twofish.domain.OrderBackfee;
import com.twofish.dto.OrderBackfeeDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【退费主表】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface OrderBackfeeService {

    /**
     * 分页查询用户数据
     * @param orderBackfeeDto
     * @return
     */
    DataGridView listPage(OrderBackfeeDto orderBackfeeDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<OrderBackfee> selectAll();

    /**
     * 添加
     * @param orderBackfeeDto
     * @return
     */
    int insert(OrderBackfeeDto orderBackfeeDto);

    /**
     * 修改
     * @param orderBackfeeDto
     * @return
     */
    int update(OrderBackfeeDto orderBackfeeDto);

    /**
     * 根据ID修改
     * @param orderBackfee
     * @return
     */
    int update(OrderBackfee orderBackfee);

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
    OrderBackfee getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<OrderBackfee> findByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    OrderBackfee getOneByAttr(String attr, Object attrValue);
}
