package twofish.service;

import com.twofish.domain.OrderCharge;
import com.twofish.dto.NoChargeCareHistoryDto;
import com.twofish.dto.OrderChargeDto;
import com.twofish.dto.OrderChargeWithCashDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【收费表】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface OrderChargeService {

    /**
     * 分页查询收费表数据
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
    OrderCharge findById(String id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<OrderCharge> queryByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    OrderCharge queryOneByAttr(String attr, Object attrValue);

    OrderCharge queryByChIdAndRegId(String chId, String regId);

    /**
     * 挂号收费
     * @param regId
     * @param orderChargeDto
     * @return
     */
    int collectFee(String regId, OrderChargeDto orderChargeDto);

    /**
     * 根据挂号ID查询已支付的处方信息
     * @param regId
     * @return
     */
    OrderChargeDto getChargedCareHistoryByRegId(String regId);

    /**
     * 根据挂号ID查询未支付的处方信息及详情
     * @param regId
     * @return
     */
    NoChargeCareHistoryDto getNoChargeCareHistoryByRegId(String regId);

    /**
     * 创建现金收费订单
     * @param orderChargeWithCashDto
     * @return
     */
    int createOrderChargeWithCash(OrderChargeWithCashDto orderChargeWithCashDto, String type);

    /**
     * 订单列表支付订单
     * @param orderId
     * @param type 现金、支付宝
     * @return
     */
    List<OrderCharge> payWithCash(String orderId, String type);
}
