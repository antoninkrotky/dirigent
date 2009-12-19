package org.dirigent.test.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import junit.framework.AssertionFailedError;

/**
 * File comparator utility used in test cases to compare generated results to
 * etalon files.
 * */
public class FileComparator {
	public static void assertEquals(InputStream expected, String actual) {
		try {
			assertEquals(expected, new ByteArrayInputStream(actual.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void assertEquals(InputStream expected, File actualFilePath) {
		try {
			FileInputStream actIn = new FileInputStream(actualFilePath);
			assertEquals(expected, actIn);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void assertEquals(InputStream expected, InputStream actual) {
		try {
			LineNumberReader exp = new LineNumberReader(new InputStreamReader(
					expected));
			LineNumberReader act = new LineNumberReader(new InputStreamReader(
					actual));
			int line = 1;
			while (act.ready() || exp.ready()) {

				if (!act.ready() || !exp.ready()
						|| !act.readLine().equals(exp.readLine())) {
					throw new AssertionFailedError(
							"File comparison error on line " + line);
				}
				line++;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
