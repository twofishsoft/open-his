package twofish.service;

import com.twofish.domain.OrderCharge;
import com.twofish.dto.OrderChargeDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【收费表】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface OrderChargeService {

    /**
     * 分页查询用户数据
     * @param orderChargeDto
     * @return
     */
    DataGridView listPage(OrderChargeDto orderChargeDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<OrderCharge> selectAll();

    /**
     * 添加
     * @param orderChargeDto
     * @return
     */
    int insert(OrderChargeDto orderChargeDto);

    /**
     * 修改
     * @param orderChargeDto
     * @return
     */
    int update(OrderChargeDto orderChargeDto);

    /**
     * 根据ID修改
     * @param orderCharge
     * @return
     */
    int update(OrderCharge orderCharge);

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
    OrderCharge getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<OrderCharge> findByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    OrderCharge getOneByAttr(String attr, Object attrValue);
}
