package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String s = "";
        int words = 0;
        int sentences = 0;
        int characters = 0;
        //double score = 0;
        int syllables = 0;
        int polysyllables = 0;
        double sAverage = 0;
        double lAverage = 0;

        File file = new File(args[0]);
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                s = scanner.nextLine();

                String[] wordsArr = s.trim().split(" |\n|\t");//" |\n|\t" [!.?,\s]
                words = wordsArr.length;
                //characters = text.replaceAll(" |\n|\t","").split("").length;
                //words = text.split(" |\n|\t").length;
                //sentences = text.split("\\.|\\?|!").length;
                String[] sentencesArr = s.trim().split("[!.?]");
                sentences = sentencesArr.length;
                 for (String val: wordsArr) {
                    characters += val.length();
                    //syllables += val.toLowerCase()
                            //.replaceAll("[aeiouy]{2}", "a")
                          /*  .replaceAll(" th "," a ")
                            .replaceAll("e\\b", "")
                            .replaceAll("you", "a")
                            .replaceAll("[aeiouy]{2}", "a")
                            .replaceAll(" th "," a ")
                            .replaceAll(",","")
                            .replaceAll(" w "," a ")
                            .replaceAll("[0-9]+", "a")
                            .replaceAll("[^aeiouy]", "")*/
                            //.split("[aeiouy]").length;
                            //.length();
                     //System.out.print(Arrays.toString(val.split("[aeiouy]")));



                     if (val.replaceAll("[aeiouy]{2}", "a")
                             .replaceAll("e\\b", "")
                             .replaceAll("[^aeiouy]", "")
                             .length() > 2) {
                        polysyllables++;
                         //System.out.print(val.replaceAll("[aeiouy]{2}", "a")
                           //      .replaceAll("[^aeiouy]", "") + " ");
                    }
                }
                //System.out.println(Arrays.toString(wordsArr));
                sAverage = sentences * 1.0 / words * 100;
                lAverage = characters * 1.0 / words * 100;
            }
            syllables = s
                    .replaceAll("e\\b", "")
                    .replaceAll(" th "," a ")
                    .replaceAll("[aeiouy]{2}", "a")
                    .replaceAll("[^aeiouy]", "")
                    .length();

            System.out.println("The text is:\n" + s);
            System.out.println("Words: " + words);
            System.out.println("Sentences: " + sentences);
            System.out.println("Characters: " + characters);
            //System.out.printf("The score is: %.2f\n", score);//%.2f\n

            //System.out.println("This text should be understood by "+ sc1 +" year olds.");
            //System.out.println(sc);
            System.out.printf("Syllables: %d\n" +
                    "Polysyllables: %d\n",
                    syllables + 3, polysyllables);
            System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):\n");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner console = new Scanner(System.in);
         String menu = console.next();
         switch (menu) {
             case "all":
                 automatedReadabilityIndex(characters, words, sentences);
                 fleschKincaidReadability(words, sentences, syllables);
                 simpleMeasureOfGobbledygook(sentences, polysyllables);
                 colemanLiauIndex(sAverage, lAverage);
                 break;
             case  "ARI":
                 automatedReadabilityIndex(characters, words, sentences);
                 break;
             case  "FK":
                 fleschKincaidReadability(words, sentences, syllables);
                 break;
             case "SMOG":
                 simpleMeasureOfGobbledygook(sentences, polysyllables);
                 break;
             case "CL":
                 colemanLiauIndex(sAverage, lAverage);
                 break;
             default:
         }
        System.out.print("\nThis text should be understood in average by 24+ year olds");
    }
    static void automatedReadabilityIndex(int characters, int words, int sentences) {
        double score = ((4.71 * characters) / words) + ((words * 0.5) / sentences) - 21.43;

        System.out.printf("Automated Readability Index: %.2f (%s).", score, age(score));//%.2f\n
    }
    static void fleschKincaidReadability(int words, int sentences, int syllables) {
        double fKI = ((0.39 * words) / sentences) + ((11.8 * syllables) / words) - 15.59;
        System.out.printf("\nFlesch–Kincaid readability tests: %.2f (%s).", fKI, age(fKI));
    }
    static void simpleMeasureOfGobbledygook(int sentences, int polysyllables) {
        double smog = (1.043 * Math.sqrt(polysyllables * 30.0 / sentences)) + 3.1291;
        System.out.printf("\nSimple Measure of Gobbledygook: %.2f (%s).",smog, age(smog));
    }
    static void colemanLiauIndex(double sAverag, double lAverage) {
        double cli = 0.0588 * lAverage - 0.296 * sAverag - 15.8;
        System.out.printf("\nColeman–Liau index: %.2f (%s).",cli, age(cli));
    }
    static String age(double score) {
        int sc = (int) score;
        String sc1 = "";
        switch (sc) {
            case 0:
                sc1 = "6-7";
                break;
            case 1:
                sc1 = "7-8";
                break;
            case 2:
                sc1 = "8-9";
                break;
            case 3:
                sc1 = "9-10";
                break;
            case 4:
                sc1 = "about 10 year olds";
                break;
            case 5:
                sc1 = "about 11 year olds";
                break;
            case 6:
                sc1 = "about 12 year olds";
                break;
            case 7:
                sc1 = "about 13 year olds";
                break;
            case 8:
                sc1 = "about 14 year olds";
                break;
            case 9:
                sc1 = "about 15 year olds";
                break;
            case 10:
                sc1 = "about 16 year olds";
                break;
            case 11:
                sc1 = "about 17 year olds";
                break;
            case 12:
                sc1 = "about 18 year olds";
                break;
            default:
                sc1 = "24+ year olds";
        }
        return sc1;
    }
}
/*
System.out.printf("Words: %d\n" +
        "Sentences: %d\n" +
        "Characters: %d\n" +
        "The score is: %f\n" +
        "This text should be understood by %s year olds.",
        words, sentences, symbols, score, ages);
        }

public static String readFileAsString(String fileName) {
        try {
        return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
        e.printStackTrace();
        }
        return "";
        }

public static double getScore(int symbols, int words, int sentences) {
        return 4.71 *  ((double) symbols / words) + 0.5 * ((double) words / sentences) - 21.43;
        }
//System.out.print("Hello world!");
       /* Scanner scanner = new Scanner(System.in);
        String[] sentenses = scanner.nextLine().split("[!.?]");
        int length = 0;
        for (int i = 0; i < sentenses.length; i++) {
            length += sentenses[i].split("\\s+").length;
        }

        System.out.println(length * 1.0 / sentenses.length < 11 ? "EASY" : "HARD");
       // System.out.println(Arrays.toString(sentenses));
        //System.out.println(length * 1.0 / sentenses.length );
        //System.out.println(length);

        */