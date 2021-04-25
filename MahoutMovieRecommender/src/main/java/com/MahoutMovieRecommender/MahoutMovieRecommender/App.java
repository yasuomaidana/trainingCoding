package com.MahoutMovieRecommender.MahoutMovieRecommender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	//Get the current name
    	String baseName = System.getProperty("user.dir")+"/";
    	
    	//Read the data contained in data.zip
    	ZipFile zipFile = new ZipFile(baseName+"data/data.zip");
    	Enumeration<? extends ZipEntry> entries = zipFile.entries();
    	while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            String name = entry.getName();
            long compressedSize = entry.getCompressedSize();
            long normalSize = entry.getSize();
            String type = entry.isDirectory() ? "DIR" : "FILE";

            System.out.println(name);
            System.out.format("\t %s - %d - %d\n", type, compressedSize, normalSize);
            
            InputStream input = zipFile.getInputStream(entry);
            BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            while(br.readLine()!=null){
            	System.out.println(br.readLine());
            	break;
            }
        }

        zipFile.close();
    	//DataModel model = new FileDataModel(new File("/Users/nsl-intern/Documents/Academy Code/MahoutMovieRecommender/data/data.csv"));
        System.out.println("It worked");
    }
}
class Data{

}
