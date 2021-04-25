package com.MahoutMovieRecommender.MahoutMovieRecommender;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	Data data = new Data();
    }
}

// This class contains all the functions and parameters related with data
class Data{
	//Directory's path
	String pathDir;
	int BUFFER_SIZE = 4096;
	// Total information
	int TotalReviews;
	int TotalProducts;
	int TotalUsers;
	
	//Constructor
	public Data() throws IOException{
		//Get the current name
    	pathDir = System.getProperty("user.dir")+"/";
		getDat();
	}
	
	
	//Read the data.zip file and unzip it
	public void getDat() throws IOException {
		//Read the data contained in data.zip
    	ZipInputStream zipIn = new ZipInputStream(new FileInputStream(pathDir+"data/data.zip"));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = pathDir +"data/data.csv";
                extractFile(zipIn, filePath);
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        System.out.println("Data.zip decompressed");
        
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
