package twofish.service;

import com.twofish.domain.Patient;
import com.twofish.dto.PatientDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【患者信息表】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface PatientService {

    /**
     * 分页查询患者信息表数据
     * @param patientDto
     * @return
     */
    DataGridView listPage(PatientDto patientDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<Patient> selectAll();

    /**
     * 添加
     * @param patientDto
     * @return
     */
    int insert(PatientDto patientDto);

    /**
     * 修改
     * @param patientDto
     * @return
     */
    int update(PatientDto patientDto);

    /**
     * 根据ID修改
     * @param patient
     * @return
     */
    int update(Patient patient);

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
    Patient findById(String id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<Patient> queryByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    Patient queryOneByAttr(String attr, Object attrValue);

    /**
     * 根据身份证号查询患者信息
     * @param idCard
     * @return
     */
    Patient getPatientByIdCard(String idCard);

}
