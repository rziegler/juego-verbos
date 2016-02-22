package ch.zir.juegoverbos.app.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.zir.juegoverbos.api.GrammaticalConjugation;
import ch.zir.juegoverbos.api.Verb;
import ch.zir.juegoverbos.app.store.csv.CsvLoader;

public class VerbStore {

	Logger log = LoggerFactory.getLogger(VerbStore.class);
	List<Verb> verbs;

	public VerbStore() {
		verbs = new ArrayList<>();
	}

	public void load() {
		verbs.addAll(new CsvLoader().load());
	}

	public List<Verb> getVerbs() {
		return verbs;
	}

	public Verb getVerb(final String infinitive) {
		Verb result;
		if ("random".equals(infinitive)) {
			final int randomIndex = new Random().nextInt(verbs.size());
			result = verbs.get(randomIndex);
		} else {
			final Optional<Verb> verb = verbs.stream().filter(v -> v.getInfinitive().equals(infinitive)).findFirst();
			result = verb.get();
		}
		return result;
	}

	public Verb getVerb(final String infinitive, final String tense) {
		final Verb verb = getVerb(infinitive);
		final Set<GrammaticalConjugation> conjugations = verb.getConjugations().stream().filter(c -> c.getTense().name().equalsIgnoreCase(tense))
				.collect(Collectors.toSet());

		final Verb result = new Verb();
		result.setInfinitive(verb.getInfinitive());
		result.setGerund(verb.getGerund());
		result.setPastparticiple(verb.getPastparticiple());
		result.setTranslations(verb.getTranslations());
		result.setConjugations(conjugations);
		log.debug(result.toString());
		return result;
	}
}
