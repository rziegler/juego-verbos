package ch.zir.juegoverbos.api;

import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonFormat(shape = Shape.OBJECT)
@JsonFormat(shape = Shape.OBJECT)
public enum GrammaticalTense {

	PRESENT("TENSE.PRESENT", "presente", "present", "Präsens", true, true), //
	PRESENT_PERFECT("TENSE.PRESENT_PERFECT", "préterito perfecto", "present perfect", "Perfekt", true, true), //
	PRETERITE("TENSE.PRETERITE", "pretérito indefinido", "preterite", "Präteritum", true, true), //
	FUTURE_SIMPLE("TENSE.FUTURE_SIMPLE", "futuro simple", "future simple", "Futur Einfach", true, true), //
	GERUNDIO("TENSE.GERUNDIO", "gerundio", "gerund", "Gerund", true, true), //
	FUTURE("TENSE.FUTURE", "futuro", "future", "Futur", false, false), //
	IMPERFECT("TENSE.IMPERFECT", "pretérito imperfecto", "imperfect", "Imperfekt", false, false), //
	CONDIDIONAL("TENSE.CONDITIONAL", "condicional", "conditional", "Konditional", false, false), //
	FUTURE_PERFECT("TENSE.FUTURE_PERFECT", "futuro perfecto", "future perfect", "", false, false), //
	PAST_PERFECT("TENSE.PAST_PERFECT", "pluscuamperfecto", "past perfect", "Präteritum Perfekt", false, false), //
	PRETERITE_ARCHAIC("TENSE.PRETERITE_ARCHAIC", "pretérito anterior", "preterite (archaic)", "", false, false), //
	CONDITIONAL_PERFECT("TENSE.CONDITIONAL_PERFECT", "condicional perfecto", "conditional perfect", "", false, false), //
	PASTPARTICIPLE("TENSE.PASTPARTICIPLE", "participio", "pastparticiple", "Partizip", false, false), //
	;

	private final String key;
	private final String es;
	private final String en;
	private final String de;
	private boolean active;
	private boolean visible;

	private GrammaticalTense(final String key, final String es, final String en, final String de, final boolean active, final boolean visible) {
		this.key = key;
		this.es = es;
		this.en = en;
		this.de = de;
		this.active = active;
		this.visible = visible;
	}

	@JsonProperty
	public String getKey() {
		return key;
	}

	@JsonProperty
	public String getEs() {
		return es;
	}

	@JsonProperty
	public String getEn() {
		return en;
	}

	@JsonProperty
	public String getDe() {
		return de;
	}

	@JsonProperty
	public boolean isActive() {
		return active;
	}

	@JsonProperty
	public boolean isVisible() {
		return visible;
	}

	public String asString(final Language lang) {
		switch (lang) {
		case ES:
			return es;
		case EN:
			return en;
		case DE:
			return de;
		default:
			throw new IllegalArgumentException("Illegal argument: " + lang);
		}
	}

	public static GrammaticalTense from(final String enString) {
		final Optional<GrammaticalTense> tense = Stream.of(GrammaticalTense.values())//
				.filter(v -> v.getEn().equalsIgnoreCase(enString)).findFirst();
		return tense.get();
	}
}
