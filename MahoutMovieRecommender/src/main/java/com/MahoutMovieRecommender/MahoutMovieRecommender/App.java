package com.MahoutMovieRecommender.MahoutMovieRecommender;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, TasteException
    {
    	MovieRecommender recommender = new MovieRecommender();
    	
    	//assertEquals(7911684, recommender.getTotalReviews());
        //assertEquals(253059, recommender.getTotalProducts());
        //assertEquals(889176, recommender.getTotalUsers());

        List<String> recommendations = recommender.getRecommendationsForUser("A141HP4LYPWMSR");
        for (String i:recommendations) {
        	System.out.println(i);
        }
        
        //assertThat(recommendations, hasItem("B0002O7Y8U"));
        //assertThat(recommendations, hasItem("B00004CQTF"));
        //assertThat(recommendations, hasItem("B000063W82"));
    }
}


