package org.treez.javafxd3.d3;

import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import org.junit.Assert;
import org.junit.Test;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.time.JsDate;
import org.treez.javafxd3.javafx.JavaFxD3Browser;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.web.WebEngine;


/**
 * Abstract parent class for all test cases 
 */
public abstract class AbstractTestCase extends Assert {

	//#region ATTRIBUTES

	protected static JavaFxD3Browser browser = null;
	
	protected static double TOLERANCE = 1e-6; 

	protected WebEngine webEngine;
	
	protected D3 d3;

	protected boolean isInitialized = false;

	private boolean jfxIsSetup;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 */
	public AbstractTestCase() {
		if (browser == null) {
			initializeJavaFxD3Browser();
		}
	}	

	//#end region

	//#region METHODS
	
	@Test
	public void doTestOnJavaFxApplicationThread(){
		Runnable testRunnable = ()->doTest();
		doOnJavaFXThread(testRunnable);
	}
	
	private void initializeJavaFxD3Browser() {
		Runnable postLoadingFinishedHook = () -> {
			d3 = browser.getD3();
			webEngine = d3.getWebEngine();
			isInitialized = true;
		};

		Runnable createBrowserRunnable = () -> {
			browser = new JavaFxD3Browser(postLoadingFinishedHook, true);
		};

		// create browser
		doOnJavaFXThread(createBrowserRunnable);

		// wait for initialization of browser
		while (!isInitialized) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	protected void setupJavaFX() throws RuntimeException {
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(() -> {
			new JFXPanel(); // initializes JavaFX environment
			latch.countDown();
		});

		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	protected void doOnJavaFXThread(Runnable pRun) throws RuntimeException {
		if (!jfxIsSetup) {
			setupJavaFX();
			jfxIsSetup = true;
		}
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Platform.runLater(() -> {
			pRun.run();
			countDownLatch.countDown();
		});

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * runs the test
	 * 
	 */
	public abstract void doTest();
	
	
	/**
	 * Clears the content of the svg element and returns
	 * the svg as Selection
	 * @return 
	 */
	public Selection clearSvg(){			
		Selection svg = getSvg();
		svg.selectAll("*").remove();
		return svg;
	}

	/**
	 * @return
	 */
	public Selection getSvg() {
		Selection svg = d3.select("#svg");
		return svg;
	}
	
	public void assertDateEquals(double expected, double actual) {
		assertDateEquals(null, expected, actual);
	}

	public void assertDateEquals(String message, double expected, double actual) {
		double delta = .01;
		if (Double.compare(expected, actual) == 0)
			return;
		if (!(Math.abs(expected-actual) <= delta)){
			JsDate expectedDate = JsDate.create(webEngine, expected);
			JsDate actualDate = JsDate.create(webEngine, actual);			
			
			assertEquals(message, expectedDate, actualDate);
		}
	}

	//#end region
}
