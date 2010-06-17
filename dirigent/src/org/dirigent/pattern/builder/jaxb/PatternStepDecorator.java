package org.dirigent.pattern.builder.jaxb;

import java.util.Iterator;

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
		return step.getType().value();
	}

	@Override
	public String getCondition() {
		return step.getCondition();
	}

	@Override
	public boolean isIgnoreErrors() {
		return step.isIgnoreErrors();
	}

	@Override
	public String getParameter(String name) {
		Iterator<StepParameter> i=step.getParameter().iterator();
		while (i.hasNext()) {
			StepParameter sp=i.next();
			if (name.equals(sp.getName())) {
				return sp.getValue();
			}
		}
		return null;
	}

}
