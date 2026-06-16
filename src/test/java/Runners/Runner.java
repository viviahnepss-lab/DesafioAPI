package Runners;

import Test.LoginTest;
import Test.UsuarioTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(TesteNegativos.class)
@Suite.SuiteClasses({
        LoginTest.class,
        UsuarioTest.class})

public class Runner {
}
