package org.dirigent.generator.test;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.generator.Generator;

public class TestGenerator extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// Set model path
		String path = "resources/builderTestFiles/model_1";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		System.setProperty("dirigent.model.type", "CSV");
	}

	
	public void testGenerateMapping() throws Exception{
		Generator.generate("M_EMPLOYEE");
	}
}
