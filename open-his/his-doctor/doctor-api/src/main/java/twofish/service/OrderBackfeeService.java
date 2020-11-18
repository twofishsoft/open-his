package twofish.service;

import com.twofish.domain.OrderBackfee;
import com.twofish.dto.ChargedCareHistoryDto;
import com.twofish.dto.OrderBackfeeDto;
import com.twofish.dto.OrderBackfeeWithCashDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【退费主表】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface OrderBackfeeService {

    /**
     * 分页查询退费主表数据
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
    OrderBackfee findById(String id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<OrderBackfee> queryByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    OrderBackfee queryOneByAttr(String attr, Object attrValue);

    /**
     * 根据挂号ID查询已支付的处方信息【退费时使用】
     * @param regId
     * @return
     */
    ChargedCareHistoryDto getChargedCareHistoryByRegId(String regId);

    int createOrderBackfeeWithCash(OrderBackfeeWithCashDto orderBackfeeDto, String type);
}
