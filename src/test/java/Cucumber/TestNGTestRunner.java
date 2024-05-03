package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",tags= "@Regression",glue="Framework_Tutorial.stepDefinations",monochrome=true,plugin= {"html:target/Cucmber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
