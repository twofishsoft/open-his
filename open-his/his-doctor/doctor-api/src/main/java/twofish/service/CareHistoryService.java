package twofish.service;

import com.twofish.domain.CareHistory;
import com.twofish.dto.CareHistoryDto;
import com.twofish.dto.CareOrdersDto;
import com.twofish.dto.PatientAllMessageDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【病例表】业务逻辑接口
 * @date 2020-11-17 16:30:29
 **/
public interface CareHistoryService {

    /**
     * 分页查询病例表数据
     * @param careHistoryDto
     * @return
     */
    DataGridView listPage(CareHistoryDto careHistoryDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<CareHistory> selectAll();

    /**
     * 添加
     * @param careHistoryDto
     * @return
     */
    int insert(CareHistoryDto careHistoryDto);

    /**
     * 修改
     * @param careHistoryDto
     * @return
     */
    int update(CareHistoryDto careHistoryDto);

    /**
     * 根据ID修改
     * @param careHistory
     * @return
     */
    int update(CareHistory careHistory);

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
    CareHistory findById(String id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<CareHistory> queryByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    CareHistory queryOneByAttr(String attr, Object attrValue);

    /**
     * 根据患者ID查询患者所有信息【基础，档案，病例】
     * @param patientId
     * @return
     */
    List<CareHistory> getPatientAllMessageByPatientId(String patientId);

    /**
     * 根据患者ID获取患者信息、档案信息、病历信息
     * @param patientId
     */
    PatientAllMessageDto getPatientAllMessageByPatientIds(String patientId);

    /**
     * 根据病例ID查询处方列表及详情
     * @param chId
     * @return
     */
    CareOrdersDto queryCareOrdersByChId(String chId);

}
