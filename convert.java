/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stackproblem;

/**
 *
 * @author Cyber
 */
public class convert {
    public int precedence(char operator){
        int preced;
        if(operator=='-'||operator=='+'){
            preced=1;
        }
        else if(operator=='*'||operator=='/'){
            preced=2;
        }
        else if(operator=='^'){//^
            preced=3;
        }
        else{
            preced=0;
        }
        return preced;
    }
    public String infixTopostfix(String expression){
        char comp;
        String postfixExpression="";
        allocation myStack=new allocation();
        for(int i=0;i<expression.length();i++){
           comp=expression.charAt(i);
           if(comp=='+'||comp=='-'||comp=='/'||comp=='*'||comp=='^'||comp=='('||comp==')'){
               Object operator=new Object(expression.charAt(i),0);
               if(myStack.isEmpty()){
                    myStack.push(operator);                       
               }
               else{
                    int preced1=precedence(comp);
                    int preced2=precedence(myStack.peek().operator);
                    if(preced1<=preced2 && comp!='(' && comp!=')'){
                        while(!myStack.isEmpty() && preced1<=precedence(myStack.peek().operator)){
                            postfixExpression+=myStack.pop().operator;                           
                        }
                        myStack.push(operator);
                    }
                    else if((preced1>preced2 && comp!=')')|| comp=='('){
                        myStack.push(operator);
                    }
                    else if(comp==')'){
                        while(myStack.peek().operator!='('){
                             postfixExpression+=myStack.pop().operator;
                        }
                        myStack.pop();
                    }
               }               
           }
           else{
               postfixExpression+=comp;
           }
        }
        while(!myStack.isEmpty()){
            postfixExpression+=myStack.pop().operator;
        }
        return postfixExpression;
    }

    public int value(Object operand,int a,int b,int c){
        if(operand.operator=='a'){
            return a;
        }
        else if(operand.operator=='b'){
            return b;
        }
        else if(operand.operator=='c'){
            return c;
        }
        else{
            return operand.value;
        }
    }
    public int evaluate(String expression,int a,int b,int c){
        double val1,val2;
        int res=0;
        allocation evalStack=new allocation();
        Object operand=new Object('a',0);
        for(int i=0;i<expression.length();i++){
            Object r=new Object('r',res);
            switch(expression.charAt(i)){
                case '+':
                    val1=value(evalStack.pop(),a,b,c);
                    val2=value(evalStack.pop(),a,b,c);
                    res=(int)(val1+val2);
                    r.value=res;
                    evalStack.push(r);
                    break;
                case '-':
                    val1=value(evalStack.pop(),a,b,c);
                    if(!evalStack.isEmpty()){
                        val2=value(evalStack.pop(),a,b,c);                        
                          res=(int)(val2-val1);
                          r.value=res;
                          evalStack.push(r);
                    }                   
                    else{
                        res=(-res);
                    }
                    break;
                case '*':
                    val1=value(evalStack.pop(),a,b,c);
                    val2=value(evalStack.pop(),a,b,c);
                    res=(int)(val1*val2);
                    r.value=res;
                    evalStack.push(r);
                    break;
                case '/':
                    val1=value(evalStack.pop(),a,b,c);
                    val2=value(evalStack.pop(),a,b,c);
                    if(val1==0){
                        System.out.println("Error");
                        System.exit(0);
                    }
                    res=(int)(val2/val1);
                    r.value=res;
                    evalStack.push(r);
                    break;
                case '^':
                    val1=value(evalStack.pop(),a,b,c);
                    val2=value(evalStack.pop(),a,b,c);
                    res=(int)(Math.pow(val2,val1));
                    r.value=res;
                    evalStack.push(r);
                    break;
                default:
                    operand=new Object(expression.charAt(i),value(operand,a,b,c));
                    evalStack.push(operand);
            }
        }
        if(res==0){
            if(expression.length()==1){
                return operand.value;
            }
            else if(expression.length()==2){
                return -(operand.value);
            }
        }
        return res;
    }
}
