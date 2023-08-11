package com.example.community.exception;

import java.util.Scanner;

/**
 * @author: lichengcan
 * @date: 2023-08-11 21:42
 * @description
 **/
public class TryWithResource {

    public static void main(String[] args) {

        //把需要创建的资源放到（）,用完的时候会自己关闭,避免了资源没关、浪费资源
        try (Scanner input = new Scanner("1")){

        }catch (Exception E){

        }
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")))) {
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
