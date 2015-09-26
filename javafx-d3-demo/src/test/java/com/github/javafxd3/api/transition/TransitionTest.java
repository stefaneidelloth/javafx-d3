package com.github.javafxd3.api.transition;

import static org.junit.Assert.assertNotNull;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Transition;
import com.github.javafxd3.api.core.Transition.EventType;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.ease.Easing;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.interpolators.CallableInterpolator;
import com.github.javafxd3.api.interpolators.Interpolator;
import com.github.javafxd3.api.selection.AbstractSelectionTest;
import com.github.javafxd3.api.selection.Label;
import com.github.javafxd3.api.tweens.TweenFunction;
import com.github.javafxd3.api.wrapper.Element;

@SuppressWarnings("javadoc")
public class TransitionTest extends AbstractSelectionTest {

	private final DatumFunction<String> callback = new DatumFunction<String>() {
		@Override
		public String apply(final Element context, final Value d,
				final int index) {
			return "16px";
		}
	};

	@Override
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		// NOTE: all this is only smoke tests

		Selection selection = givenASimpleSelection(new Label("blah"));
		//
		Transition transition = selection.transition().duration(100);
		transition.attr("foo", callback);
		transition.attr("bar", 16);
		transition.attr("bar", "32px");
		transition.attrTween("bar", new TweenFunction<String>() {
			@Override
			public Interpolator<String> apply(final Element context,
					final Value datum, final int index, final Value value) {
				return new CallableInterpolator<String>() {
					@Override
					public String interpolate(final double t) {
						return (int) (t * 10) + "px";
					}
				};
			}
		});
		transition
				.delay(1000)
				.delay(new DatumFunction<Integer>() {
					@Override
					public Integer apply(final Element context, final Value d,
							final int index) {
						return 100;
					}
				})
				.duration(new DatumFunction<Integer>() {
					@Override
					public Integer apply(final Element context, final Value d,
							final int index) {
						return 123;
					}
				})
				.each(EventType.START, new DatumFunction<Void>() {
					@Override
					public Void apply(final Element context, final Value d,
							final int index) {
						return null;
					}
				})
				.each(EventType.END, new DatumFunction<Void>() {
					@Override
					public Void apply(final Element context, final Value d,
							final int index) {
						return null;
					}
				}).ease(Easing.back(webEngine, 12))
				.style("font-size", new DatumFunction<String>() {
					@Override
					public String apply(final Element context, final Value d,
							final int index) {
						return "";
					}
				});

		transition.size();

		// d3.transition
		assertNotNull(d3.transition());
		// FIXME: with argument

	}

}
