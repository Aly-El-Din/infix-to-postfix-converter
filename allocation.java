/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stackproblem;

/**
 *
 * @author Cyber
 */
public class allocation {
     public static final int Capacity=1000;  
  private int capacity;  
  private Object s[];
  private int top=-1;
  //initialize the array with the default capacity
  public allocation(){
      capacity=Capacity;
      s=new Object[capacity];
  }
  //initialize the array with parameterized capacity
  public allocation(int cap){
     capacity=cap;
     s=new Object[capacity];
  }
  public void push(Object val){
      if(top!=capacity){
          top=top+1;
          s[top]=val;
      }
      else{
          System.out.println("Error");
          return;
      }
  }  
  public int size(){
      return (top+1);
  }
  public void print(){
      System.out.print("[");
      for(int i=size()-1;i>=0;i--){
          System.out.print(s[i].operator);
          if(i!=0){
              System.out.print(", ");
          }
      }
      System.out.println("]");
  }
 public boolean isEmpty(){
     if(size()==0){
         return true;
     }
     return false;
 }
  public Object peek(){
       return s[top];
  }
  public Object pop(){
      Object elem;
      elem=s[top];
       s[top--]=null;
       return elem;
  }
    
}
