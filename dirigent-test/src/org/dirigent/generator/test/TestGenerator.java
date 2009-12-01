package org.dirigent.generator.test;

import java.util.ArrayList;
import java.util.Collection;

import org.dirigent.generator.Generator;
import org.dirigent.metafacade.Generatable;
import org.dirigent.metafacade.Pattern;
import org.dirigent.metafacade.PatternStep;
import org.dirigent.test.utils.FileComparator;

import junit.framework.TestCase;

public class TestGenerator extends TestCase {
	public void testGenerate() {
		Generator.generate(createGeneratable());
		FileComparator.assertEquals(TestGenerator.class.getResourceAsStream("TestGenerator.expected.install.sql"), "output/install.sql");
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
		};
		return g;
	}
}
