package org.dirigent.metafacade.builder.ea.decorator;

import java.util.Collection;
import java.util.Collections;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IDimensionColumn;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public class EADimensionDecorator extends EATableDecorator implements IDimension {
	public EADimensionDecorator(EAElementVO ea) {
		super(ea);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Collection<IDimensionColumn> getDimensionColumns() {
		return Collections.checkedCollection((Collection)getAttributes(), IDimensionColumn.class);
		
		
	}

	@Override
	public int getSCDType() {
		return Integer.valueOf(getProperties().get("slowlyChangingDimensionType"));
	}
	
	@Override
	public IPattern getPattern() {		
		String pattern=DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.DEFAULT_PATTERN_DIMENSION);
		if (pattern==null) {
			throw new RuntimeException("Default pattern for dimension not configured. Set "+DirigentConfig.DEFAULT_PATTERN_DIMENSION+" property in configuration file.");
		}
		return PatternBuilder.getPatternBuilder().getPattern(
				pattern + ".pattern.xml");
	}

}
