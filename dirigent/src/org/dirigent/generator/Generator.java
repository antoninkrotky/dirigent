package org.dirigent.generator;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dirigent.executor.ExecutorFactory;
import org.dirigent.executor.IStepExecutor;
import org.dirigent.executor.PatternExecutionStatistics;
import org.dirigent.executor.StepStatistics;
import org.dirigent.executor.TemplateHelper;
import org.dirigent.metafacade.IComposite;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.IPatternStep;

public class Generator {
	private static Logger l = Logger.getLogger(Generator.class.getName());

	protected static ThreadLocal<Collection<String>> generatedStack = new ThreadLocal<Collection<String>>();

	public static void generate(String elementURI) {
		MetafacadeBuilder.getMetafacadeBuilder().clearCache();
		generatedStack.set(new LinkedList<String>());
		l.info("Getting metafacade for URI=" + elementURI);
		IGeneratable gen = (IGeneratable) MetafacadeBuilder
				.getMetafacadeBuilder().getMetafacade(elementURI);
		if (gen == null) {
			throw new RuntimeException("Element URI=" + elementURI
					+ " not found.");
		}
		generate(gen);
	}

	protected static void generate(IGeneratable gen) {
		// prevent deadlocks
		if (generatedStack.get().contains(gen.getUri())) {
			return;
		}
		generatedStack.get().add(gen.getUri());
		PatternExecutionStatistics.reset();
		l.info("Starting generation of element " + gen.toString());
		IPattern pattern = gen.getPattern();
		/*
		 * pattern may be null if the template file does not exist. Warning is
		 * logged in calling method.
		 */
		if (pattern != null) {
			Iterator<IPatternStep> i = pattern.getSteps().iterator();
			while (i.hasNext()) {
				IPatternStep step = i.next();
				if (step.getCondition() == null
						|| TemplateHelper.evaluateCondition(gen, step)) {
					IStepExecutor executor = ExecutorFactory
							.getStepExecutor(step.getType());
					try {
						createStepStatistics(step);
						long t = System.currentTimeMillis();
						executor.execute(gen, step);
						PatternExecutionStatistics
								.getStepStatistics()
								.lastElement()
								.setExecutionTime(
										System.currentTimeMillis() - t);
						PatternExecutionStatistics.getStepStatistics()
								.lastElement().setSucces(true);
						l.info(PatternExecutionStatistics.getStepStatistics()
								.lastElement().getExecutionSummary());
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
		}
		if (gen instanceof IComposite) {
			Iterator<IElement> y = ((IComposite) gen).getChildElements()
					.iterator();
			while (y.hasNext()) {
				IElement e = y.next();
				if (e instanceof IGeneratable) {
					generate((IGeneratable) e);
				}
			}
		}
		l.info("Element " + gen.getName() + " sucesfully generated.");
	}

	/**
	 * @param step
	 */
	private static void createStepStatistics(IPatternStep step) {
		StepStatistics s = new StepStatistics();
		s.setStepName(step.getName());
		s.setStepType(step.getType());
		PatternExecutionStatistics.getStepStatistics().add(s);
	}

}
