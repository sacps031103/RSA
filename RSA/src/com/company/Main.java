package com.company;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BigInteger p = new BigInteger("7661333881203417516962494611008362951740312908291438097341481931150663039620582479610440537665294097");
        BigInteger q = new BigInteger("9976906837832581626646736058604930852786115300610741714761181227222488280367302542873612115463582473");
        BigInteger e = new BigInteger("2437229500141640519614712219487279111689063320218144162841451337357195176792449536422780486416766491");
        BigInteger PublicKey = p.multiply(q);//n = p * q;
        BigInteger t1 = p.subtract(BigInteger.valueOf(1));
        BigInteger t2 = q.subtract(BigInteger.valueOf(1));
        BigInteger t = t1.multiply(t2);
        System.out.println("PublicKey  "+PublicKey);
        System.out.println("e "+e);
        BigInteger privateKey = e.modInverse(t);//d = e^-1 mod t
        System.out.println("PrivateKey  "+privateKey);
        while (true) {
            int tect;
            System.out.println("encrypt data 1 or decrypt data 2 ");
            Scanner input1 = new Scanner(System.in);
            tect = input1.nextInt();
            switch (tect)
            {
                case 1:
                    String message;
                    System.out.println("encrypt data:");
                    Scanner input2 = new Scanner (System.in);
                    message = input2.next();
                    BigInteger encryptInt = new BigInteger("1"+toBinary(message, 8), 2);
                    System.out.println("encrypt data  = "+encryptInt.modPow(e, PublicKey));
                    break;
                case 2:
                    System.out.println("decrypt data:");
                    Scanner input3 = new Scanner (System.in);
                    String Data = input3.next();
                    BigInteger IntegerData = new BigInteger(Data);
                    String binaryString = IntegerData.modPow(privateKey, PublicKey).toString(2);
                    binaryString = binaryString.substring(1, binaryString.length());
                    String decryptMessage="";
                    for(int i=0;i<=binaryString.length()-8;i+=8 ){
                        decryptMessage+=(char)Integer.parseInt(binaryString.substring(i, i+8),2);
                    }
                    System.out.println("decrypt data  = "+decryptMessage);
                    break;
            }
        }

    }
    public static String toBinary(String str, int bits) {
        String result = "";
        String tmpStr;
        int tmpInt;
        char[] messChar = str.toCharArray();

        for (int i = 0; i < messChar.length; i++) {
            tmpStr = Integer.toBinaryString(messChar[i]);
            tmpInt = tmpStr.length();
            if(tmpInt != bits) {
                tmpInt = bits - tmpInt;
                if (tmpInt == bits) {
                    result += tmpStr;
                } else if (tmpInt > 0) {
                    for (int j = 0; j < tmpInt; j++) {
                        result += "0";
                    }
                    result += tmpStr;
                } else {
                    System.err.println("argument 'bits' is too small");
                }
            } else {
                result += tmpStr;
            }
            //result += " ";
        }
        return result;
    }
}
