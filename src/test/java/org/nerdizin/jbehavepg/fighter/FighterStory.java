package org.nerdizin.jbehavepg.fighter;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;

import java.text.SimpleDateFormat;
import java.util.Properties;

import static org.jbehave.core.reporters.Format.*;

public abstract class FighterStory extends JUnitStory {

	private final CrossReference crossRef = new CrossReference();

	public FighterStory() {
		configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(true)
				.doIgnoreFailureInView(true)
				.useThreads(2)
				.useStoryTimeouts("60");
		// Uncomment to set meta filter, which can also be set via Ant or Maven
		// configuredEmbedder().useMetaFilters(Arrays.asList("+theme parametrisation"));
	}

	@Override
	public Configuration configuration() {
		Class<? extends Embeddable> embeddableClass = this.getClass();
		Properties viewResources = new Properties();
		viewResources.put("decorateNonHtml", "true");
		// Start from default ParameterConverters instance
		ParameterConverters parameterConverters = new ParameterConverters();
		// factory to allow parameter conversion and loading from external
		// resources (used by StoryParser too)
		ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(),
				new LoadFromClasspath(embeddableClass), parameterConverters);
		// add custom converters
		parameterConverters.addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
				new ParameterConverters.ExamplesTableConverter(examplesTableFactory));

		return new MostUsefulConfiguration()
				.useStoryControls(new StoryControls()
						.doDryRun(false)
						.doSkipScenariosAfterFailure(false))
				.useStoryLoader(new LoadFromClasspath(embeddableClass))
				.useStoryParser(new RegexStoryParser(examplesTableFactory))
				.useStoryPathResolver(new UnderscoredCamelCaseResolver())
				.useStoryReporterBuilder(
						new StoryReporterBuilder()
								.withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
								.withDefaultFormats()
								.withPathResolver(new FilePrintStreamFactory.ResolveToPackagedName())
								.withViewResources(viewResources)
								.withFormats(CONSOLE, TXT, HTML, XML)
								.withFailureTrace(true)
								.withFailureTraceCompression(true)
								.withCrossReference(crossRef))
				.useParameterConverters(parameterConverters)
				.useStepPatternParser(new RegexPrefixCapturingPatternParser("$"));
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new FighterSteps());
		/*
		, new AndSteps(), new MetaParametrisationSteps(),
				new CalendarSteps(), new PriorityMatchingSteps(), new PendingSteps(), new ParametrisedSteps(), new SandpitSteps(),
				new SearchSteps(), new BeforeAfterSteps(), new CompositeSteps(), new CompositeNestedSteps(), new NamedParametersSteps()
		 */
	}
}
