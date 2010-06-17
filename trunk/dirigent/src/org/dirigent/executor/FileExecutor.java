package org.dirigent.executor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.dirigent.metafacade.IGeneratable;
import org.dirigent.pattern.IPatternStep;

public class FileExecutor implements IStepExecutor {

	@Override
	public void execute(IGeneratable gen, IPatternStep step) {
		try {
			String fileName=step.getParameter("fileName");
			if (fileName==null) {
				throw new RuntimeException("Step parameter fileName must be specified.");
			}
			fileName=TemplateHelper.generateValue(fileName, gen);
			Writer w = new FileWriter(fileName, true);
			w.append(TemplateHelper.generateTemplate(gen, step));
			w.close();
		} catch (IOException e) {
			throw new RuntimeException("Unable to generate pattern step "
					+ step.getName(), e);
		}
	}

}
