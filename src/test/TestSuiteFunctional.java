package test;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.junit.Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestNewProject.class, TestLoadProject.class, TestSaveProject.class, TestVariousProjects.class})
public class TestSuiteFunctional {

}
