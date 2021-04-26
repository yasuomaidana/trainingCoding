package com.MahoutMovieRecommender.MahoutMovieRecommender;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

public class MovieRecommender extends Data{
	DataModel model;
	UserBasedRecommender recommender;
	public MovieRecommender(String path) throws IOException, TasteException, ClassNotFoundException {
		super(path);
		
		model = new FileDataModel(new File(System.getProperty("user.dir")+"/data/data.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
        recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        System.out.println("MovieRecommender has been succesfully created");
        
	}
	public List<String> getRecommendationsForUser(String user) throws TasteException{
		int user_Id =Users.get(user);
		System.out.format("Starting recommendations for user: %s with id: %d %n",user,user_Id);
		List<String> recommendations = new ArrayList<String>();
		for (RecommendedItem recommendation : recommender.recommend(user_Id, 3)) {
			int prodId = (int) recommendation.getItemID();
			String product =ProductsId.get(prodId);			
            recommendations.add(product);
        }
		System.out.println("Get recommendations end");
        return recommendations;
	}
	public int getTotalReviews() {return this.TotalReviews;}
	public int getTotalProducts() {return this.TotalProducts;}
	public int getTotalUsers() {return this.TotalUsers;}
}
