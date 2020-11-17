package twofish.service;

import com.twofish.domain.CheckResult;
import com.twofish.dto.CheckResultDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author ww
 * @description 【检查结果】业务逻辑接口
 * @date 2020-11-17 16:35:08
 **/
public interface CheckResultService {

    /**
     * 分页查询用户数据
     * @param checkResultDto
     * @return
     */
    DataGridView listPage(CheckResultDto checkResultDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<CheckResult> selectAll();

    /**
     * 添加
     * @param checkResultDto
     * @return
     */
    int insert(CheckResultDto checkResultDto);

    /**
     * 修改
     * @param checkResultDto
     * @return
     */
    int update(CheckResultDto checkResultDto);

    /**
     * 根据ID修改
     * @param checkResult
     * @return
     */
    int update(CheckResult checkResult);

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
    CheckResult getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<CheckResult> findByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    CheckResult getOneByAttr(String attr, Object attrValue);
}
