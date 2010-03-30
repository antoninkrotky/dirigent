package org.dirigent;

import org.dirigent.config.DirigentConfig;
import org.dirigent.generator.Generator;

public class Dirigent {

	public static void main(String args[]) {
		System.setProperty(DirigentConfig.MODEL_PATH, args[0]);
		Generator.generate(args[1]);		
	}
}
