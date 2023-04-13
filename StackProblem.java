/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stackproblem;
/**
 *
 * @author Cyber
 */
import java.util.Scanner;
public class StackProblem {
    public static int parse(String exp){
        int val;
        if(exp.charAt(2)=='-'){
            val=-(Integer.parseInt(exp.substring(3)));
        }
        else{
            val=Integer.parseInt(exp.substring(2));
        }
        return val;
    }
    public static void main(String[] args) {
        Scanner cin=new Scanner(System.in);
        String expression,A,B,C;
        String auxExp="";
        int a,b,c;
        expression=cin.nextLine();
        A=cin.nextLine();
        a=parse(A);
        B=cin.nextLine();
        b=parse(B);
        C=cin.nextLine();
        c=parse(C);
        //empty string
        if(expression.isEmpty()){
            System.out.println("Error");
            return;
        }
        int openBracket=0,closedBracket=0;
        for(int i=0;i<expression.length();i++){
            char ch=expression.charAt(i);
            //handling unknown chars
            if(ch!='a' && ch!='b' &&ch!='c'&& ch!='+'&& ch!='-'&&ch!='*'&&ch!='/'&&ch!='^'&&ch!='('&&ch!=')'){
                System.out.println("Error");
                return;
            }
            //handling -- -->+
            
            if((i!=(expression.length()-1)) && expression.charAt(i)==expression.charAt(i+1)&& expression.charAt(i)=='-'){
                //operator --
                if(i!=0 && expression.charAt(i-1)!='a'&& expression.charAt(i-1)!='b'&& expression.charAt(i-1)!='c'&& expression.charAt(i-1)!='('&& expression.charAt(i-1)!=')'){
                    expression=expression.substring(0, i)+expression.substring(i+2);
                }
                else if(i!=(expression.length()-2) && (expression.charAt(i+2)=='+'|| expression.charAt(i+2)=='-'|| expression.charAt(i+2)=='*'|| expression.charAt(i+2)=='/'|| expression.charAt(i+2)=='^')){
                    expression=expression.substring(0, i)+expression.substring(i+2);
                }
                else{
                    expression=expression.substring(0, i)+'+'+expression.substring(i+2);                    
                }
            }
        }
        
        //handling + in the first 
        while(expression.charAt(0)=='+' && expression.length()!=1){
            expression=expression.substring(1);
        }
        //handling ++ ** // ^^ aa bb cc 
        for(int i=0;i<expression.length();i++){
            if(i!=expression.length()-1&&expression.charAt(i)==expression.charAt(i+1)&&expression.charAt(i)!='-'&&expression.charAt(i)!='('&&expression.charAt(i)!=')'){
                System.out.println("Error");
                return;
            }
        }
        //handling the first element
        if(expression.charAt(0)!='a'&&expression.charAt(0)!='b'&&expression.charAt(0)!='c'&&expression.charAt(0)!='('&&expression.charAt(0)!='-'){
            System.out.println("Error");
            return;
        }
        //handle negative
        if(expression.charAt(0)=='-' && expression.charAt(1)!='('){
            auxExp=expression;
                   auxExp=auxExp.substring(1);
                    switch(auxExp.charAt(0)){
                        case 'a':
                            a=-a;
                            break;
                        case 'b':
                            b=-b;
                            break;
                        case 'c':
                            c=-c;
                            break;
                    }
        }
        for(int i=0;i<expression.length();i++){
            char check=expression.charAt(i);
            //checking which char could be taken after a or b or c or )
            if(check=='a' || check=='b' || check=='c' || check==')'){
                if(i!=(expression.length()-1) && expression.charAt(i+1)!='a' && expression.charAt(i+1)!='b' && expression.charAt(i+1)!='c' &&expression.charAt(i+1)!='('){
                    continue;
                }
                else {
                    if(i!=expression.length()-1){
                        System.out.println("Error");
                        return;
                    }
                }
            }
            //check which char could be taken after operator
            if(check!='a'&&check!='b'&&check!='c' && check!=')'){
                if(check=='(' && expression.charAt(i+1)=='+'){
                    expression=expression.substring(0,i+1)+expression.substring(i+2);
                }
                else if(check=='(' && expression.charAt(i+1)=='-'){
                   auxExp=expression;
                   auxExp=auxExp.substring(0,i+1)+expression.substring(i+2);
                    switch(auxExp.charAt(i+1)){
                        case 'a':
                            a=-a;
                            break;
                        case 'b':
                            b=-b;
                            break;
                        case 'c':
                            c=-c;
                            break;
                    }
                    continue;
                }
                else if(i!=expression.length()-1 && (expression.charAt(i+1)=='a' || expression.charAt(i+1)=='b' || expression.charAt(i+1)=='c' || expression.charAt(i+1)=='(')){
                    continue;
                }   
                else{
                      if(i!=expression.length()-1){
                        System.out.println("Error");
                        return;
                    }
                }
            }
        }
        //counting number of brackets
        for(int i=0;i<expression.length();i++){
            if(expression.charAt(i)=='('){
                openBracket++;
            }
            if(expression.charAt(i)==')'){
                closedBracket++;
            }
        }    
        //check last element
        if(expression.charAt(expression.length()-1)!='a' && expression.charAt(expression.length()-1)!='b'&&expression.charAt(expression.length()-1)!='c'&&expression.charAt(expression.length()-1)!=')'){
            System.out.println("Error");
            return;
        }
        //check brackets
        if(openBracket!=closedBracket){
            System.out.println("Error");
            return;
        }
        convert eval=new convert();
        System.out.println(eval.infixTopostfix(expression));
        if(auxExp.isEmpty()){
            System.out.println(eval.evaluate(eval.infixTopostfix(expression), a, b, c));
        }
        else{
            System.out.println(eval.evaluate(eval.infixTopostfix(auxExp), a, b, c));
        }
    }
}
