package org.nlp.util;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

/**
 * Created by giko on 7/4/14.
 */
public class TokenizerStanford extends Tokenizer {
    public TokenizerStanford(String text) {
        tokens = new ArrayList<>();
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Collection<CoreMap> sentences = null;
        if (text != null && text.length() > 0) {
            Annotation annotation = pipeline.process(text);
            sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
            for (CoreMap sentence : sentences) {
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    tokens.add(token.word());
                }
            }
        }
        tokenIter = tokens.listIterator();
    }
}
