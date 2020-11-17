package twofish.service;

import com.twofish.domain.RegisteredItem;
import com.twofish.dto.RegisteredItemDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【挂号项目】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface RegisteredItemService {

    /**
     * 分页查询挂号项目数据
     * @param registeredItemDto
     * @return
     */
    DataGridView listPage(RegisteredItemDto registeredItemDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<RegisteredItem> selectAll();

    /**
     * 添加
     * @param registeredItemDto
     * @return
     */
    int insert(RegisteredItemDto registeredItemDto);

    /**
     * 修改
     * @param registeredItemDto
     * @return
     */
    int update(RegisteredItemDto registeredItemDto);

    /**
     * 根据ID修改
     * @param registeredItem
     * @return
     */
    int update(RegisteredItem registeredItem);

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
    RegisteredItem getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<RegisteredItem> findByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    RegisteredItem getOneByAttr(String attr, Object attrValue);
}
