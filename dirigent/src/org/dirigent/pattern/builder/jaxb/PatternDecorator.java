package org.dirigent.pattern.builder.jaxb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.IPatternStep;

public class PatternDecorator implements IPattern{

	
	private org.dirigent.pattern.builder.jaxb.Pattern pattern;
	
	private Collection<IPatternStep> steps;

	


	public PatternDecorator(org.dirigent.pattern.builder.jaxb.Pattern pattern) {
		this.pattern=pattern;
	}
	
	@Override
	public String getName() {
		return pattern.name;
	}

	@Override
	public Collection<IPatternStep> getSteps() {
		if (steps==null) {
			List<PatternStep> ps= this.pattern.getPatternStep();
			steps=new ArrayList<IPatternStep>(ps.size());
			Iterator<PatternStep> i=ps.iterator();
			while (i.hasNext()) {
				steps.add(new PatternStepDecorator(i.next()));
			}			
		}
		return steps;
	}

}
