package ch.zir.juegoverbos.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Verb {

	private String infinitive;
	private Map<Language, String> translations;

	private List<GrammaticalConjugation> conjugations;

	public Verb() {
		// JSON deserialize
	}

	public Verb(final String infinitive, final Map<Language, String> translations, final List<GrammaticalConjugation> conjugations) {
		super();
		this.infinitive = infinitive;
		this.translations = translations;
		this.conjugations = conjugations;
	}

	public String getInfinitive() {
		return infinitive;
	}

	// public String getTranslationDefault() {
	// return getTranslation(Language.EN);
	// }
	//
	// public String getTranslation(final Language lang) {
	// String result = translations.get(Language.EN);
	//
	// if (lang != null || lang != Language.EN) {
	// result = translations.get(lang);
	// }
	// return result;
	// }

	@JsonProperty
	public Map<Language, String> getTranslations() {
		return translations;
	}

	@JsonProperty
	public List<GrammaticalConjugation> getConjugations() {
		return conjugations;
	}

}
