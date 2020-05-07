//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.company;

import java.util.Scanner;

public class CmdCalc {
    Scanner scanner;
    boolean isFail;
    int num1;
    int num2;
    String operation;
    boolean isRim1;
    boolean isRim2;

    public CmdCalc() {
        this.scanner = new Scanner(System.in);
        this.isFail = false;
        this.num1 = 0;
        this.num2 = 0;
        this.operation = "";
        this.isRim1 = false;
        this.isRim2 = false;
        System.out.println("Input:");
        String[] dataCalc = this.readCmd();
        if (!this.isFail) {
            this.parserDataCmd(dataCalc);
            if (!this.isFail) {
                int resultOperation = this.runOperation();
                if (!this.isFail) {
                    if (this.isRim1 && this.isRim2) {
                        int[] mas1 = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
                        String[] mas2 = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
                        int i = 0;
                        String s = "";

                        while(resultOperation > 0) {
                            if (mas1[i] <= resultOperation) {
                                resultOperation -= mas1[i];
                                s = s + mas2[i];
                            } else {
                                ++i;
                            }
                        }

                        System.out.println("Output: " + s);
                    } else {
                        System.out.println("Output: " + resultOperation);
                    }

                }
            }
        }
    }

    String[] readCmd() {
        String cmdCalc = "";
        cmdCalc = this.scanner.nextLine();
        String[] dataCalc = cmdCalc.split(" ");
        if (dataCalc.length != 3) {
            System.out.println("Количество допустимых операндов превышает допустимое значение (пример, 1 + 2):");
            return this.readCmd();
        } else {
            return dataCalc;
        }
    }

    void parserDataCmd(String[] data) {
        this.num1 = this.getNum(data[0]);
        if (!this.isFail) {
            this.num2 = this.getNum(data[2]);
            if (!this.isFail) {
                this.operation = data[1];
            }
        }
    }

    int getNum(String data) {
        if (this.tryParseInt(data)) {
            return Integer.parseInt(data);
        } else {
            int[] arab = new int[]{10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            String[] rome = new String[]{"X", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

            for(int i = 0; i < rome.length; ++i) {
                if (data.equals(rome[i])) {
                    if (this.isRim1 && !this.isRim2) {
                        this.isRim2 = true;
                    }

                    if (!this.isRim1 && !this.isRim2) {
                        this.isRim1 = true;
                    }

                    return arab[i];
                }
            }

            System.out.println("Недопустимое значение одной из переменной (пример, 3 или III)");
            this.isFail = true;
            return 0;
        }
    }

    int runOperation() {
        String var1 = this.operation;
        byte var2 = -1;
        switch(var1.hashCode()) {
            case 42:
                if (var1.equals("*")) {
                    var2 = 2;
                }
                break;
            case 43:
                if (var1.equals("+")) {
                    var2 = 0;
                }
            case 44:
            case 46:
            default:
                break;
            case 45:
                if (var1.equals("-")) {
                    var2 = 1;
                }
                break;
            case 47:
                if (var1.equals("/")) {
                    var2 = 3;
                }
        }

        switch(var2) {
            case 0:
                return this.num1 + this.num2;
            case 1:
                return this.num1 - this.num2;
            case 2:
                return this.num1 * this.num2;
            case 3:
                try {
                    return this.num1 / this.num2;
                } catch (Exception var4) {
                    System.out.println("Ошибка при операции деления");
                    System.out.println(var4.getMessage());
                    this.isFail = true;
                    return 0;
                }
            default:
                System.out.println("Недопустимое значение операции (пример, +, -, * или -)");
                this.isFail = true;
                return 0;
        }
    }

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException var3) {
            return false;
        }
    }
}

