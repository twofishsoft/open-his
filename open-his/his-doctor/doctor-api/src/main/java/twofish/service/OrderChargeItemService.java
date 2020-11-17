package twofish.service;

import com.twofish.domain.OrderChargeItem;
import com.twofish.dto.OrderChargeItemDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【支付订单详情表】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface OrderChargeItemService {

    /**
     * 分页查询用户数据
     * @param orderChargeItemDto
     * @return
     */
    DataGridView listPage(OrderChargeItemDto orderChargeItemDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<OrderChargeItem> selectAll();

    /**
     * 添加
     * @param orderChargeItemDto
     * @return
     */
    int insert(OrderChargeItemDto orderChargeItemDto);

    /**
     * 修改
     * @param orderChargeItemDto
     * @return
     */
    int update(OrderChargeItemDto orderChargeItemDto);

    /**
     * 根据ID修改
     * @param orderChargeItem
     * @return
     */
    int update(OrderChargeItem orderChargeItem);

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
    OrderChargeItem getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<OrderChargeItem> findByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    OrderChargeItem getOneByAttr(String attr, Object attrValue);
}
