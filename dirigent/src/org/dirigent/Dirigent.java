package org.dirigent;

import org.dirigent.generator.Generator;

/**
 * Executable class for launching Dirigent generator.
 * */
public class Dirigent {

	/**
	 * Main method.
	 * @param args
	 *            Field of parameters. Expected one parameter containing URI of
	 *            model element to generate.
	 * */
	public static void main(String args[]) {
		Generator.generate(args[0]);
	}
}
