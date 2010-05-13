package org.dirigent.test.utils;

import java.io.BufferedWriter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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

	public static void assertEquals(String expected, String actual) {
		ByteArrayInputStream inputStream = null;
		InputStream goldenFile = null;
		try {
			inputStream = new ByteArrayInputStream(actual.getBytes());
			try {
				goldenFile = new FileInputStream(new File(expected));
			} catch (FileNotFoundException fnf) {
				createGoldenFile(expected, actual);				
				throw new AssertionFailedError(
						"Couldn't find file to compare so new one has been created. Re-check and re-run failing tests.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("Actual query:\n"+actual);
		assertEquals(goldenFile, inputStream);

	}

	public static void assertEquals(String expected, File actualFilePath) {
		InputStream goldenFile = null;
		try {
			FileInputStream actIn = new FileInputStream(actualFilePath);
			try {
				goldenFile = new FileInputStream(new File(expected));
			} catch (FileNotFoundException fnf) {
				createGoldenFile(expected, actualFilePath);
				throw new AssertionFailedError(
						"Couldn't find file to compare so new one has been created. Re-check and re-run failing tests.");
			}
			assertEquals(goldenFile, actIn);
			actIn.close();
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
			String is;
			String shouldBe;
			while (act.ready() || exp.ready()) {
				is = act.readLine();
				shouldBe = exp.readLine();
				// !act.ready() || !exp.ready() || - commented out (will se if
				// temporarily)
				if (!is.equals(shouldBe)) {
					throw new AssertionFailedError(
							"File comparison error on line " + line + "\n"
									+ "was: >" + is + "<\n" + "expected was: >"
									+ shouldBe + "<");
				}
				line++;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void createGoldenFile(String expected, String actual)
			throws IOException {
		File newFile = new File(expected);
		FileWriter fstream;

		fstream = new FileWriter(newFile);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(actual);
		out.close();
	}

	private static void createGoldenFile(String expected, File actualPath)
			throws IOException, FileNotFoundException {

		BufferedWriter out = new BufferedWriter(new FileWriter(new File(
				expected)));

		LineNumberReader actual = new LineNumberReader(new InputStreamReader(
				new FileInputStream(actualPath)));
		String line;

		while ((line = actual.readLine()) != null) {

			out.write(line);
		}
		out.close();

	}
}
