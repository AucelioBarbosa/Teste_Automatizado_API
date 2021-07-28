package br.com.south.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.south.TesteAPI.Restricoes;
import br.com.south.TesteAPI.Simulacoes;

@RunWith(Suite.class)
@SuiteClasses({
	Restricoes.class,
	Simulacoes.class
})
public class SuiteTest {

}
