package test;

import org.nlp.util.LineIterator;
import org.nlp.util.Tokenizer;
import org.nlp.vec.VectorModel;
import org.nlp.vec.Word2Vec;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author siegfang
 */
public class TestWord2Vec {

    public static void readByJava(String textFilePath, String modelFilePath){

        Word2Vec wv = new Word2Vec.Factory()
                .setMethod(Word2Vec.Method.Skip_Gram)
                .setNumOfThread(5).build();

        File folder = new File(textFilePath);
        for (File file : folder.listFiles()) {
            try (BufferedReader br =
                    new BufferedReader(new FileReader(file))) {
                int lineCount = 0;
                for (String line = br.readLine(); line != null;
                     line = br.readLine()) {
                    wv.readTokens(new Tokenizer(line, " "));
                    //                System.out.println(line);
                    lineCount++;

                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        wv.training();
        wv.saveModel(new File(modelFilePath));
    }

    public static void testVector(String modelFilePath){

        VectorModel vm = VectorModel.loadFromFile(modelFilePath);
        Set<VectorModel.WordScore> result1 = Collections.emptySet();

        result1 = vm.similar("Reebok");
        for (VectorModel.WordScore we : result1){
            System.out.println(we.name + " :\t" + we.score);
        }
    }

    public static void main(String[] args){

        String textFilePath = "/home/giko/data";
        String modelFilePath = "/home/giko/data/corpus.nn";
        //readByJava(textFilePath, modelFilePath);
        testVector(modelFilePath);
    }

}
