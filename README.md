# Bigrams
Simple Bigrams counter.

## Requirements
Java JDK

## How Compile
`javac main.java`

## How run
The program contain methods for collect, print and search bigrams.
Example:

```java
//Create a instance of BigramController.
BigramController bigramController = new BigramController("Input.txt");
//Read the file and create the bigram hashtable with all the bigrams and the count example:
// "Word1 Word2" => 2
bigramController.createBigramTable();
//Collect results and fill a array to sort the result.
bigramController.collectResults();

//Print the bigrams sorted by count.
bigramController.printBigrams();

//Search a bigram
String bigram = ". sed";
System.out.println("El bigrama " + bigram+  " aparece "+  bigramController.searchBigram(bigram) + " veces.");
```
Just run `java main`.
