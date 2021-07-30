package com.richard;

import com.richard.pojo.JobInfo;
import com.richard.service.JobInfoService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class LuceneDemoApplicationTests {

    @Autowired
    private JobInfoService jobInfoService;

    @Test
    public void create() throws Exception{
        // 1、指定索引文件存储的位置 D:\class\index
        Directory directory = FSDirectory.open(new File("D:/class/index"));
        // 2、配置版本和分词器
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,analyzer);
        // 3、创建一个用来创建索引的对象 IndexWriter
        IndexWriter indexWriter = new IndexWriter(directory,config);
        indexWriter.deleteAll();//先删除索引
        // 4、获取原始数据
        List<JobInfo> jobInfoList = jobInfoService.selectAll();
        // 有多少的数据就应该构建多少lucene的文档对象document
        for (JobInfo jobInfo : jobInfoList) {
            Document document = new Document();
            // 域名 值 源数据是否存储
            document.add(new LongField("id",jobInfo.getId(), Field.Store.YES));
            document.add(new TextField("companyName",jobInfo.getCompanyName(), Field.Store.YES));
            document.add(new TextField("companyAddr",jobInfo.getCompanyAddr(), Field.Store.YES));
            document.add(new TextField("jobName",jobInfo.getJobName(), Field.Store.YES));
            document.add(new TextField("jobAddr",jobInfo.getJobAddr(), Field.Store.YES));
            document.add(new IntField("salaryMin",jobInfo.getSalaryMin(), Field.Store.YES));
            document.add(new IntField("salaryMax",jobInfo.getSalaryMax(), Field.Store.YES));
            document.add(new StringField("url",jobInfo.getUrl(), Field.Store.YES));
            // StringField 不需要分词时使用 举例：url 、电话号码、身份证号
            indexWriter.addDocument(document);
        }
        // 关闭资源
        indexWriter.close();
    }


    @Test
    public void testQueryIndex() throws Exception {
        // 1、指定索引文件存储的位置 D:\class\index
        Directory directory = FSDirectory.open(new File("D:\\class\\index"));
        // 2、 创建一个用来读取索引的对象 indexReader
        IndexReader indexReader = DirectoryReader.open(directory);
        // 3、 创建一个用来查询索引的对象 IndexSearcher
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // 使用term查询：指定查询的域名和关键字
        Query query = new TermQuery(new Term("companyName","北京"));

        TopDocs topDocs = indexSearcher.search(query, 100);//第二个参数：最多显示多 少条数据

        int totalHits = topDocs.totalHits;//查询的总数量

        System.out.println("符合条件的总数:"+totalHits);

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;//获取命中的文档 存储的是文档的id

        for (ScoreDoc scoreDoc : scoreDocs) {
            int docID = scoreDoc.doc;
            Document document = indexSearcher.doc(docID);// 根据id查询文档
            System.out.println( "id:"+ document.get("id"));
            System.out.println( "companyName:"+ document.get("companyName"));
            System.out.println( "companyAddr:"+ document.get("companyAddr"));
            System.out.println( "salaryMin:"+ document.get("salaryMin"));
            System.out.println( "salaryMax:"+ document.get("salaryMax"));
            System.out.println( "url:"+ document.get("url"));
            System.out.println("*********************************************************** ***");
        }
        indexReader.close();
    }
}
