package Runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = {"Features"},
   glue ={"Step"},
   tags ={"@Login"} ,
   dryRun = false,
   monochrome = true
)
public class Runner {
}
