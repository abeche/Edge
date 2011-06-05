/**
 * 
 */
package org.abeche.android.edge.generator;

import static org.abeche.android.edge.generator.EventGeneratorOptions.DEFAULT_OPTIONS_GENERATE_CLASS;
import static org.abeche.android.edge.generator.EventGeneratorOptions.DEFAULT_OPTIONS_OUTPUT_PACKAGE;
import static org.abeche.android.edge.generator.EventGeneratorOptions.KEY_OPTIONS_GENERATE_CLASS;
import static org.abeche.android.edge.generator.EventGeneratorOptions.KEY_OPTIONS_OUTPUT_PACKAGE;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

/**
 * @author abeche
 *
 */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("org.abeche.android.edge.event.EventListener")
@SupportedOptions({KEY_OPTIONS_OUTPUT_PACKAGE, KEY_OPTIONS_GENERATE_CLASS})
public class EventListenerProcessor extends AbstractProcessor {
	
	private String outputPackage = DEFAULT_OPTIONS_OUTPUT_PACKAGE;
	private String generateClass = DEFAULT_OPTIONS_GENERATE_CLASS;
	
	private Formatter formatter;
	private String generateDate;
	
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		
		Map<String, String> options = this.processingEnv.getOptions();
		outputPackage = options.get(KEY_OPTIONS_OUTPUT_PACKAGE);
		if (outputPackage == null) {
			outputPackage = DEFAULT_OPTIONS_OUTPUT_PACKAGE;
		}
		generateClass = options.get(KEY_OPTIONS_GENERATE_CLASS);
		if (generateClass == null) {
			generateClass = DEFAULT_OPTIONS_GENERATE_CLASS;
		}
		
		Filer filer = processingEnv.getFiler();
		try {
			JavaFileObject javaFile
				= filer.createSourceFile(outputPackage + "." + generateClass);
			formatter = new Formatter(new BufferedWriter(javaFile.openWriter()));
		} catch (IOException e) {
			Messager messager = processingEnv.getMessager();
			messager.printMessage(Kind.ERROR, e.toString());
		}
		
	}
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		try {
			if (roundEnv.processingOver()) {
				return true;
			}
			
			for (TypeElement annotation : annotations) {
					generate(ElementFilter.typesIn(
						roundEnv.getElementsAnnotatedWith(annotation)));
			}
			return false;
		} finally {
			close();
		}
	}

	private void generate(Set<TypeElement> targetElements) {
		EventTableGenerator generator =
			new EventTableGenerator(targetElements, processingEnv, formatter
				, outputPackage, generateClass);
		generator.generate();
		generateDate = generator.getGenerateDate();
	}
	
	public String getGenerateDate() {
		return generateDate;
	}

	private void close() {
		if (formatter != null) {
			formatter.close();
		}
	}
}
