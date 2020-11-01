package StacksPractice;



import java.util.Stack;

/**
 * The Expressions class allows for operations to be carried out
 * on a string of operands by converting expressions into
 * postfix notation and evaluating postfix notation
 *
 * @author Montek Kalsi
 * @version 11/2/2017
 */

public class Expressions
{
    /**
     * parenthesis matching : An expression is said to be balanced if
     * every opener has a corresponding closer, in the right order
     * {, [ or ( are the only types of brackets allowed
     * @param   expression containing operands operators
     * and any of the 3 supported brackets
     * @return  true is the parenthesis are balanced
     * false otherwise
     */
    public static boolean matchParenthesis(String expression)
    {
        Stack<String> stacc = new Stack<String>();
        for (int i=0; i<expression.length(); i++)
        {
            String c = expression.substring(i, i+1);
            if (c.equals("("))
                stacc.add("(");
            else if (c.equals("["))
                stacc.add("[");
            else if (c.equals("{"))
                stacc.add("{");
            else if (c.equals(")") || c.equals("]") || c.equals("}"))
            {
                if (stacc.isEmpty())
                    return false;
                if (c.equals(")"))
                {
                    if (stacc.peek().equals("("))
                    {
                        stacc.pop();
                    }
                    else
                        return false;
                }
                else if (c.equals("]"))
                {
                    if (stacc.peek().equals("["))
                    {
                        stacc.pop();
                    }
                    else
                        return false;
                }
                else if (c.equals("}"))
                {
                    if (stacc.peek().equals("{"))
                    {
                        stacc.pop();
                    }
                    else
                        return false;
                }
            }
        }
        return stacc.isEmpty();
    }

    /**
     * Determines which operation has a higher precedence
     * @param str the operator in string form
     * @param stacc the stack which contains the operators which the
     *  operator str is compared to for precedence
     * @return true if the operator str has a higher precedence than the one on the top of the stack
     */
    public static boolean precedence(String str, Stack stacc)
    {
        if (stacc.isEmpty())
            return true;
        else if (str.equals("%") || str.equals("*") || str.equals("/"))
        {
            if (stacc.peek().equals("%") || stacc.peek().equals("*") || stacc.peek().equals("/"))
                return false;
            return true;
        }
        return false;
    }

    /**
     * returns a string in postfix form
     * if given an expression in infix form as a parameter
     * does this conversion using a Stack
     * @param expr valid expression in infix form
     * @return equivalent expression in postfix form
     */
    public static String infixToPostfix(String expr)
    {
        Stack<String> postFix = new Stack<String>();
        String strPostfix = "";
        int x = 0;
        for (int i = 0; i<expr.length(); i++)
        {
            x = i+1;
            String c = expr.substring(i, x);
            if (0 <= c.compareTo("0") && c.compareTo("9") <= 0)
            {
                while (x < expr.length() && !expr.substring(x, x+1).equals(" "))
                    x++;
                c = expr.substring(i, x);
                strPostfix += c;
                strPostfix += " ";
            }
            else if (!expr.substring(i, i+1).equals(" "))
            {

                if (precedence(expr.substring(i, i+1), postFix))
                    postFix.push(expr.substring(i, i+1));
                else
                {
                    while (!precedence(expr.substring(i, i+1), postFix))
                    {
                        strPostfix += postFix.pop();
                        strPostfix += " ";
                    }
                    postFix.push(expr.substring(i, i+1));
                }
            }
            i = x;
        }
        while (!postFix.isEmpty())
            strPostfix += postFix.pop() + " ";
        return strPostfix;
    }

    /**
     * returns the value of an expression in postfix form
     * does this computation using a Stack
     * @param expr valid expression in postfix form
     * @return value of the expression
     * @precondition postfix expression
     *               contains numbers and operators + - * / and %
     *               and that operands and operators are separated by spaces
     */
    public static double evalPostfix(String expr)
    {
        Stack<Double> postfixOperands = new Stack<Double>();
        int x = 0;
        for (int i = 0; i<expr.length(); i++)
        {
            double a;
            double b;
            x = i + 1;
            String c = expr.substring(i, x);
            if (0 <= c.compareTo("0") && c.compareTo("9") <= 0)
            {
                while (x < expr.length() && !expr.substring(x, x+1).equals(" "))
                    x++;
                c = expr.substring(i, x);
                postfixOperands.push((double) Integer.parseInt(c));
            }
            else if (c.equals("/"))
            {
                a = postfixOperands.pop();
                b = postfixOperands.pop();
                postfixOperands.push((double) b / a);
            }
            else if (c.equals("*"))
            {
                a = postfixOperands.pop();
                b = postfixOperands.pop();
                postfixOperands.push((double)b * a);
            }
            else if (c.equals("+"))
            {
                a = postfixOperands.pop();
                b = postfixOperands.pop();
                postfixOperands.push((double)b + a);
            }
            else if (c.equals("-"))
            {
                a = postfixOperands.pop();
                b = postfixOperands.pop();
                postfixOperands.push((double)b - a);
            }
            else if (c.equals("%"))
            {
                a = postfixOperands.pop();
                b = postfixOperands.pop();
                postfixOperands.push((double)b % a);
            }
            i = x;
        }
        return postfixOperands.peek();
    }

    /**
     * Tester to check if infix to postfix and evaluate postfix work well
     */
    public static void main(String[] args)
    {
        String exp = "2 + 3 * 4";
        test(exp, 14);

        exp = "8 * 12 / 2";
        test(exp, 48);

        exp = "5 % 2 + 3 * 2 - 4 / 2";
        test(exp, 5);

        // test balanced expressions
        testBalanced("{ 2 + 3 } * ( 4 + 3 )", true);
        testBalanced("} 4 + 4 { * ( 4 + 3 )", false);
        testBalanced("[ [ [ ] ]", false);
        testBalanced("{ ( } )", false);
        testBalanced("( ( ( ) ) )", true);
    }

    /**
     * Tests whether the methods infixToPostfix and evalPostfix function correctly
     * @param expr the string of operands and operators passed into the methods
     * @param expect the expected value after the method is complete
     */
    public static void test(String expr, double expect)
    {
        String post = infixToPostfix(expr);
        double val = evalPostfix(post);

        System.out.println("Infix: " + expr);
        System.out.println("Postfix: " + post);
        System.out.println("Value: " + val);
        if (val == expect)
        {
            System.out.print("** Success! Great Job **");
        }
        else
        {
            System.out.print("** Oops! Something went wrong. ");
            System.out.println("Check your postfix and eval methods **");
        }
    }

    /**
     * Tests the parentheses matching method
     * @param ex the string of parentheses
     * @param expected the expected output
     */
    public static void testBalanced(String ex, boolean expected)
    {
        boolean act = matchParenthesis(ex);
        if (act == expected)
            System.out.println("** Success!: matchParenthesis(" + ex + ") returned " + act);
        else
        {
            System.out.print("** Oops! Something went wrong check : matchParen(" + ex + ")");
            System.out.println(" returned " + act + " but should have returned " + expected);
        }
    }
}
