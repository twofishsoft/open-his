package twofish.service;

import com.twofish.domain.OrderBackfeeItem;
import com.twofish.dto.OrderBackfeeItemDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【退费订单详情表】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface OrderBackfeeItemService {

    /**
     * 分页查询退费订单详情表数据
     * @param orderBackfeeItemDto
     * @return
     */
    DataGridView listPage(OrderBackfeeItemDto orderBackfeeItemDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<OrderBackfeeItem> selectAll();

    /**
     * 添加
     * @param orderBackfeeItemDto
     * @return
     */
    int insert(OrderBackfeeItemDto orderBackfeeItemDto);

    /**
     * 修改
     * @param orderBackfeeItemDto
     * @return
     */
    int update(OrderBackfeeItemDto orderBackfeeItemDto);

    /**
     * 根据ID修改
     * @param orderBackfeeItem
     * @return
     */
    int update(OrderBackfeeItem orderBackfeeItem);

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
    OrderBackfeeItem getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<OrderBackfeeItem> findByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    OrderBackfeeItem getOneByAttr(String attr, Object attrValue);
}
