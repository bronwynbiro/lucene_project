package org.apache.lucene.demo;

import org.apache.lucene.search.similarities.ClassicSimilarity;

import java.lang.Math;


public class CMPT456Similarity extends ClassicSimilarity {
    @Override
    public float tf(float frequency) {
        return (float) (Math.sqrt(1 + frequency));
    }

    @Override
    public float idf(long docFrequency, long docCount) {
        return (float) (Math.log((docCount + 2) / (docFrequency + 2)) + 1);
    }
}