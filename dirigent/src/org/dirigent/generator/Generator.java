package org.dirigent.generator;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dirigent.metafacade.Generatable;
import org.dirigent.metafacade.PatternStep;

public class Generator {
	public static void generate(Generatable gen) {
		Iterator<PatternStep> i=gen.getPattern().getSteps().iterator();
		while (i.hasNext()) {
			PatternStep step=i.next();
			generate(gen,step);
		}
	}

	private static void generate(Generatable gen, PatternStep step) {
		VelocityContext vCtx=new VelocityContext();
		vCtx.put("element", gen);
		try {
		Writer w=new PrintWriter("output/install.sql");
		Velocity.evaluate(vCtx, w, step.getName(), step.getTemplate());
		w.close();
		} catch (Exception e) {
			throw new RuntimeException("Unable to generate pattern step "+step.getName(),e);
		}
		
		
	}
	

}
