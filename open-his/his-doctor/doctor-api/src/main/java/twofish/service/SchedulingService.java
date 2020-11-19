package twofish.service;

import com.twofish.domain.Scheduling;
import com.twofish.dto.SchedulingDto;
import com.twofish.dto.SchedulingInfoDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【排班信息表】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface SchedulingService {

    /**
     * 分页查询排班信息表数据
     * @param schedulingDto
     * @return
     */
    DataGridView listPage(SchedulingDto schedulingDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<Scheduling> selectAll();

    /**
     * 添加
     * @param schedulingDto
     * @return
     */
    int saveScheduling(SchedulingDto schedulingDto);

    /**
     * 修改
     * @param schedulingDto
     * @return
     */
    int update(SchedulingDto schedulingDto);

    /**
     * 根据ID修改
     * @param scheduling
     * @return
     */
    int update(Scheduling scheduling);

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
    Scheduling findById(String id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<Scheduling> queryByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    Scheduling queryOneByAttr(String attr, Object attrValue);

    /**
     * 根据条件查询有号的部门
     * @param schedulingDto
     * @return
     */
    List<Scheduling> listDeptForScheduling(SchedulingDto schedulingDto);

    /**
     * 查询要排班的医生的排班信息
     * @return
     */
    SchedulingInfoDto queryScheduling(SchedulingDto schedulingDto);

    /**
     * 查询当前登陆用户的排班信息
     * @param schedulingDto
     * @return
     */
    SchedulingInfoDto queryMyScheduling(SchedulingDto schedulingDto);
}
