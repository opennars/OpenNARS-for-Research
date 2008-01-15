
package nars.entity;

import nars.language.Term;

/**
 * A Goal is an event to be realized, and may conain query variables
 */
public class Goal extends Judgment {
    public Goal(Term term, char punc, TruthValue t, Base b) {
        super(term, punc, t, b);
    }
}

