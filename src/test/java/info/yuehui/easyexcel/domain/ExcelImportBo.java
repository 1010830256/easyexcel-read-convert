package info.yuehui.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import info.yuehui.easyexcel.converter.BigDecimalFieldConverter;
import info.yuehui.easyexcel.converter.BooleanFieldConverter;
import info.yuehui.easyexcel.converter.Converter;
import info.yuehui.easyexcel.converter.IntegerFieldConverter;
import info.yuehui.easyexcel.converter.LocalDateFieldConverter;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author zhangxing
 * @date 2022-06-15 15:54
 */
@Data
public class ExcelImportBo {

    @ExcelProperty(value = "行号", index = 0)
    @Converter(value = IntegerFieldConverter.class, errorMsg = "行号格式不正确")
    private Integer rowNum;

    @ExcelProperty(value = "日期", index = 1)
    @Converter(value = LocalDateFieldConverter.class, errorMsg = "无法识别日期格式")
    @NotNull(message = "请填入日期")
    private LocalDate date;

    @ExcelProperty(value = "客户", index = 2)
    @NotBlank(message = "请填入客户名称")
    private String customerName;

    @ExcelProperty(value = "产品", index = 3)
    @NotBlank(message = "请填入产品")
    private String productName;

    @ExcelProperty(value = "数量", index = 4)
    @Converter(value = IntegerFieldConverter.class, errorMsg = "数量格式不正确")
    @NotNull(message = "请填入数量")
    private Integer quantity;

    @ExcelProperty(value = "单价", index = 5)
    @Converter(value = BigDecimalFieldConverter.class, errorMsg = "单价格式不正确")
    @NotNull(message = "请填入单价")
    @DecimalMax(value = "10000", message = "单价不能超过10000")
    @DecimalMin(value = "0", message = "单价必须大于0", inclusive = false)
    private BigDecimal price;

    @ExcelProperty(value = "是否收税", index = 6)
    @Converter(value = BooleanFieldConverter.class, errorMsg = "无法识别是否收税")
    @NotNull(message = "请填入是否收税")
    private Boolean taxFlag;

}
