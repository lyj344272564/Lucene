# Lucene


![image-20210731012704516](C:\Users\34427\AppData\Roaming\Typora\typora-user-images\image-20210731012704516.png)

![image-20210731012732987](C:\Users\34427\AppData\Roaming\Typora\typora-user-images\image-20210731012732987.png)



**Lucene**适用场景：

- 在应用中为数据库中的数据提供全文检索实现。
- 开发独立的搜索引擎服务、系统

**Lucene**的特性：

1. **稳定、索引性能高**
   - 每小时能够索引150GB以上的数据
   - 对内存的要求小，只需要1MB的堆内存
   - 增量索引和批量索引一样快
   - 索引的大小约为索引文本大小的20%~30%
2. **高效、准确、高性能的搜索算法**
   - 良好的搜索排序
   - 强大的查询方式支持：短语查询、通配符查询、临近查询、范围查询等
   - 支持字段搜索（如标题、作者、内容）
   - 可根据任意字段排序
   - 支持多个索引查询结果合并
   - 支持更新操作和查询操作同时进行
   - 支持高亮、join、分组结果功能
   - 速度快
   - 可扩展排序模块，内置包含向量空间模型、BM25模型可选
   - 可配置存储引擎
3. **跨平台**
   - 纯java编写
   - 作为Apache开源许可下的开源项目，你可以在商业或开源项目中使用
   - Lucene有多种语言实现版（如C，C++、Python等），不仅仅是JAVA

**Lucene架构**：

![image-20210731013107300](C:\Users\34427\AppData\Roaming\Typora\typora-user-images\image-20210731013107300.png)

**索引和搜索流程图**

![image-20210731013133090](C:\Users\34427\AppData\Roaming\Typora\typora-user-images\image-20210731013133090.png)
