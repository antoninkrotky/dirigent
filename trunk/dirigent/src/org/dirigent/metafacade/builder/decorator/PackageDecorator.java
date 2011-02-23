package org.dirigent.metafacade.builder.decorator;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IPackage;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ElementVO;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public class PackageDecorator extends ElementDecorator implements IPackage {

	private ElementVO element;
	
	private static Logger l=Logger.getLogger(PackageDecorator.class.getName());
	public PackageDecorator(ElementVO v) {
		super(v);
		this.element=v;
	}

	@Override
	public Vector<IElement> getChildElements() {
		return MetafacadeBuilder.getMetafacadeBuilder().getChildElements(element.uri);
	}

	@Override
	public IPattern getPattern() {
		String pattern=null;
 		if ("true".equals(DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.PATTERN_OVERRIDE,"true"))) {
 			pattern=element.getProperties().get("pattern");
 		}
 		if (pattern==null) {
 			String confPattern=DirigentConfig.DEFAULT_PATTERN_PACKAGE;
 			if (getStereotype()!=null) {
 				confPattern=confPattern+ "." + getStereotype().toLowerCase();
 				pattern=DirigentConfig.getDirigentConfig().getProperty(confPattern);
 				if (pattern==null) {
 					pattern=DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.DEFAULT_PATTERN_PACKAGE); 				}
 			} else {
 				pattern=DirigentConfig.getDirigentConfig().getProperty(confPattern);
 			}
 			if (pattern==null) {
 				PackageDecorator.l.log(Level.WARNING, "Element " + getName() + " skipped. Pattern definition missing in configuration file for pattern " + confPattern);
 				return null;	
 			}
  		}		
		return PatternBuilder.getPatternBuilder().getPattern(
					pattern + ".pattern.xml");
	}
	

}
