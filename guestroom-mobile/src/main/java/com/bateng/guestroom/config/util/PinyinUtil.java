package com.bateng.guestroom.config.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PinyinUtil {

    /**
     * 传递中文字符串获取拼音数组
     *
     * @return
     */
    public static String toPinyinString(String str) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);

        char[] arr = str.toCharArray();
        List<String> list = new ArrayList<String>();

        for (char c : arr) {
            if (c >= '0' && c <= '9')
                list.add(c + "");
            else {
                String[] s = null;
                try {
                    s = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (s != null)
                        list.add(s[0]);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }
        }
        String pin = "";
        for (String s : list) {
            pin += s;
        }
        return pin;
    }


    public static void main(String[] args) {

    }
}
