package com.MahoutMovieRecommender.MahoutMovieRecommender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;

public class Test {
	@Test
    public void testDataInfo() throws IOException, TasteException, ClassNotFoundException {
		String filePath = System.getProperty("user.dir");
    	filePath+="/movies.txt.gz";
    	MovieRecommender recommender = new MovieRecommender(filePath);
    	
    	assertEquals(7911684, recommender.getTotalReviews());
        assertEquals(253059, recommender.getTotalProducts());
        assertEquals(889176, recommender.getTotalUsers());

        List<String> recommendations = recommender.getRecommendationsForUser("A141HP4LYPWMSR");
        
        assertThat(recommendations, hasItem("B0002O7Y8U"));
        assertThat(recommendations, hasItem("B00004CQTF"));
        assertThat(recommendations, hasItem("B000063W82"));
        System.out.print("Program ends");
    }

    
}
