package org.nlp.vec;

/**
 * Created by giko on 7/28/14.
 */
public class WordModel {
    private String word;
    private float[] vector;

    public String getWord() {
        return word;
    }

    public float[] getVector() {
        return vector;
    }

    public WordModel(String word, float[] vector) {
        this.word = word;
        this.vector = vector;
    }
    
    public WordModel add(WordModel second){
        int vectorSize = this.vector.length;
        float[] center = new float[vectorSize];
        for (int i = 0; i < vectorSize; i++) {
            center[i] = this.vector[i] + second.vector[i];
        }
        return new WordModel(this.word + " + " + second.getWord(), center);
    }

    public WordModel substract(WordModel second){
        int vectorSize = this.vector.length;
        float[] center = new float[vectorSize];
        for (int i = 0; i < vectorSize; i++) {
            center[i] = this.vector[i] - second.vector[i];
        }
        return new WordModel(this.word + " - " + second.getWord(), center);
    }
}
