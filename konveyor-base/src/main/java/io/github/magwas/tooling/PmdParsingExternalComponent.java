package io.github.magwas.tooling;

import org.springframework.stereotype.Component;

import net.sourceforge.pmd.lang.LanguageProcessor;
import net.sourceforge.pmd.lang.LanguageProcessorRegistry;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.PmdCapableLanguage;
import net.sourceforge.pmd.lang.ast.Parser;

@Component
public class PmdParsingExternalComponent {
	PmdCapableLanguage java = (PmdCapableLanguage) LanguageRegistry.PMD.getLanguageById("java");
	LanguageProcessor processor = java.createProcessor(java.newPropertyBundle());
	Parser parser = processor.services().getParser();
	LanguageProcessorRegistry lpr = LanguageProcessorRegistry.singleton(processor);
}
