package org.treez.javafxd3.d3.transition;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.core.Transition.EventType;
import org.treez.javafxd3.d3.democases.svg.text.LabelFactory;
import org.treez.javafxd3.d3.ease.Easing;
import org.treez.javafxd3.d3.functions.ConstantDatumFunction;
import org.treez.javafxd3.d3.functions.DatumFunction;

import org.treez.javafxd3.d3.selection.AbstractSelectionTest;
import org.treez.javafxd3.d3.transition.function.InterpolatorTweenFunction;

@SuppressWarnings("javadoc")
public class TransitionTest extends AbstractSelectionTest {

	private final DatumFunction<String> callback = new ConstantDatumFunction<String>("16px");

	@Override	
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		// NOTE: all this is only smoke tests

		Selection selection = givenASimpleSelection(new LabelFactory("blah"));
		//
		Transition transition = selection.transition().duration(100);
		transition.attr("foo", callback);
		transition.attr("bar", 16);
		transition.attr("bar", "32px");
		transition.attrTween("bar", new InterpolatorTweenFunction());
		
		
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
		// FIXME: with argument

	}

}
