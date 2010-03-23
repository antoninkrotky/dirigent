package org.dirigent.generator.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

import org.dirigent.generator.Generator;
import org.dirigent.metafacade.Generatable;
import org.dirigent.metafacade.Pattern;
import org.dirigent.metafacade.PatternStep;
import org.dirigent.test.utils.FileComparator;

public class TestGenerator extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// create output directory if does not exist
		File outputDir = new File("output");
		if (!outputDir.exists() || !outputDir.isDirectory()) {
			outputDir.mkdir();
		}
		// delete output file
		File outputFile = new File("output/install.sql");
		outputFile.delete();

	}

	public void testGenerate() {
		Generator.generate(createGeneratable());
		FileComparator.assertEquals("results/generatorTest/TestGenerator.expected.install.sql",
				new File("output/install.sql"));
	}

	/**
	 * Helper method for creating generatable object. MetafacadeFactory should
	 * be used to create generatable object.
	 * */
	private Generatable createGeneratable() {
		Generatable g = new Generatable() {

			public String getName() {
				return "TestElement";
			}

			@Override
			public Pattern getPattern() {
				return new Pattern() {
					@Override
					public Collection<PatternStep> getSteps() {
						ArrayList<PatternStep> steps = new ArrayList<PatternStep>(
								1);
						steps.add(new PatternStep() {

							@Override
							public String getTemplate() {
								return "Element name: ${element.name}";
							}

							@Override
							public String getName() {
								return "TestPattern.1-TestStep";
							}
						});
						return steps;
					}

					@Override
					public String getName() {
						return "TestPattern";
					}
				};
			}

			@Override
			public String getUri() {
				return null;
			}
		};
		return g;
	}
}
