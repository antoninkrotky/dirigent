package org.dirigent.metafacade.builder.decorator;

import java.util.Collection;
import java.util.Vector;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IPackage;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ElementVO;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public class PackageDecorator extends ElementDecorator implements IPackage {

	private ElementVO element;
	
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
		return PatternBuilder.getPatternBuilder().getPattern(
				DirigentConfig.getDirigentConfig().getProperty(
						DirigentConfig.DEFAULT_PATTERN_PACKAGE) + ".pattern.xml");
	}
	

}
