# easyexcel-read-convet
在easyexcel基础上进行一些工具类编写，能够做到直接将读取出来的数据转换成具体的不同类型的字段，如BigDecimal类型或者LocalDate类型等...

## 使用方法
1. @Converter(value = IntegerFieldConverter.class, errorMsg = "行号格式不正确")
