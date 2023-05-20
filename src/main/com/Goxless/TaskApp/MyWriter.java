package main.com.Goxless.TaskApp;

import java.io.*;
import java.util.List;

public class MyWriter {

    /** writeIntoDoc
     * method is used for write result data into output.txt file
     * @param outputFile output.txt file (URI)
     * @param resultArray  result which must be written into output.txt file
     */
    public static void writeIntoDoc(File outputFile,List<String> resultArray){
        try (BufferedWriter BufferWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile)))) {

            for(String line: resultArray){
                BufferWriter.write(line);
                BufferWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
