package com.bsoft.support.utils;

//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class StringUtils {
	/**
	 * 获取字符串的拼音大写首字母
	 * 
	 * @param str
	 * @return
	 */
//	public static String pinyin(String str) {
//		char b;
//		String reStr = null;
//		int a = str.length();
//		for (int k = 0; k < a; k++) {
//			b = str.charAt(k);
//			String d = String.valueOf(b);
//			String str1 = converterToFirstSpell(d);
//			String s = str1.toUpperCase();
//			String g = s;
//			char h;
//			for (int y = 0; y <= 0; y++) {
//				h = g.charAt(0);
//				if (reStr == null) {
//					reStr = h + "";
//				} else {
//					reStr = reStr + h;
//				}
//
//			}
//		}
//		// System.out.println(reStr);
//		return reStr;
//	}

//	public static String converterToFirstSpell(String chines) {
//		String pinyinName = "";
//		char[] nameChar = chines.toCharArray();
//		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		for (int i = 0; i < nameChar.length; i++) {
//			String s = String.valueOf(nameChar[i]);
//			if (s.matches("[\\u4e00-\\u9fa5]")) {
//				try {
//					String[] mPinyinArray = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
//					pinyinName += mPinyinArray[0];
//				} catch (BadHanyuPinyinOutputFormatCombination e) {
//					e.printStackTrace();
//				}
//			} else {
//				pinyinName += nameChar[i];
//			}
//		}
//		return pinyinName;
//	}
}
