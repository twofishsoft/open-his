package twofish.service;

import com.twofish.domain.CareOrder;
import com.twofish.dto.CareOrderDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【药用处方表】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface CareOrderService {

    /**
     * 分页查询药用处方表数据
     * @param careOrderDto
     * @return
     */
    DataGridView listPage(CareOrderDto careOrderDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<CareOrder> selectAll();

    /**
     * 添加
     * @param careOrderDto
     * @return
     */
    int insert(CareOrderDto careOrderDto);

    /**
     * 添加
     * @param careOrder
     * @return 返回添加的数据
     */
    CareOrder saveCareOrder(CareOrderDto careOrderDto);

    /**
     * 修改
     * @param careOrderDto
     * @return
     */
    int update(CareOrderDto careOrderDto);

    /**
     * 根据ID修改
     * @param careOrder
     * @return
     */
    int update(CareOrder careOrder);

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
    CareOrder findById(String id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<CareOrder> queryByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    CareOrder queryOneByAttr(String attr, Object attrValue);

    /**
     * 查询处方信息
     * @param careOrderDto
     * @return
     */
    List<CareOrder> getNoChargeCareByRegId(CareOrderDto careOrderDto);

    /**
     * 根据病例获取所有的药用处方和明细
     * @param careOrderDto
     * @return
     */
    List<CareOrder> getCareOrderItem(CareOrderDto careOrderDto);

}
