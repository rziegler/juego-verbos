package ch.zir.juegoverbos.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GrammaticalConjugation {

	private GrammaticalPerson person;
	private GrammaticalTense tense;
	private String value;

	public GrammaticalConjugation() {
		// JSON deserialize
	}

	public GrammaticalConjugation(final GrammaticalTense tense, final GrammaticalPerson person, final String value) {
		super();
		this.person = person;
		this.tense = tense;
		this.value = value;
	}

	@JsonProperty
	public GrammaticalPerson getPerson() {
		return person;
	}

	@JsonProperty
	public GrammaticalTense getTense() {
		return tense;
	}

	@JsonProperty
	public String getValue() {
		return value;
	}

}
