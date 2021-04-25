package com.MahoutMovieRecommender.MahoutMovieRecommender;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

//This class contains all the functions and parameters related with data
class Data{
	//Directory's path
	String pathDir;
	int BUFFER_SIZE = 4096;
	// Total information
	int TotalReviews;
	int TotalProducts;
	int TotalUsers;
	HashMap<Integer, String> Products = new HashMap<Integer, String>();
	HashMap<String,Integer> Users = new HashMap<String,Integer>();
	//Constructor
	public Data() throws IOException{
		//Get the current name
 	pathDir = System.getProperty("user.dir")+"/";
		getDat();
		zipToHash();
		
	}
	
	public void zipToHash() throws IOException{
		zipUser();
		zipProduct();
	}
	
	//Read the zip file and fill User hashtable
	public void zipUser() throws IOException{
		ZipFile zipFile = new ZipFile(pathDir+"data/users.zip");
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
	 	while (entries.hasMoreElements()) {
	         ZipEntry entry = entries.nextElement();
	         InputStream input = zipFile.getInputStream(entry);
	         BufferedReader br = new BufferedReader(new InputStreamReader(input));
	         int i =1;
	         String user = br.readLine();
	         Users.put(user, i);
	         i+=1;
	         while(user!=null){
	         	user = br.readLine();
	             Users.put(user, i);
	             i+=1;
	         }
	     }
	     zipFile.close();
	     TotalUsers = Users.size()-1;
	     System.out.format("Number of users: %d %n",TotalUsers);
	}
	
	//Read the zip file and fill Product hashtable
	public void zipProduct() throws IOException{
		ZipFile zipFile = new ZipFile(pathDir+"data/products.zip");
 	Enumeration<? extends ZipEntry> entries = zipFile.entries();
 	while (entries.hasMoreElements()) {
         ZipEntry entry = entries.nextElement();
         InputStream input = zipFile.getInputStream(entry);
         BufferedReader br = new BufferedReader(new InputStreamReader(input));
         int i =1;
         String product = br.readLine();
         Products.put(i, product);
         i+=1;
         while(product!=null){
         	product = br.readLine();
             Products.put(i,product);
             i+=1;
         }
     }
     zipFile.close();
     TotalProducts = Products.size()-1;
     System.out.format("Number of products: %d %n",TotalProducts);
	}
	//Read the data.zip file and unzip it
	public void getDat() throws IOException {
		//Read the data contained in data.zip
 	ZipInputStream zipIn = new ZipInputStream(new FileInputStream(pathDir+"data/data.zip"));
     ZipEntry entry = zipIn.getNextEntry();
     // iterates over entries in the zip file
     String filePath = pathDir +"data/data.csv";
     while (entry != null) {
         extractFile(zipIn, filePath);
         zipIn.closeEntry();
         entry = zipIn.getNextEntry();
     }
     zipIn.close();
     
     TotalReviews = 0;
     InputStream is = new BufferedInputStream(new FileInputStream(filePath));
     
     byte[] c = new byte[1024];
     
     int readChars = 0;
     while ((readChars = is.read(c)) != -1) {
         for (int i = 0; i < readChars; ++i) {
             if (c[i] == '\n') {
                 ++TotalReviews;
             }
         }
     }
     is.close();
     System.out.format("Number of reviews: %d %n",TotalReviews);
	}
	//Extracts the file
	private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
     byte[] bytesIn = new byte[BUFFER_SIZE];
     int read = 0;
     while ((read = zipIn.read(bytesIn)) != -1) {
         bos.write(bytesIn, 0, read);
     }
     bos.close();
 }
}
