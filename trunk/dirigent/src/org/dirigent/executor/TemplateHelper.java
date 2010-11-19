package org.dirigent.executor;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dirigent.config.DirigentConfig;
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
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class", "org.dirigent.executor.DirigentResourceLoader");
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

	public static String generateValue(String template, IGeneratable gen) {
		VelocityContext vCtx = new VelocityContext();
		vCtx.put("element", gen);
		vCtx.put("config", DirigentConfig.getDirigentConfig());
		vCtx.put("utils", TemplateUtils.class);
		try {
			Writer w = new StringWriter();
			Velocity
					.evaluate(vCtx, w, gen.getName() + ":" + template, template);
			w.close();
			return w.toString();
		} catch (Exception e) {
			throw new RuntimeException("Unable to generate template "
					+ template, e);
		}
	}

	public static boolean evaluateCondition(IGeneratable gen, IPatternStep step) {
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
