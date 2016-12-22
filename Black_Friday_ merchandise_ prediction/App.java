package com.predictionmarketing.Recommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Hello world!
 *】
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
//    		HashSet<Integer> UIDs = new HashSet<Integer>(), PIDs = new HashSet<Integer>();
//    		BufferedReader reader = new BufferedReader(new FileReader("data/uid.csv"));
//    		String line = "";
//    		while ((line = reader.readLine()) != null)
//    	    {
//    			UIDs.add(Integer.parseInt(line));
//    	    }
//    		reader.close();
//    		
//    		reader = new BufferedReader(new FileReader("data/pid.csv"));
//    		line = "";
//    		while ((line = reader.readLine()) != null)
//    	    {
//    			PIDs.add(Integer.parseInt(line));
//    	    }
//    		reader.close();
//    		
//    		PrintWriter writer = new PrintWriter("data/user_recommender.txt", "UTF-8");
    		
    		DataModel model = new FileDataModel(new File("data/data.csv"));

//			// User-based 
//			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
//			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
//			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
//			for(Integer uid: UIDs) {
//				List<RecommendedItem> recommendations = recommender.recommend(uid,5);
//				writer.println("User: " + uid);
//				writer.flush();
//				for (RecommendedItem recommendation : recommendations) {
//					writer.println(recommendation + " ");
//					writer.flush();
//				}
//				writer.println();
//				writer.println();
//				writer.flush();
//			}
//			writer.close();
//			
//			// Item-based
//			ItemSimilarity sim = new LogLikelihoodSimilarity(model);
//			GenericItemBasedRecommender ibr = new GenericItemBasedRecommender(model, sim);
//			
//			writer = new PrintWriter("data/item_recommender.txt", "UTF-8");
//			for (LongPrimitiveIterator items = model.getItemIDs(); items.hasNext(); ) {
//				long itemId = items.nextLong();
//				writer.println("Item id: " + itemId);
//				writer.flush();
//				List<RecommendedItem> recomds = ibr.mostSimilarItems(itemId, 5);
//				
//				for(RecommendedItem recommendedItem: recomds ) {
//					writer.println("productID = " + recommendedItem.getItemID() + ", similarity = " + recommendedItem.getValue());
//					writer.flush();
//				}
//				writer.println();
//				writer.println();
//			}
    		
    		class MyRecommenderBuilder implements RecommenderBuilder {

    			public Recommender buildRecommender(DataModel arg0) {
    				try {
        				UserSimilarity similarity = new PearsonCorrelationSimilarity(arg0);
        				UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, arg0);
        				return new GenericUserBasedRecommender(arg0, neighborhood, similarity);
						} catch (TasteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				return null;
    			}
    		}
    			
			RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
			RecommenderBuilder builder = new MyRecommenderBuilder();
			double result = evaluator.evaluate(builder, null, model, 0.9, 1.0);
			System.out.println("Result " + result);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}


