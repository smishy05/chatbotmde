/*
 * generated by Xtext 2.22.0
 */
package org.sctest.scdsl.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.util.Modules2;
import org.sctest.scdsl.SCDslRuntimeModule;
import org.sctest.scdsl.SCDslStandaloneSetup;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class SCDslIdeSetup extends SCDslStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new SCDslRuntimeModule(), new SCDslIdeModule()));
	}
	
}
