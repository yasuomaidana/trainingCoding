package com.MahoutMovieRecommender.MahoutMovieRecommender;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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
	
	// Tries to 
	@SuppressWarnings("unchecked")
	public void tryLoad() throws IOException, ClassNotFoundException{
		
		File tmpDir = new File(rootP+"/data.zip");
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
			TotalUsers=Users.size();
			TotalProducts=ProductsId.size();
			Decompress();
			countReviews();
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
        
        
        //Saving information
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"/data/users.hash");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Users);
        oos.close();
        
        fos = new FileOutputStream(System.getProperty("user.dir")+"/data/productsId.hash");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(ProductsId);
        oos.close();
        Compress();
        
        System.out.println("Stopping reading");
    }
	
	public void Compress() throws IOException{
		String sourceFile = rootP+"/data.csv";
        FileOutputStream fos = new FileOutputStream(rootP+"/data.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(sourceFile);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        zipOut.close();
        fis.close();
        fos.close();
	}
	public void Decompress() throws IOException {
        String fileZip = rootP+"/data.zip";
        File destDir = new File(rootP);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDir+"/"+ entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdirs();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }
	private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
