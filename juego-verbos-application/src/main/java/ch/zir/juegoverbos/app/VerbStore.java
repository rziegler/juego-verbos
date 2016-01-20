package ch.zir.juegoverbos.app;

import static ch.zir.juegoverbos.api.GrammaticalPerson.FIRST_PLURAL;
import static ch.zir.juegoverbos.api.GrammaticalPerson.FIRST_SINGULAR;
import static ch.zir.juegoverbos.api.GrammaticalPerson.SECOND_PLURAL;
import static ch.zir.juegoverbos.api.GrammaticalPerson.SECOND_SINGULAR;
import static ch.zir.juegoverbos.api.GrammaticalPerson.THIRD_PLURAL;
import static ch.zir.juegoverbos.api.GrammaticalPerson.THIRD_SINGULAR;
import static ch.zir.juegoverbos.api.GrammaticalTense.PRESENT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.zir.juegoverbos.api.GrammaticalConjugation;
import ch.zir.juegoverbos.api.Language;
import ch.zir.juegoverbos.api.Verb;

public class VerbStore {

	List<Verb> verbs;

	public VerbStore() {
		verbs = new ArrayList<>();
	}

	public void load() {
		final Map<Language, String> translations = new HashMap<>();
		translations.put(Language.EN, "go");
		translations.put(Language.DE, "gehen");

		final List<GrammaticalConjugation> conjugations = new ArrayList<>();
		conjugations.add(new GrammaticalConjugation(PRESENT, FIRST_SINGULAR, "voy"));
		conjugations.add(new GrammaticalConjugation(PRESENT, SECOND_SINGULAR, "vas"));
		conjugations.add(new GrammaticalConjugation(PRESENT, THIRD_SINGULAR, "va"));
		conjugations.add(new GrammaticalConjugation(PRESENT, FIRST_PLURAL, "vamos"));
		conjugations.add(new GrammaticalConjugation(PRESENT, SECOND_PLURAL, "vais"));
		conjugations.add(new GrammaticalConjugation(PRESENT, THIRD_PLURAL, "van"));

		verbs.add(new Verb("ir", translations, conjugations));
	}

	public List<Verb> getVerbs() {
		return verbs;
	}

}
