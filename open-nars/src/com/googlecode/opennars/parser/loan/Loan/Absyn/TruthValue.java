package com.googlecode.opennars.parser.loan.Loan.Absyn; // Java Package generated by the BNF Converter.

public abstract class TruthValue implements java.io.Serializable {
  public abstract <R,A> R accept(TruthValue.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(com.googlecode.opennars.parser.loan.Loan.Absyn.TruthE p, A arg);
    public R visit(com.googlecode.opennars.parser.loan.Loan.Absyn.TruthF p, A arg);
    public R visit(com.googlecode.opennars.parser.loan.Loan.Absyn.TruthFC p, A arg);

  }

}