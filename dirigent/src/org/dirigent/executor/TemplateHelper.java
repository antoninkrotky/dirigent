package org.dirigent.executor;

import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.pattern.IPatternStep;

public class TemplateHelper {
	public static String generateTemplate(IGeneratable gen, IPatternStep step) {
		VelocityContext vCtx = new VelocityContext();
		vCtx.put("element", gen);
		try {
			Writer w = new StringWriter();
			Velocity.evaluate(vCtx, w, step.getName(), step.getTemplate());
			w.append('\n');
			w.close();
			return w.toString();
		} catch (Exception e) {
			throw new RuntimeException("Unable to generate pattern step "
					+ step.getName(), e);
		}
	}
}
