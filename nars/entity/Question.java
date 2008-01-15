
package nars.entity;

import nars.language.Term;

/**
 * A Question is a sentence without a truth value needs evaluation, and may conain query variables
 */
public class Question extends Sentence {

    public Question(Term term, char punc) {
        content = term;
        punctuation = punc;
    }
}

