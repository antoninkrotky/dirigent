package org.dirigent.generator;

import java.util.Iterator;
import java.util.logging.Logger;

import org.dirigent.executor.ExecutorFactory;
import org.dirigent.executor.IStepExecutor;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.pattern.IPatternStep;

public class Generator {
	private static Logger l=Logger.getLogger(Generator.class.getName());
	
	public static void generate(String elementURI) {
		IGeneratable gen=(IGeneratable)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(elementURI);
		generate(gen);
	}
	
	private static void generate(IGeneratable gen) {
		l.info("Starting generation of element "+gen.getName());
		Iterator<IPatternStep> i=gen.getPattern().getSteps().iterator();
		while (i.hasNext()) {
			IPatternStep step=i.next();
			IStepExecutor executor=ExecutorFactory.getStepExecutor(step.getType());
			executor.execute(gen, step);
		}
		l.info("Element "+gen.getName()+" sucesfully generated.");
	}
}
