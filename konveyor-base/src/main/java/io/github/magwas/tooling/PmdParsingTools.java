package io.github.magwas.tooling;

import org.springframework.stereotype.Component;

import io.github.magwas.kodekonveyorannotations.Glue;
import net.sourceforge.pmd.lang.LanguageProcessor;
import net.sourceforge.pmd.lang.LanguageProcessorRegistry;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.PmdCapableLanguage;
import net.sourceforge.pmd.lang.ast.Parser;

@Glue
@Component
public class PmdParsingTools {
	PmdCapableLanguage java = (PmdCapableLanguage) LanguageRegistry.PMD
			.getLanguageById("java");
	LanguageProcessor processor = java.createProcessor(java.newPropertyBundle());
	Parser parser = processor.services().getParser();
	LanguageProcessorRegistry lpr = LanguageProcessorRegistry
			.singleton(processor);

}