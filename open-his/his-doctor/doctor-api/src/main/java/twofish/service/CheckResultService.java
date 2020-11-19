package twofish.service;

import com.twofish.domain.CheckResult;
import com.twofish.dto.CheckItemDto;
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
     * 分页查询检查结果数据
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
    CheckResult findById(String id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<CheckResult> queryByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    CheckResult queryOneByAttr(String attr, Object attrValue);

    /**
     * 检查完成
     * @param checkResultDto
     * @return
     */
    int completeCheckResult(CheckResultDto checkResultDto);

    /**
     * 开始检查
     * @param itemId
     * @return
     */
    int startCheck(String itemId);

    /**
     * 查询所有检查中的项目
     * @param checkResultDto
     * @return
     */
    DataGridView queryAllCheckingResultForPage(CheckResultDto checkResultDto);

    /**
     * 根据检查单号查询要检查的项目详情
     * @param itemId
     */
    CheckItemDto queryCheckItemByItemId(String itemId);
}
