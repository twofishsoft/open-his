package com.twofish.dto;

import com.twofish.domain.CareHistory;
import com.twofish.domain.Patient;
import com.twofish.domain.PatientFile;
import com.twofish.vo.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author ww
 * @description
 * @create 2020/11/19 20:16
 */
@ApiModel(value="com-twofish-dto-PatientAllMessageDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class PatientAllMessageDto extends BaseDto {

    private List<CareHistory> careHistoryList;
    private Patient patient;
    private PatientFile patientFile;

}
