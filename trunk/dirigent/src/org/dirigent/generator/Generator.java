package org.dirigent.generator;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dirigent.executor.ExecutorFactory;
import org.dirigent.executor.IStepExecutor;
import org.dirigent.metafacade.IComposite;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.pattern.IPatternStep;

public class Generator {
	private static Logger l = Logger.getLogger(Generator.class.getName());

	public static void generate(String elementURI) {
		l.info("Getting metafacade for URI="+elementURI);
		IGeneratable gen = (IGeneratable) MetafacadeBuilder
				.getMetafacadeBuilder().getMetafacade(elementURI);
		if (gen==null) {
			throw new RuntimeException("Element URI="+elementURI+" not found.");
		}
		generate(gen);
		


	}

	public static void generate(IGeneratable gen) {
		l.info("Starting generation of element " + gen.getName()+'.');
		Iterator<IPatternStep> i = gen.getPattern().getSteps().iterator();
		while (i.hasNext()) {
			IPatternStep step = i.next();
			if (step.getCondition()==null || evaluateCondition(gen, step)) {
				IStepExecutor executor = ExecutorFactory.getStepExecutor(step
						.getType());
				try {
					executor.execute(gen, step);
				} catch (Throwable t) {
					if (step.isIgnoreErrors()) {
						l.log(Level.WARNING, "Step " + step.getName()
								+ " failed.", t);
					} else {
						throw new RuntimeException("Step " + step.getName()
								+ " - generation failed.", t);
					}
				}
			}
		}

		if (gen instanceof IComposite) {
			Iterator<IElement> y=((IComposite)gen).getChildElements().iterator();
			while (y.hasNext()) {
				IElement e=y.next();
				if (e instanceof IGeneratable) {
					generate((IGeneratable)e);
				}
			}
		}


		
		l.info("Element " + gen.getName() + " sucesfully generated.");
	}

	private static boolean evaluateCondition(IGeneratable gen, IPatternStep step) {
		VelocityContext ctx = new VelocityContext();
		ctx.put("element", gen);
		StringWriter sw = new StringWriter();
		try {
			Velocity.evaluate(ctx, sw, "Dirigent", "#if ("
					+ step.getCondition() + ")true#{else}false#end");
		} catch (IOException e) {
			throw new RuntimeException("Unable to evaluate condition ["
					+ step.getCondition() + "] fro step " + step.getName()
					+ ".");
		}
		return "true".equals(sw.toString());

	}
}
