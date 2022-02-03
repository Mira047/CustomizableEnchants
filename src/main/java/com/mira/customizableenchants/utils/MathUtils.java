package com.mira.customizableenchants.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;

public class MathUtils {
    public static int evaluateQuestion(String question, int level) {
        Scanner sc = new Scanner(question);

        if(question.startsWith("level")) {
            String a = sc.findInLine("level").trim();

            String operator = sc.findInLine("[^0-9]*").trim();

            int value = Integer.parseInt(sc.findInLine("[0-9]*"));

            switch (operator){
                case "+":
                    return level + value;
                case "-":
                    return level - value;
                case "/":
                    return level / value;
                case "*":
                    return level * value;
                case "%":
                    return level % value;

                default:
                    throw new RuntimeException("unknown operator: "+operator);
            }
        } else if(question.endsWith("level")) {
            int value = Integer.parseInt(sc.findInLine("[0-9]*"));

            String operator = sc.findInLine("[^0-9]*").trim();

            operator = operator.substring(0, operator.length() - 5);

            switch (operator){
                case "+":
                    return level + value;
                case "-":
                    return value - level;
                case "/":
                    return value / level;
                case "*":
                    return level * value;
                case "%":
                    return value % level;

                default:
                    throw new RuntimeException("unknown operator: "+operator);
            }
        }
        else {
            int value = Integer.parseInt(question);
            return value;
        }
    }

}
