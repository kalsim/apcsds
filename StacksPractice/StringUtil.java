package StacksPractice;

import java.util.Stack;

/**
 * Provides methods for manipulating strings such as reversing a string and checking if it is a palindrome
 * 
 * @author Montek Kalsi
 * @version 11/2/2017
 */
public class StringUtil
{
    /**
     * reverse a string using a Stack and substring not charAt
     * @param str the string to be reversed
     * @return the reversed string
     */
    public static String reverseString(String str)
    {
        Stack<String> stacc = new Stack<>();
        for (int i=0; i<str.length(); i++){
            stacc.add(str.substring(i, i+1));
        }
        String rev = "";
        while (!stacc.isEmpty())
        {
            rev += stacc.pop();
        }
        return rev;
    }

    /**
     * Checks to see if string s is a palindrome
     * @param s the string being checked
     * @return true if s is a palindrome
     */
    public static boolean isPalindrome(String s)
    {
        return s.equals(reverseString(s));
    }

    /**
     * The tester for checking that reverse and isPalindrome work well.
     */
    public static void main(String[] args)
    {
        String test =  "racecar";
        String test2 = "notapalindrome";

        if ( !("".equalsIgnoreCase(reverseString(""))) )
            System.out.println("** Oops Something went wrong. Check your reverse method **");

        if ( !("a".equalsIgnoreCase(reverseString("a"))) )
            System.out.println("** Oops Something went wrong. Check your reverse method **");

        if (!test.equalsIgnoreCase(reverseString(test)))
            System.out.println("** Oops Something went wrong. Check your reverse method **");
        else
            System.out.println("Success " + test + " matched " + reverseString(test));
            
        if (test2.equalsIgnoreCase(reverseString(test2)))
            System.out.println("** Oops Something went wrong. Check your reverse method **");

    }
}
