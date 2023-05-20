package main.com.Goxless.TaskApp;

import java.util.*;

public class MySolver {

    /** levenshteinAlgorithm
     * Levenshtein distance algorithm is a string metric algorithm for measuring the difference of two string between each other
     * @param a first string
     * @param b second string
     * @return int required number of permutations
     */
    static public int levenshteinAlgorithm(String a, String b) {

        a = " " + a.toLowerCase();
        b = " " + b.toLowerCase();

        int[][] matrix = new int[a.length()][b.length()];

        for (int i = 0; i < a.length() ; i++) {
            for (int j = 0; j < b.length(); j++) {
                if (Math.min(i, j) == 0) {
                    matrix[i][j] = Math.max(i, j);
                }
                else {
                    int x = matrix[i - 1][j];
                    int y = matrix[i][j - 1];
                    int z = matrix[i - 1][j - 1];

                    matrix[i][j]= Math.min(Math.min(x, y), z) + (a.charAt(i) == b.charAt(j) ? 0 : 1);
                }
            }
        }
        return matrix[a.length() - 1][b.length() - 1 ];
    }

    /** recognize
     * method is used for semantic recognition
     * @param firstWords first words array
     * @param secondWords  second words array
     * @return map resulted map
     */
    public static HashMap<String, String> recognize(List<String> firstWords, List<String> secondWords) {

        HashMap<String, List<String>> firstArrayFrame = splitArray(firstWords); // sentences presented word by word
        HashMap<String, List<String>> secondArrayFrame = splitArray(secondWords);

        HashMap<String, String> resultMap = new HashMap<>();

        int currentMin;
        for(String frstWord:firstWords ) {  // result map filling
            int maxApproach = Integer.MAX_VALUE;
            String maxApproachValue = "";
            for (String scndWord : secondWords){
                currentMin = findMaxApproach(secondArrayFrame.get(scndWord),firstArrayFrame.get(frstWord));
                if(currentMin < maxApproach) {
                    maxApproach = currentMin;
                    maxApproachValue = scndWord;
                }
            }
            resultMap.put(frstWord,maxApproachValue);
        }

        if(firstWords.size() < secondWords.size()){ // overdata checking and placing ? sign

            Set<String> resultMapKeys = resultMap.keySet();
            Collection<String> resultMapValues = resultMap.values();

            for (String secondWord : secondWords) {

                if (!resultMapKeys.contains(secondWord) && !resultMapValues.contains(secondWord)) {
                    resultMap.put(secondWord, "?");
                }
            }
        }

        HashMap<String,List<String>> duplicateValues= new HashMap<>();

        for(Map.Entry<String,String> entry: resultMap.entrySet()){   // duplicate resolving
            for(Map.Entry<String,String> sideEntry: resultMap.entrySet()){
                if(entry.getValue().equals(sideEntry.getValue()) &&  !entry.getKey().equals(sideEntry.getKey())){
                    duplicateValues.computeIfAbsent(entry.getValue(), k -> new ArrayList<>());
                    duplicateValues.get(entry.getValue()).add(sideEntry.getKey());
                }
            }
        }

        int minApproach = 0;
        for(Map.Entry<String, List<String>> entry: duplicateValues.entrySet()){ // duplicate processing and correction
            int levVal;
            String minValue = "";
            for(String word: entry.getValue()){
                levVal = findMaxApproach(Arrays.asList(entry.getKey().split(" ")),Arrays.asList(word.split(" ")));
                if(minApproach < levVal) {
                    minApproach = levVal;
                    minValue = word;
                }
            }
            resultMap.put(minValue,"?");
        }

        return  resultMap;
    }

    /** recognize
     * method is used for finding closed approach between to sentences
     * @param firstArray first sentences array
     * @param secondArray  second sentences array
     * @return int minimum Levenstein metric
     */
    private static int findMaxApproach(List<String> firstArray, List<String> secondArray ) {

        int min = Integer.MAX_VALUE;
        int levVal;

        for (String a: firstArray){
            for (String b: secondArray){
                levVal= MySolver.levenshteinAlgorithm(a,b);
                if (levVal < min)
                    min = levVal;
            }
        }
        return min;
    }




    private static HashMap<String, List<String>> splitArray(List<String> array) {

        HashMap<String, List<String>> result = new HashMap<>();
        for(String a:array ) {
            result.put(a,Arrays.asList(a.split(" ")));
        }
        return result;
    }

}
