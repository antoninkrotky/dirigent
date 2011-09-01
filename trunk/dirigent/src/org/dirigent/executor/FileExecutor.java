package org.dirigent.executor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Logger;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.pattern.IPatternStep;

/**
 * Generates file based on step template.<BR>
 * Paramaters:<BR>
 * fileName - relative path to generated file.<BR>
 * mode - generation mode. (append - appends content to file, overwrite
 * - overwrite file if exists, create - generate file if it does not exist,
 * otherwise do nothing.) Default mode is append.<BR>
 * */
public class FileExecutor implements IStepExecutor {

	public static final String MODE_APPEND = "append";
	public static final String MODE_OVERWRITE = "overwrite";
	public static final String MODE_CREATE = "create";

	private Logger l=Logger.getLogger(FileExecutor.class.getName());
	@Override
	public void execute(IGeneratable gen, IPatternStep step) {
		try {
			String fileName = step.getParameter("fileName");
			fileName = TemplateHelper.generateValue(fileName, gen);
			String mode = step.getParameter("mode",MODE_APPEND);
			if (fileName == null) {
				throw new RuntimeException(
						"Step parameter fileName must be specified.");
			}
			File file=new File(fileName);
			// Create parent directory
			String dir = file.getParent();
			if (dir != null) {
				new File(dir).mkdirs();
			}
					
			if (MODE_OVERWRITE.equals(mode)) {
					file.delete();
			}
			if (MODE_CREATE.equals(mode)) {
				if (file.exists()) {
					return;
				}
			}
			Writer w = new OutputStreamWriter(new FileOutputStream(fileName,true), DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.DIRIGENT_FILEEXECUTOR_ENCODING,"UTF-8"));
			w.append(TemplateHelper.generateTemplate(gen, step));
			w.close();
			l.info("Step "+step.getName()+" generated file "+file.getAbsolutePath()+".");

		} catch (IOException e) {
			throw new RuntimeException("Unable to generate pattern step "
					+ step.getName(), e);
		}
	}

}
