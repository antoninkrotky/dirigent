package org.dirigent.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.AssertionFailedError;

/**
 * File comparator utility used in test cases to compare generated results to etalon files.
 * */
public class FileComparator {
	public static void assertEquals(InputStream expected, String actualFilePath) {
		try {
		FileInputStream actIn=new FileInputStream(actualFilePath);
		int pos=0;
		while (actIn.available()>0 || expected.available()>0) {
			if (actIn.read()!=expected.read()) {
				throw new AssertionFailedError("File comparison error on position "+pos);
			}
			pos++;
		}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
