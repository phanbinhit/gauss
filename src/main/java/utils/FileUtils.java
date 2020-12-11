/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.desktop.FilesEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author OS
 */
public class FileUtils {

    private FileUtils() {

    }

    public static List<List<String>> readFile(String path) {
        File file = new File(path);
        List<List<String>> result = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            result =  lines.stream()
                    .map(line -> Pattern.compile("[\\s]+").splitAsStream(line).collect(Collectors.toList()))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
        }
        return result;
    }
    
    public static List<Double> getListDouble(String line) {
        return Pattern.compile("[\\s]+")
                .splitAsStream(line)
                .map((String s) -> {
                    try {
                        Double d = Double.parseDouble(s);
                        return d;
                    } catch (Exception e) {
                        e.printStackTrace();
                        //return null;
                    }
                })
                .collect(Collectors.toList());
    }
    
    public static boolean validateInput(List<List<Double>> matrix) {
        for (List<Double> list: matrix) {
            for (Double d: list) {
                if(d == null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void print(double[][] matrix) {
        for (double[] arr: matrix) {
            for (double d: arr) {
                System.out.print(d + " ");
            }
            System.out.println();
        }
    }
    
    public static double[][] loadData(List<List<String>> listMatrix) {
        double[][] matrix = new double[listMatrix.size()][listMatrix.get(0).size()];
        for(int i = 0 ; i < listMatrix.size(); i++) {
            for(int j = 0 ; j < listMatrix.get(i).size(); j++) {
                try {
                    matrix[i][j] = Double.parseDouble(listMatrix.get(i).get(j));
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return matrix;
    }
}
