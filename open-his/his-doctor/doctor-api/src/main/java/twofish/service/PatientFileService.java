package twofish.service;

import com.twofish.domain.PatientFile;
import com.twofish.dto.PatientFileDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【患者档案】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface PatientFileService {

    /**
     * 分页查询患者档案数据
     * @param patientFileDto
     * @return
     */
    DataGridView listPage(PatientFileDto patientFileDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<PatientFile> selectAll();

    /**
     * 添加
     * @param patientFileDto
     * @return
     */
    int insert(PatientFileDto patientFileDto);

    /**
     * 修改
     * @param patientFileDto
     * @return
     */
    int update(PatientFileDto patientFileDto);

    /**
     * 根据ID修改
     * @param patientFile
     * @return
     */
    int update(PatientFile patientFile);

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
    PatientFile getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<PatientFile> findByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    PatientFile getOneByAttr(String attr, Object attrValue);
}
