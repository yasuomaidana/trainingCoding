package com.MahoutMovieRecommender.MahoutMovieRecommender;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

//This class contains all the functions and parameters related with data
class Data{
	//Directory's path
	String pathDir;
	// Total information
	int TotalReviews;
	int TotalProducts;
	int TotalUsers;
	String rootP = System.getProperty("user.dir")+"/data";
	HashMap<Integer, String> ProductsId = new HashMap<Integer, String>();
	HashMap<String,Integer> Users = new HashMap<String,Integer>();
	HashMap<String,Integer> Products = new HashMap<String,Integer>();
	//Constructor
	public Data() {}
	public Data(String path) throws IOException, ClassNotFoundException{
		//Get the current name
		pathDir = path;
		//getDat();
		//zipToHash();
		tryLoad();
		System.out.format("Number of Users %d, Products %d, and Reviews %d %n", TotalUsers,TotalProducts,TotalReviews);
	}
	@SuppressWarnings("unchecked")
	public void tryLoad() throws IOException, ClassNotFoundException{
		
		File tmpDir = new File(rootP+"/data.csv");
		boolean exists_data = tmpDir.exists();
		tmpDir = new File(rootP+"/users.hash");
		boolean exists_users = tmpDir.exists();
		tmpDir = new File(rootP+"/productsId.hash");
		boolean exists_products = tmpDir.exists();
		if (!(exists_data&&exists_users&&exists_products)){
			processFile();
		}else {
			System.out.println("Loading configuration data");
			FileInputStream reader=new FileInputStream(rootP+"/users.hash");
			ObjectInputStream buffer=new ObjectInputStream(reader);
			Object obj=buffer.readObject();
			Users=(HashMap<String,Integer>)obj;
			buffer.close();
			reader.close();
			reader=new FileInputStream(rootP+"/productsId.hash");
			buffer=new ObjectInputStream(reader);
			obj=buffer.readObject();
			ProductsId=(HashMap<Integer, String>)obj;
			buffer.close();
			reader.close();
			countReviews();
			TotalUsers=Users.size();
			TotalProducts=ProductsId.size();
			
			System.out.println("Ending loading");
		}
	}
	public void countReviews() throws IOException {
		TotalReviews = 0;
		try (InputStream is = new BufferedInputStream(new FileInputStream(rootP+"/data.csv"))) {
			byte[] c = new byte[1024];
			 
			 int readChars = 0;
			 while ((readChars = is.read(c)) != -1) {
			     for (int i = 0; i < readChars; ++i) {
			         if (c[i] == '\n') {
			             ++TotalReviews;
			         }
			     }
			     }
		}
	}
	public void processFile() throws IOException {
		//Reads the gzip 
        FileInputStream file = new FileInputStream(pathDir);
        GZIPInputStream gzipInput = new GZIPInputStream(file);
        Reader decoder = new InputStreamReader(gzipInput);
        BufferedReader reader = new BufferedReader(decoder);
        //Generates patterns
        Pattern usersRegex = Pattern.compile("review\\/userId: ([\\D\\d]+)");
        Pattern reviewsRegex = Pattern.compile("review\\/score: ([\\D\\d]+)");
        Pattern productsRegex = Pattern.compile("product\\/productId: ([\\D\\d]+)");
        //Matchers
        Matcher match_User;
        Matcher match_Product;
        Matcher match_Score;
        //File writers
        FileWriter writer = new FileWriter(System.getProperty("user.dir")+"/data/data.csv");
        
        String user = "";
        String score = "";
        String product = "";
        
        System.out.println("Start reading movies.txt.gz");
        
        String currentLine = reader.readLine();
        while (currentLine != null) {

        	match_User = usersRegex.matcher(currentLine);
        	match_Product = productsRegex.matcher(currentLine);
        	match_Score = reviewsRegex.matcher(currentLine);
            
            if (match_User.matches()) {
                user = currentLine.split(" ")[1];
                if (Users.get(user) == null) {
                    this.TotalUsers++;
                    Users.put(user, this.TotalUsers);
                }
            }
            
            
            if (match_Product.matches()) {
                product = currentLine.split(" ")[1];
                if (Products.get(product) == null) {
                    this.TotalProducts++;
                    Products.put(product, this.TotalProducts);
                    ProductsId.put(this.TotalProducts, product);
                }
            }
            

            if (match_Score.matches()) {
                score = currentLine.split(" ")[1];
                this.TotalReviews++;
                writer.write(Users.get(user) + "," + Products.get(product) + "," + score + "\n");
            }

            currentLine = reader.readLine();
        }

        reader.close();
        writer.close();
        
        System.out.println("Stopping reading");
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"/data/users.hash");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Users);
        oos.close();
        System.out.println("Stopping reading");
        fos = new FileOutputStream(System.getProperty("user.dir")+"/data/productsId.hash");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(ProductsId);
        oos.close();
    }
}
