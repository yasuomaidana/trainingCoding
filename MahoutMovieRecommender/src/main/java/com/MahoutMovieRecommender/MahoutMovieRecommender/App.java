package com.MahoutMovieRecommender.MahoutMovieRecommender;

import java.io.IOException;
import java.util.List;
import org.apache.mahout.cf.taste.common.TasteException;

public class App 
{
    public static void main( String[] args ) throws IOException, TasteException, ClassNotFoundException
    {
    	String filePath = System.getProperty("user.dir");
    	filePath+="/movies.txt.gz";
    	MovieRecommender recommender = new MovieRecommender(filePath);
    	

        List<String> recommendations = recommender.getRecommendationsForUser("A141HP4LYPWMSR");
        for (String i:recommendations) {
        	System.out.println(i);
        }
        
        System.out.print("Program ends");
    }
}


