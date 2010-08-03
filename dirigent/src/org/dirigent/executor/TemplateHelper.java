package org.dirigent.executor;

import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.pattern.IPatternStep;

public class TemplateHelper {
	public static String generateTemplate(IGeneratable gen, IPatternStep step) {
		return generateValue(step.getTemplate(), gen)+'\n';
	}
	
	public static String generateValue(String template,IGeneratable gen) {
		VelocityContext vCtx = new VelocityContext();
		vCtx.put("element", gen);
		vCtx.put("config", DirigentConfig.getDirigentConfig());
		try {
			Writer w = new StringWriter();
			Velocity.evaluate(vCtx, w, gen.getName()+":"+template, template);
			w.close();
			return w.toString();
		} catch (Exception e) {
			throw new RuntimeException("Unable to generate template "
					+ template, e);
		}
	}
}
