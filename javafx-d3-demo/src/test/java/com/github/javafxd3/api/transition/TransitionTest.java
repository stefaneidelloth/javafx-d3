package com.github.javafxd3.api.transition;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Transition;
import com.github.javafxd3.api.core.Transition.EventType;
import com.github.javafxd3.api.ease.Easing;
import com.github.javafxd3.api.functions.ConstantDatumFunction;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.selection.AbstractSelectionTest;
import com.github.javafxd3.api.transition.function.InterpolatorTweenFunction;
import com.github.javafxd3.demo.client.democases.svg.LabelFactory;

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
