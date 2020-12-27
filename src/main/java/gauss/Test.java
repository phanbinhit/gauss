/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauss;

import java.util.Random;

/**
 *
 * @author OS
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 1; i <= 50; i++) {
            for (int j = 0; j <= 50; j++) {
            int n = -10 + new Random().nextInt(20);
            System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
