package twofish.service;

import com.twofish.domain.Patient;
import com.twofish.domain.Registration;
import com.twofish.domain.Scheduling;
import com.twofish.dto.RegistrationDto;
import com.twofish.dto.SchedulingDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【挂号信息】业务逻辑接口
 * @date 2020-11-17 14:14:02
 **/
public interface RegistrationService {

    /**
     * 分页查询挂号信息数据
     * @param registrationDto
     * @return
     */
    DataGridView listPage(RegistrationDto registrationDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<Registration> selectAll();

    /**
     * 添加
     * @param registrationDto
     * @return
     */
    int insert(RegistrationDto registrationDto);

    /**
     * 修改
     * @param registrationDto
     * @return
     */
    int update(RegistrationDto registrationDto);

    /**
     * 根据ID修改
     * @param registration
     * @return
     */
    int update(Registration registration);

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
    Registration findById(String id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<Registration> queryByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    Registration queryOneByAttr(String attr, Object attrValue);

    /**
     * 作废【根据挂号单号】
     * @param regId
     * @return
     */
    int doInvalid(String regId);

    /**
     * 退号【根据挂号单号】
     * @param regId
     * @return
     */
    int doReturn(String regId);

}
