package org.apache.lucene.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.lucene.store.Directory;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.demo.CMPT456Analyzer;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryparser.classic.QueryParser;

public class SimpleMetrics {
    private int documentFrequency;
    private long termFrequency;

    public SimpleMetrics(Term term) {
        try {
            String index = "index";
            IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
            this.documentFrequency = reader.docFreq(term);
            this.termFrequency = reader.totalTermFreq(term);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Analyzer analyzer = new CMPT456Analyzer();
        QueryParser parser = new QueryParser("contents", analyzer);

        System.out.println("Enter query: ");
        while (scanner.hasNext()){ 
            String input = scanner.next();
            Query query = parser.parse(input);

            String contents = query.toString("contents");
            System.out.println(contents);

            Term term = new Term("contents", contents);
            SimpleMetrics metrics = new SimpleMetrics(term);

            System.out.println("Metrics for term: " + contents);
            System.out.println("Term frequency: " + metrics.getTermFrequency());
            System.out.println("Document frequency: " + metrics.getDocumentFrequency());
        }
        
    }

    public int getDocumentFrequency() {
        return documentFrequency;
    }

    public void setDocumentFrequency(int documentFrequency) {
        this.documentFrequency = documentFrequency;
    }

    public long getTermFrequency() {
        return termFrequency;
    }

    public void setTermFrequency(long termFrequency) {
        this.termFrequency = termFrequency;
    }
}