package org.dirigent.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;
import org.dirigent.test.utils.FileComparator;

public class TestFileExecutor extends TestCase {
	private File testDir = new File("test");
	private File appendFile = new File(testDir, "Append.txt");
	private File overwriteFile = new File(testDir, "Overwrite.txt");
	private File createFile = new File(testDir, "Create.txt");
	private File configFile = new File(testDir, "Config.txt");

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.getProperties().setProperty("dirigent.model.path","DIRIGENT");
		DirigentConfig.resetConfig();
		testDir.mkdir();
		appendFile.delete();
		overwriteFile.delete();
		createFile.delete();
	}

	public void testFileExecutor() {
		IGeneratable gen = createGeneratable();
		assertFalse(appendFile.exists());
		assertFalse(overwriteFile.exists());
		assertFalse(createFile.exists());
		Generator.generatedStack.set(new LinkedList<String>());
		Generator.generate(gen);
		FileComparator.assertEquals("results/fileExecutor/Append.txt",
				appendFile);
		FileComparator.assertEquals("results/fileExecutor/Overwrite.txt",
				overwriteFile);
		FileComparator.assertEquals("results/fileExecutor/Create.txt",
				createFile);
		appendToFile(appendFile);
		appendToFile(overwriteFile);
		appendToFile(createFile);
		Generator.generatedStack.set(new LinkedList<String>());
		Generator.generate(gen);
		FileComparator.assertEquals("results/fileExecutor/Append2.txt",
				appendFile);
		FileComparator.assertEquals("results/fileExecutor/Overwrite.txt",
				overwriteFile);
		FileComparator.assertEquals("results/fileExecutor/Create2.txt",
				createFile);
		FileComparator.assertEquals("results/fileExecutor/Config.txt",
				configFile);

	}

	private void appendToFile(File f) {
		try {
			FileOutputStream fo = new FileOutputStream(f,true);
			fo.write(" This text was appended by TestFileExecutor.\n".getBytes());
			fo.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	private IGeneratable createGeneratable() {
		return new IGeneratable() {
			private IPattern p = PatternBuilder.getPatternBuilder().getPattern(
					"TEST/TEST_FILE_EXECUTOR.pattern.xml");

			@Override
			public IPattern getPattern() {
				return p;
			}

			@Override
			public String getName() {
				return "FileExecutorTest";
			}
			
			@Override
			public String getUri() {
				return null;
			}

		};

	}
}
