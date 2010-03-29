package org.dirigent.generator.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.generator.Generator;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.IPatternStep;
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
		FileComparator.assertEquals("results/generatorTest/TestGenerator.testGenerate.expected.install.sql",
				new File("output/install.sql"));
	}
	
	public void testGenerateMapping() throws Exception{
		String path = "resources/builderTestFiles/model_1";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		Generator.generate((IGeneratable)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade("M_EMPLOYEE"));
		FileComparator.assertEquals("results/generatorTest/TestGenerator.testGenerateMapping.expected.install.sql",
				new File("output/install.sql"));
	}
	 
	

	/**
	 * Helper method for creating generatable object. MetafacadeFactory should
	 * be used to create generatable object.
	 * */
	private IGeneratable createGeneratable() {
		IGeneratable g = new IGeneratable() {

			public String getName() {
				return "TestElement";
			}

			@Override
			public IPattern getPattern() {
				return new IPattern() {
					@Override
					public Collection<IPatternStep> getSteps() {
						ArrayList<IPatternStep> steps = new ArrayList<IPatternStep>(
								1);
						steps.add(new IPatternStep() {

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
