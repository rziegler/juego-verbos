package ch.zir.juegoverbos.app.store.csv;

import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import ch.zir.juegoverbos.api.GrammaticalConjugation;
import ch.zir.juegoverbos.api.GrammaticalPerson;
import ch.zir.juegoverbos.api.GrammaticalTense;
import ch.zir.juegoverbos.api.Language;
import ch.zir.juegoverbos.api.Verb;

public class CsvLoader {

	public List<Verb> load() {
		final List<Verb> result = new ArrayList<>();

		try {
			final URL resource = getClass().getClassLoader().getResource("jehle_verb_database.csv");
			final List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));

			final Stream<CsvLine> csvLines = lines.stream().skip(1).map(new Function<String, CsvLine>() {

				@Override
				public CsvLine apply(final String element) {
					return parse(element);
				}
			});

			final Map<String, List<CsvLine>> groupedLines = csvLines.collect(groupingBy(CsvLine::getInfinitive));

			groupedLines.values().stream().forEach(i -> {
				final Verb resultVerb = i.stream().reduce(new Verb(), (verb, line) -> {
					verb.setInfinitive(line.getInfinitive());
					verb.addTranslation(Language.EN, line.getInfinitiveEnglish());
					final GrammaticalTense tense = GrammaticalTense.from(line.getTense());

					verb.addConjugation(new GrammaticalConjugation(tense, GrammaticalPerson.FIRST_SINGULAR, line.getForm1s()));
					verb.addConjugation(new GrammaticalConjugation(tense, GrammaticalPerson.SECOND_SINGULAR, line.getForm2s()));
					verb.addConjugation(new GrammaticalConjugation(tense, GrammaticalPerson.THIRD_SINGULAR, line.getForm3s()));
					verb.addConjugation(new GrammaticalConjugation(tense, GrammaticalPerson.FIRST_PLURAL, line.getForm1p()));
					verb.addConjugation(new GrammaticalConjugation(tense, GrammaticalPerson.SECOND_PLURAL, line.getForm2p()));
					verb.addConjugation(new GrammaticalConjugation(tense, GrammaticalPerson.THIRD_PLURAL, line.getForm3p()));

					return verb;
				}, (v1, v2) -> {
					v1.setInfinitive(v1.getInfinitive() + v2.getInfinitive());
					return v1;
				});
				result.add(resultVerb);
			});

		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(String.format("Loaded %d verbs.", result.size()));
		return result;
	}

	private CsvLine parse(final String element) {

		final String[] parts = element.split("\"{1}(,\")?");
		// System.out.println(Joiner.on("::").join(parts));
		final CsvLine result = new CsvLine();
		result.setInfinitive(parts[1]);
		result.setInfinitiveEnglish(parts[2]);
		result.setTense(parts[6]);
		result.setForm1s(parts[8]);
		result.setForm2s(parts[9]);
		result.setForm3s(parts[10]);
		result.setForm1p(parts[11]);
		result.setForm2p(parts[12]);
		result.setForm3p(parts[13]);
		result.setGerund(parts[14]);
		result.setPastparticiple(parts[16]);
		return result;
	}
}
