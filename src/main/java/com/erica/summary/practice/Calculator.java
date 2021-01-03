package com.erica.summary.practice;

import java.util.Stack;

/**
 * @author Erica
 * @date 2021/1/3 19:29
 * @description
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 * 示例 :
 * 输入: "3+2*2" 输出: 7
 * 输入:"3+5/2" 输出: 5
 */
public class Calculator {

    public static void main(String[] args) {
        System.out.println(calculate("3+5/2"));
    }

    /**
     * 计算器
     * @param s string字符串 公式
     * @return long长整型
     */
    public static long calculate (String s) {
        // 保存上一个符号，初始为 +
        char sign = '+';
        Stack<Integer> numStack = new Stack<>();
        // 保存当前数字，如：12是两个字符，需要进位累加
        int num = 0;
        int result = 0;
        for(int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            if(cur >= '0'){
                // 处理两位数的问题
                num = num*10 - '0' + cur;
            }
            // 是一个运算符 或者是 最后一个空格
            //cur < '0' 代表它是一个运算符
            if((cur < '0' && cur !=' ' )|| i == s.length()-1){
                // 判断上一个符号是什么
                switch(sign){
                    // 当前符号前的数字直接压栈
                    case '+': numStack.push(num);break;
                    // 当前符号前的数字取反压栈
                    case '-': numStack.push(-num);break;
                    // 数字栈栈顶数字出栈，与当前符号前的数字相乘，结果值压栈
                    case '*': numStack.push(numStack.pop()*num);break;
                    // 数字栈栈顶数字出栈，除于当前符号前的数字，结果值压栈
                    case '/': numStack.push(numStack.pop()/num);break;
                }
                // 记录当前符号
                sign = cur;
                // 数字清零
                num = 0;
            }
        }
        // 将栈内剩余数字累加，即为结果
        while(!numStack.isEmpty()){
            result += numStack.pop();
        }
        return result;

    }
}
