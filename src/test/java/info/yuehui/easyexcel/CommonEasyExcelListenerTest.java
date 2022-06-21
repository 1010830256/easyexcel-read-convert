package info.yuehui.easyexcel;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import info.yuehui.easyexcel.domain.ConvertWrap;
import info.yuehui.easyexcel.domain.ExcelImportBo;
import info.yuehui.easyexcel.listener.CommonEasyExcelListener;
import info.yuehui.easyexcel.util.ValidatorUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxing
 * @version v1.0
 * @date 2022/6/21 12:04
 */
public class CommonEasyExcelListenerTest {

    @Test
    public void Test() {
        // 读取数据
        List<ConvertWrap<ExcelImportBo>> list = new ArrayList<>();

        EasyExcel
                .read(
                        CommonEasyExcelListenerTest.class.getResourceAsStream("/test.xlsx"),
                        new CommonEasyExcelListener<>(ExcelImportBo.class, list::add)
                )
                .headRowNumber(1)
                .sheet()
                .doRead();

        for (ConvertWrap<ExcelImportBo> convertWrap : list) {
            String validMsg = ValidatorUtils.validate2Str(convertWrap.getObj());
            System.out.println(StrUtil.format("读取内容: {}, 校验信息: {}", convertWrap, validMsg));
        }
    }

}
