import java.util.*;
import java.io.*;

public class Bigrams {

    Hashtable table;
    String bigram;
    String[] bigramStrings;
    int[] bigramCounts;
    Scanner inFile;
    Enumeration elements, keys;
    BigramsCounts[] fullResults;

    public static void main(String[] args) {

        Bigrams stats = new Bigrams();
        stats.run();

    } // main()

    void run() {

        openFile();
        collectStats();
        collectResults();
        dumpTable();
        //showAlphaResults();
        showFrequencyResults();

    } // run()

    /** Hardcoded filename here - this example is on my Powerbook G4.
     */
    void openFile() {
        // File is the 2 April 1053 Watson, Crick letter to Nature
        try{

            inFile = new Scanner(new File("SampleInput.txt"));
        }
        catch (Exception e) {
            System.out.println("No existe el archivo test.txt dentro del folder");}
    } // openFile()

    void addBigramToTable(String bigram, Hashtable table )
    {
        Object item = table.get(bigram);
        if (item != null)
            ((int[])item)[0]++;
        else
        {
            int[] count = {1};
            table.put(bigram,count);
        }
    }
    static String cleanString(String string)
    {
        String newStr = string.replaceAll("[.]+", " . ");
        newStr = newStr.replaceAll(",", " , ");
        newStr = newStr.replaceAll("\\s+", " ");
        if (newStr != null && newStr.length() > 0 && newStr.charAt(newStr.length() -1) == ' ')
        {
            newStr = newStr.substring(0,newStr.length()-1);
        }
        return newStr;

    }

    void collectStats() {

        table = new Hashtable();
        String line = "";
        StringTokenizer tokens;
        String[] tokensSplit;
        String token1 = "";
        String token2 = "";
        Boolean isFirstLine = true;

        try {
            Scanner sc = new Scanner(new File("Input.txt"));

            line = line + sc.nextLine();
            while(sc.hasNextLine())
            {

              line = cleanString(line);
              tokens = new StringTokenizer(line);


              //  Si hay palabras en la linea
              if (tokens.hasMoreTokens())
              {

                  //Si es la primera linea y la primera palabra, crear el bigrama que empieza con I.
                  if (isFirstLine)
                  {
                      token1 = tokens.nextToken();
                      token1 = token1.toLowerCase();
                      bigram = "I" + " " + token1;

                      isFirstLine = false;
                      addBigramToTable(bigram,table);
                  }
              }

              //Ahora tenemos que recorrer el arreglo de palabras dentro de la oración.
              while(tokens.hasMoreTokens()) {
                  token2 = tokens.nextToken();
                  token2 = token2.toLowerCase();
                  bigram = token1 + " " + token2;

                  addBigramToTable(bigram,table);
                  token1 = token2; // step forward
              }
              line = sc.nextLine();

            }
            bigram = token2 + " " + "F";
            addBigramToTable(bigram,table);
            sc.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("No existe el archivo test.txt dentro del folder");
        }


    } // collectStats()


    /** Local class for bigram count pairs.
     */
    class BigramsCounts{
        int count;
        String bigram;

        BigramsCounts(int count, String bigram) {
            this.count = count;
            this.bigram = bigram;
        }
    }

    void collectResults() {

        keys = table.keys();
        elements = table.elements();
        int index = 0;
        fullResults = new BigramsCounts[table.size()];

        while(keys.hasMoreElements()){
            elements.hasMoreElements();
            fullResults[index++] =
            new BigramsCounts(
                              ((int[])elements.nextElement())[0],
                              (String)keys.nextElement());
        }
    } // collectResults

    /** Dumps entire table.
     */
    void dumpTable() {


    } // dumpTable()

    void showAlphaResults() {

        Comparator comp = new Comparator(){
            public int compare(Object o1, Object o2){
                return (((BigramsCounts)o1).bigram).compareTo(((BigramsCounts)o2).bigram);
            }
        }; // Comparator comp

        Arrays.sort(fullResults, comp);
        System.out.println("Results sorted alphabetically:");
        for(int i=0; i < fullResults.length; i++)
            System.out.println(fullResults[i].count + " " + fullResults[i].bigram);

    } // showAlphaResults()

    void showFrequencyResults() {

        Comparator comp = new Comparator(){
            public int compare(Object o1, Object o2){
                int i1 = ((BigramsCounts)o1).count;
                int i2 = ((BigramsCounts)o2).count;
                if(i1 == i2) return 0;
                return ((i1 > i2) ? -1 : +1);
            }
        }; // Comparator comp

        Arrays.sort(fullResults, comp);
      //  System.out.println("Results sorted by bigram count:");
        for(int i=0; i < fullResults.length; i++)
            System.out.println(fullResults[i].bigram + "-"+fullResults[i].count );


    } // showFrequencyResults()

} // class SimpleBigramStats

/*
 Using the Watson and Crick April 1953 Letter to Nature.
 Number of bigrams is: 652

 Here are the largest entries from the count sorted result:
 11 of the
 7 that the
 6 and the
 5 it is
 5 on the
 5 in the
 4 to the
 4 the other
 4 the bases

 */
