package main.com.Goxless.TaskApp;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * This is the main method which makes app work.
     * @param args are used for identifying input and output files .
     */

    public static void main(String[] args)
    {
        List<String> firstLoad = new ArrayList<>();
        List<String> secondLoad = new ArrayList<>();
        List<String> resultArray = new ArrayList<>();

        MyReader.scanIntoArray(Paths.get(args[0]).toFile(), firstLoad,secondLoad);

        MySolver.recognize(firstLoad, secondLoad).forEach((a, b) -> resultArray.add( a + ":" + b ));

        MyWriter.writeIntoDoc(Paths.get(args[1]).toFile(), resultArray);
    }

}