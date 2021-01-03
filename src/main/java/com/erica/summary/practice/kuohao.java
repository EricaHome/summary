package com.erica.summary.practice;

/**
 * @author Erica
 * @date 2020/12/26 23:49
 * 括号闭合:
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 输入例子1:"{[]}"
 * 输出例子1:true
 */
public class kuohao {

    /**
     * 判断是否满足条件
     * @param s string字符串 字符串s
     * @return bool布尔型
     */
    public boolean isValid (String s) {
        // write code here
        if (s == null || s == "") return true;
        char chars[] = new char[s.length()];
        int index = 0;
        for (int i = 0; i < chars.length;i++) {
            if (index > 0 && chars[index-1] == '(' && s.charAt(i) == ')') {
                index--;
            } else if (index > 0 && chars[index-1] == '{' && s.charAt(i) == '}') {
                index--;
            } else if (index > 0 && chars[index-1] == '[' && s.charAt(i) == ']') {
                index--;
            } else {
                chars[index++] = s.charAt(i);
            }
        }
        return index >0 ? false : true;
    }
}
