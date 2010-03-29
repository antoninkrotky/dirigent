package org.dirigent.generator;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Iterator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.pattern.IPatternStep;

public class Generator {
	public static void generate(IGeneratable gen) {
		Iterator<IPatternStep> i=gen.getPattern().getSteps().iterator();
		while (i.hasNext()) {
			IPatternStep step=i.next();
			generate(gen,step);
		}
	}

	private static void generate(IGeneratable gen, IPatternStep step) {
		VelocityContext vCtx=new VelocityContext();
		vCtx.put("element", gen);
		try {
		Writer w=new FileWriter("output/install.sql",true);
		
		Velocity.evaluate(vCtx, w, step.getName(), step.getTemplate());
		w.append('\n');
		w.close();
		} catch (Exception e) {
			throw new RuntimeException("Unable to generate pattern step "+step.getName(),e);
		}
	}
}
