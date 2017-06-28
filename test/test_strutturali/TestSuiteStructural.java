package test;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.junit.Test;

/**
 * Structural test suite
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({TestCircle.class, TestRectangle.class, TestLine.class,
					TestInstaller.class, TestParser.class, TestDrawingProject.class,
					TestRendering.class})
public class TestSuiteStructural {
}
