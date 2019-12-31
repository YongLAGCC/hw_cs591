package com.example.c3_p29;

public class Calculator {

    private int chicken;
    private int vegetable;
    private int potato;
    private int icecream;

    private int chickenCal = 200;
    private int vegetableCal = 50;
    private int potatoCal = 60;
    private int icecreamCal = 300;

    public Calculator(){
        chicken = 0;
        vegetable = 0;
        potato = 0;
        icecream = 0;
    }

    public int calculate(int c, int v, int p, int i){
        return c*chickenCal + v*vegetableCal + p*potatoCal + i*icecreamCal;
    }


}
