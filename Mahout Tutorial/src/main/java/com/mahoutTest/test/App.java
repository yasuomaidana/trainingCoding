package com.mahoutTest.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
/* This program calculates the recommendation based on userId, itemId, and value contained in data.csv
 * userID and itemID particular user and item
 * value the strength of their interaction
 * */
public class App 
{
    public static void main( String[] args )
    {
    	
    	try {
    		// Load data, mahout data interface DataModel
            DataModel model = new FileDataModel(new File("/Users/nsl-intern/eclipse-workspace/Mahout Tutorial/data/data.csv"));
            //Mahout have several similarity functions Pearson is one of them
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            //Define the threshold using neighborhood this could change depending on the similarity function
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
            //Creates user recommender input model=data, neighborhood = threshold, similarity = similarity
            UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
            //Gets three recommendations for user 2
            List<RecommendedItem> recommendations = recommender.recommend(2, 3);
            for (RecommendedItem recommendation : recommendations) {
              System.out.println(recommendation);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TasteException e) {
			e.printStackTrace();
		}
		
    }
}
