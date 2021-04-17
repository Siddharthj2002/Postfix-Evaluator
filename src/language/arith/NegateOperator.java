package language.arith;

import language.Operand;
import language.UnaryOperator;

/** The {@code NegateOperator} is an operator that performs negation on a single integer */

public class NegateOperator extends UnaryOperator<Integer> {

  @Override
  public Operand<Integer> performOperation() {
    if (op == null)
      throw new IllegalStateException("Could not perform operation prior to operand being set.");
    Integer result = op.getValue() * -1;
    return new Operand<Integer>(result);
  }
  
  public String toString() {
    return "!";
  }
}
