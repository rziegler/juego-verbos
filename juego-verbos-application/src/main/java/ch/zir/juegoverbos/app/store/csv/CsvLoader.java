package ch.zir.juegoverbos.app.store.csv;

import static java.util.stream.Collectors.groupingBy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.zir.juegoverbos.api.GrammaticalConjugation;
import ch.zir.juegoverbos.api.GrammaticalPerson;
import ch.zir.juegoverbos.api.GrammaticalTense;
import ch.zir.juegoverbos.api.Language;
import ch.zir.juegoverbos.api.Verb;

public class CsvLoader {

	Logger log = LoggerFactory.getLogger(CsvLoader.class);

	public List<Verb> load() {
		final List<Verb> result = new ArrayList<>();

		try {
			// final URL resource = getClass().getClassLoader().getResource("jehle_verb_database.csv");
			final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jehle_verb_database.csv");

			final BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String tmp = null;

			final List<String> lines = new ArrayList<>();
			while ((tmp = in.readLine()) != null) {
				lines.add(tmp);
			}

			// log.info("Loading resource " + resource.toString());
			// final Path path = Paths.get(resource.toURI());
			// final List<String> lines = Files.readAllLines(path);

			final Map<String, List<CsvLine>> groupedCsvLines = lines.stream().skip(1).map(new Function<String, CsvLine>() {
				@Override
				public CsvLine apply(final String element) {
					return parse(element);
				}
			}).filter(csvline -> csvline.getMood().equals("Indicative")).collect(groupingBy(CsvLine::getInfinitive));

			groupedCsvLines.values().stream().forEach(i -> {

				final Verb resultVerb = i.stream().reduce(new Verb(), (verb, line) -> {
					verb.setInfinitive(line.getInfinitive());
					verb.setGerund(line.getGerund());
					verb.setPastparticiple(line.getPastparticiple());
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

		} catch (final IOException e) {
			e.printStackTrace();
		}
		log.info(String.format("Loaded %d verbs.", result.size()));
		return result;
	}

	private CsvLine parse(final String element) {
		final String[] parts = element.split("\"{1}(,\")?");
		final CsvLine result = new CsvLine();
		result.setInfinitive(parts[1]);
		result.setInfinitiveEnglish(parts[2]);
		result.setMood(parts[4]);
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
