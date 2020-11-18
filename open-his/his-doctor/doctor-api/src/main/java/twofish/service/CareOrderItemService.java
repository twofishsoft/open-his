package twofish.service;

import com.twofish.domain.CareOrderItem;
import com.twofish.domain.CheckResult;
import com.twofish.dto.CareOrderItemDto;
import com.twofish.dto.CheckResultDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【开诊细表】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface CareOrderItemService {

    /**
     * 分页查询开诊细表数据
     * @param careOrderItemDto
     * @return
     */
    DataGridView listPage(CareOrderItemDto careOrderItemDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<CareOrderItem> selectAll();

    /**
     * 添加
     * @param careOrderItemDto
     * @return
     */
    int insert(CareOrderItemDto careOrderItemDto);

    /**
     * 修改
     * @param careOrderItemDto
     * @return
     */
    int update(CareOrderItemDto careOrderItemDto);

    /**
     * 根据ID修改
     * @param careOrderItem
     * @return
     */
    int update(CareOrderItem careOrderItem);

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
    CareOrderItem getOneById(String id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<CareOrderItem> findByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    CareOrderItem getOneByAttr(String attr, Object attrValue);

    /**
     * 根据挂号单号和项目IDS查询要检查的项目
     * @param careOrderItemDto
     * @return
     */
    List<CareOrderItem> queryNeedCheckItem(CareOrderItemDto careOrderItemDto);
}
