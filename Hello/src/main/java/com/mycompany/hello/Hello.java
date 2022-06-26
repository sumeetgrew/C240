/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hello;

/**
 *
 * @author Sumeet
 */
public class Hello {
    public static void main(String[] args) {
        int[] starNum = {100, 7, 1000, 5};
        
        for (int i=0; i < starNum.length; i++) {
            String str = "*";
            System.out.println(str.repeat(starNum[i]));
        }
    }
}
