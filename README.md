# easyexcel-read-convet

在easyexcel基础上进行一些工具类编写，能够做到直接将读取出来的数据转换成具体的不同类型的字段，如BigDecimal类型或者LocalDate类型等...

## 使用方法

1. 给目标对象的字段上使用@Converter指定类型转换器
```java
@Data
public class ExcelImportBo {

    @ExcelProperty(value = "行号", index = 0)
    @Converter(value = IntegerFieldConverter.class, errorMsg = "行号格式不正确")
    private Integer rowNum;

    @ExcelProperty(value = "日期", index = 1)
    @Converter(value = LocalDateFieldConverter.class, errorMsg = "无法识别日期格式")
    @NotNull(message = "请填入日期")
    private LocalDate date;
}
```

如果没有可用的转换器可以自定义的去实现`ExcelFieldConverter`接口

2. 使用通用的Listener`CommonEasyExcelListener`
```java
public class CommonEasyExcelListenerTest{
    
    @Test
    public void Test() {
        // 读取数据
        List<ConvertWrap<ExcelImportBo>> list = new ArrayList<>();

        EasyExcel
                .read(
                        CommonEasyExcelListenerTest.class.getResourceAsStream("/test.xlsx"),
                        // 使用通用的Listener
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
```