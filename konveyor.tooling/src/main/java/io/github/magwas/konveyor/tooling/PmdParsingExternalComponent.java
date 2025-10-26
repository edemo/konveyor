package io.github.magwas.konveyor.tooling;

import org.springframework.stereotype.Component;

import net.sourceforge.pmd.lang.*;
import net.sourceforge.pmd.lang.ast.Parser;
import net.sourceforge.pmd.lang.java.JavaLanguageModule;

@Component
public class PmdParsingExternalComponent {
	{
		Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader());
	}

	LanguageRegistry registry = LanguageRegistry.PMD;
	LanguageProcessor processor;
	PmdCapableLanguage java;

	{
		Language javaLang = null;
		// Try to manually register Java language
		try {
			javaLang = new JavaLanguageModule();
			// Some PMD versions might need manual registration
		} catch (Exception e) {
			System.err.println("Failed to create JavaLanguageModule: " + e.getMessage());
		}
		java = (PmdCapableLanguage) registry.getLanguageById("java");
		if (java == null) {
			System.err.println("Java language not found. Available languages:");
			for (Language lang : LanguageRegistry.PMD.getLanguages()) {
				System.err.println("Language: " + lang.getId() + " - " + lang.getName());
			}
			throw new IllegalStateException("Java language not found in PMD LanguageRegistry.");
		}
		processor = java.createProcessor(java.newPropertyBundle());
	}

	Parser parser = processor.services().getParser();
	LanguageProcessorRegistry lpr = LanguageProcessorRegistry.singleton(processor);
}
