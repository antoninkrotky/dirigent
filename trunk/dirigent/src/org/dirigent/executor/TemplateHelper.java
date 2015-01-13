package org.dirigent.executor;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.IMetafacadeBase;
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

	private static VelocityContext getVelocityContext(IMetafacadeBase gen) {
		return AbstractContextFactory.getContextFactory().createVelocityContext(gen);
	}

	/**
	 * Evaluates velocity template using given element context.
	 * To enable metamodel to contain sub-templates, the template is evaluated in 2 round.
	 * Only one level of sub-templating is supported. 
	 * The main goal is to enable evaluation of functions and constants stored in shared libraries.
	 * */
	public static String generateValue(String template, IMetafacadeBase gen) {
		return generateValue(template, gen, 2);
	}
	
	public static String generateValue(String template, IMetafacadeBase gen,int rounds) {
		VelocityContext velocityContext = getVelocityContext(gen);
		for (int round=1;round<=rounds;round++) {
			template=generateValueInternal(template, velocityContext, round);
		}		
		return template;
	}

	private static String generateValueInternal(String template, VelocityContext velocityContext,int round) {
		try {
			Writer w = new StringWriter();		
						
			Velocity.evaluate(velocityContext, w, round+":"+template, template);
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
