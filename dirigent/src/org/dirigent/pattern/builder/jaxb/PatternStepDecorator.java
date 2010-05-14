package org.dirigent.pattern.builder.jaxb;

import org.dirigent.pattern.IPatternStep;

public class PatternStepDecorator implements IPatternStep {

	private PatternStep step;

	public PatternStepDecorator(PatternStep step) {
		this.step = step;
	}

	@Override
	public String getName() {
		return step.getName();
	}

	@Override
	public String getTemplate() {
		return step.getTemplate();
	}
	@Override
	public String getType() {
		return step.getType();
	}

	@Override
	public String getCondition() {
		return step.getCondition();
	}

	@Override
	public boolean isIgnoreErrors() {
		return step.isIgnoreErrors();
	}

}
