package evaluator.arith;

import evaluator.PostfixEvaluator;
import language.Operand;
import language.Operator;
import language.arith.DivOperator;
import language.arith.MultOperator;
import language.arith.NegateOperator;
import language.arith.PlusOperator;
import language.arith.SubOperator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;

/** An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions. */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

  private final StackInterface<Operand<Integer>> stack;
  /** Constructs an {@link ArithPostfixEvaluator} */
  public ArithPostfixEvaluator() {
    stack = new LinkedStack<>();
  }

  /** {@inheritDoc} */
  @Override
  public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
    ArithPostfixParser parser = new ArithPostfixParser(expr);
    for (Token<Integer> token : parser) {
      Type type = token.getType();
      switch (type) {
        case OPERAND:
          stack.push(token.getOperand());
          break;
        case OPERATOR:
          Operator<Integer> operator = token.getOperator();
          String op = operator.toString();
          if(op.equals("!")){
            Operator<Integer> negOperator = new NegateOperator();
            negOperator.setOperand(0, stack.pop());
            stack.push(negOperator.performOperation());
          }
          else if(op.equals("+")){
            Operator<Integer> plusOperator = new PlusOperator();
            plusOperator.setOperand(0, stack.pop());
            plusOperator.setOperand(1, stack.pop());
            stack.push(plusOperator.performOperation());
          }
          else if(op.equals("-")){
            Operator<Integer> subOperator = new SubOperator();
            subOperator.setOperand(1, stack.pop());
            subOperator.setOperand(0, stack.pop());
            stack.push(subOperator.performOperation());
          }
          else if(op.equals("*")){
            Operator<Integer> multOperator = new MultOperator();
            multOperator.setOperand(0, stack.pop());
            multOperator.setOperand(1, stack.pop());
            stack.push(multOperator.performOperation());
          }
          else if(op.equals("/")){
            Operator<Integer> divOperator = new DivOperator();
            divOperator.setOperand(1, stack.pop());
            divOperator.setOperand(0, stack.pop());
            stack.push(divOperator.performOperation());
          }
          break;
        default:
          throw new IllegalStateException("Parser returned an invalid Type: " + type);
      }
    }
    if(stack.size() != 1){
      throw new IllegalPostfixExpressionException();
  }
    return stack.pop().getValue();
  }
}
