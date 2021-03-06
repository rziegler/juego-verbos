package ch.zir.juegoverbos.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Verb {

	private String infinitive;
	private String gerund;
	private String pastparticiple;

	private final Map<Language, String> translations;
	private final Set<GrammaticalConjugation> conjugations;

	public Verb() {
		translations = new HashMap<>();
		conjugations = new HashSet<>();
	}

	@JsonProperty
	public String getInfinitive() {
		return infinitive;
	}

	public void setInfinitive(final String infinitive) {
		this.infinitive = infinitive;
	}

	@JsonProperty
	public String getPastparticiple() {
		return pastparticiple;
	}

	public void setPastparticiple(final String pastparticiple) {
		this.pastparticiple = pastparticiple;
	}

	@JsonProperty
	public String getGerund() {
		return gerund;
	}

	public void setGerund(final String gerund) {
		this.gerund = gerund;
	}

	@JsonProperty
	public Map<Language, String> getTranslations() {
		return translations;
	}

	public void addTranslation(final Language lang, final String translation) {
		translations.putIfAbsent(lang, translation);
	}

	public void setTranslations(final Map<Language, String> translations) {
		this.translations.putAll(translations);
	}

	@JsonProperty
	public Set<GrammaticalConjugation> getConjugations() {
		return conjugations;
	}

	public void addConjugation(final GrammaticalConjugation conjugation) {
		conjugations.add(conjugation);
	}

	public void setConjugations(final Set<GrammaticalConjugation> conjugations) {
		this.conjugations.addAll(conjugations);
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
			return super.toString();
		}
	}
}
