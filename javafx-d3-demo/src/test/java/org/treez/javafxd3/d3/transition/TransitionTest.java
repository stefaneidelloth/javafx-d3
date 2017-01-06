package org.treez.javafxd3.d3.transition;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.core.Transition.EventType;
import org.treez.javafxd3.d3.democases.InputElementFactory;
import org.treez.javafxd3.d3.ease.Easing;
import org.treez.javafxd3.d3.functions.ConstantDatumFunction;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.selection.AbstractSelectionTest;
import org.treez.javafxd3.d3.transition.function.InterpolatorTweenFunction;


public class TransitionTest extends AbstractSelectionTest {

	private final DatumFunction<String> callback = new ConstantDatumFunction<String>("16px");

	@Override	
	public void doTest() {
		
				
		// NOTE: all this is only smoke tests

		Selection selection = givenASimpleNodeFactory(new InputElementFactory("blah"));
		//
		Transition transition = selection.transition().duration(100);
		transition.attr("foo", callback);
		transition.attr("bar", 16);
		transition.attr("bar", "32px");
		transition.attrTween("bar", new InterpolatorTweenFunction(webEngine));
		
		
		transition
				.delay(1000)
				.delay(new ConstantDatumFunction<Integer>(100))
				.duration(new ConstantDatumFunction<Integer>(123))
				.each(EventType.START, new ConstantDatumFunction<Void>(null))
				.each(EventType.END, new ConstantDatumFunction<Void>(null))
				.ease(Easing.back(webEngine, 12))
				.style("font-size", new ConstantDatumFunction<String>(""));

		transition.size();

		// d3.transition
		assertNotNull(d3.transition());
		
	}

}
