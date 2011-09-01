package org.dirigent.executor;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.pattern.IPatternStep;

public class TemplateHelper {

	private static Logger l = Logger.getLogger(TemplateHelper.class.getName());

	static {
		initVelocity();
	}

	/**
	 * Init Velocity template enegine.
	 * */
	private static void initVelocity() {

		Properties p = new Properties();
		p.setProperty("input.encoding", "UTF-8");
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class",
				"org.dirigent.executor.DirigentResourceLoader");
		try {
			Velocity.init(p);
			l.info("Velocity template engine initialized.");
		} catch (Exception e) {
			throw new RuntimeException("Velocity initialisation failed.", e);
		}
	}

	public static String generateTemplate(IGeneratable gen, IPatternStep step) {
		return generateValue(step.getTemplate(), gen) + '\n';
	}

	private static VelocityContext getVelocityContext(IGeneratable gen) {
		String contextFactoryName = System
				.getProperty("dirigent.context.factory");
		if (contextFactoryName != null) {
			try {
				return ((AbstractContextFactory) Class.forName(
						contextFactoryName).newInstance())
						.createVelocityContext(gen);
			} catch (InstantiationException e) {
				throw new RuntimeException(
						"InstantiationException when creating instance of "
								+ contextFactoryName + ". "
								+ contextFactoryName
								+ " is either abstract or interface. ");
			} catch (IllegalAccessException e) {
				throw new RuntimeException(
						"IllegalAccessException when creating instance of "
								+ contextFactoryName, e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Class " + contextFactoryName
						+ " not found.");
			}
		} else {
			return AbstractContextFactory.getVelocityContext(gen);
		}
	}

	public static String generateValue(String template, IGeneratable gen) {
		try {
			Writer w = new StringWriter();
			Velocity.evaluate(getVelocityContext(gen), w, gen.getName() + ":"
					+ template, template);
			w.close();
			return w.toString();
		} catch (Exception e) {
			throw new RuntimeException("Unable to generate template "
					+ template, e);
		}
	}

	public static boolean evaluateCondition(IGeneratable gen, IPatternStep step) {
		StringWriter sw = new StringWriter();
		try {
			Velocity.evaluate(getVelocityContext(gen), sw, "Dirigent", "#if ("
					+ step.getCondition() + ")true#{else}false#end");
		} catch (IOException e) {
			throw new RuntimeException("Unable to evaluate condition ["
					+ step.getCondition() + "] fro step " + step.getName()
					+ ".");
		}
		return "true".equals(sw.toString());

	}
}
