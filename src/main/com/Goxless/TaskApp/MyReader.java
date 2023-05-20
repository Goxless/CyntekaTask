package main.com.Goxless.TaskApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MyReader {

    /** scanIntoArray
     * method is used for scan words from input file and place them into each array
     * @param inputFile data from input.txt file (URI)
     * @param firstArray  destination string from first part of input.txt data
     * @param secondArray  destination string from second part of input.txt data
     */
    public static void scanIntoArray(File inputFile, List<String> firstArray, List<String> secondArray){
        try (
        BufferedReader br = new BufferedReader(new FileReader(inputFile)))
        {
            writeDownArray(br,firstArray);
            writeDownArray(br,secondArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** writeDownArray
     * method is used for writing data from buffer to array
     * @param br buffer
     * @param array  destination array
     */
    private static void writeDownArray(BufferedReader br, List<String> array) throws IOException{
        String line;
        while ((line = br.readLine()) != null) {
            if(isInteger(line)) {
                int amount = Integer.parseInt(line);
                int iter = 0;

                while (iter < amount) {
                    array.add(br.readLine());
                    iter++;
                }
                break;
            }
        }
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
