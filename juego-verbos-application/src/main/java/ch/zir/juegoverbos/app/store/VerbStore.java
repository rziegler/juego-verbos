package ch.zir.juegoverbos.app.store;

import java.util.ArrayList;
import java.util.List;

import ch.zir.juegoverbos.api.Verb;
import ch.zir.juegoverbos.app.store.csv.CsvLoader;

public class VerbStore {

	List<Verb> verbs;

	public VerbStore() {
		verbs = new ArrayList<>();
	}

	public void load() {
		// final Map<Language, String> translations = new HashMap<>();
		// translations.put(Language.EN, "go");
		// translations.put(Language.DE, "gehen");
		//
		// final List<GrammaticalConjugation> conjugations = new ArrayList<>();
		// conjugations.add(new GrammaticalConjugation(PRESENT, FIRST_SINGULAR,
		// "voy"));
		// conjugations.add(new GrammaticalConjugation(PRESENT, SECOND_SINGULAR,
		// "vas"));
		// conjugations.add(new GrammaticalConjugation(PRESENT, THIRD_SINGULAR,
		// "va"));
		// conjugations.add(new GrammaticalConjugation(PRESENT, FIRST_PLURAL,
		// "vamos"));
		// conjugations.add(new GrammaticalConjugation(PRESENT, SECOND_PLURAL,
		// "vais"));
		// conjugations.add(new GrammaticalConjugation(PRESENT, THIRD_PLURAL,
		// "van"));
		//
		// verbs.add(new Verb("ir", translations, conjugations));

		verbs.addAll(new CsvLoader().load());
	}

	public List<Verb> getVerbs() {
		return verbs;
	}

}
