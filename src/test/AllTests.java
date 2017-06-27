package test;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.junit.Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestSuiteFunctional.class, TestSuiteStructural.class})

public class AllTests {
}
