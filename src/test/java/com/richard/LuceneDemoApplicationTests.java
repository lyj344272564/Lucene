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

        Directory directory = FSDirectory.open(new File("D:/class/index"));

        Analyzer analyzer = new IKAnalyzer();

        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,analyzer);

        IndexWriter indexWriter = new IndexWriter(directory,config);
        indexWriter.deleteAll();

        List<JobInfo> jobInfoList = jobInfoService.selectAll();

        for (JobInfo jobInfo : jobInfoList) {
            Document document = new Document();
            document.add(new LongField("id",jobInfo.getId(), Field.Store.YES));
            document.add(new TextField("companyName",jobInfo.getCompanyName(), Field.Store.YES));
            document.add(new TextField("companyAddr",jobInfo.getCompanyAddr(), Field.Store.YES));
            document.add(new TextField("jobName",jobInfo.getJobName(), Field.Store.YES));
            document.add(new TextField("jobAddr",jobInfo.getJobAddr(), Field.Store.YES));
            document.add(new IntField("salaryMin",jobInfo.getSalaryMin(), Field.Store.YES));
            document.add(new IntField("salaryMax",jobInfo.getSalaryMax(), Field.Store.YES));
            document.add(new StringField("url",jobInfo.getUrl(), Field.Store.YES));
            indexWriter.addDocument(document);
        }
        indexWriter.close();
    }


    @Test
    public void testQueryIndex() throws Exception {

        Directory directory = FSDirectory.open(new File("D:\\class\\index"));

        IndexReader indexReader = DirectoryReader.open(directory);

        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Query query = new TermQuery(new Term("companyName","北京"));

        TopDocs topDocs = indexSearcher.search(query, 100);

        int totalHits = topDocs.totalHits;

        System.out.println("符合条件的总数:"+totalHits);

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for (ScoreDoc scoreDoc : scoreDocs) {
            int docID = scoreDoc.doc;
            Document document = indexSearcher.doc(docID);
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
