package com.bsoft.tools.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GetStringForFile {

	public static final String getFileString(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuffer sb = new StringBuffer();
		String l = br.readLine();
		while (l != null) {	
			sb.append(l);
			l = br.readLine();
		}
		br.close();
		return sb.toString();
	}
	
}
