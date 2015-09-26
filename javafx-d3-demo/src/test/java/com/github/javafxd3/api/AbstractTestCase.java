package com.github.javafxd3.api;

import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.demo.client.JavaFxD3Browser;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.web.WebEngine;

/**
 * Abstract parent class for all test cases
 * 
 */
public abstract class AbstractTestCase {

	// #region ATTRIBUTES

	protected static JavaFxD3Browser browser = null;

	protected WebEngine webEngine;
	
	protected D3 d3;

	protected boolean isInitialized = false;

	private boolean jfxIsSetup;

	// #end region

	// #region CONSTRUCTORS

	/**
	 * Constructor
	 */
	public AbstractTestCase() {
		if (browser == null) {
			initializeJavaFxD3Browser();
		}
	}	

	// #end region

	// #region METHODS
	
	private void initializeJavaFxD3Browser() {
		Runnable postLoadingFinishedHook = () -> {
			d3 = browser.getD3();
			webEngine = d3.getWebEngine();
			isInitialized = true;
		};

		Runnable createBrowserRunnable = () -> {
			browser = new JavaFxD3Browser(postLoadingFinishedHook);
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
	 * Clears the content of the root element and returns
	 * the root as Selection
	 * @return 
	 */
	public Selection clearRoot(){			
		Selection root = getRoot();
		root.html("");
		return root;
	}

	/**
	 * @return
	 */
	public Selection getRoot() {
		Selection root = d3.select("#root");
		return root;
	}

	// #end region
}
