package ch.zir.juegoverbos.api;

import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonFormat(shape = Shape.OBJECT)
@JsonFormat(shape = Shape.OBJECT)
public enum GrammaticalTense {

	PRESENT("PRESENT", "Presente", "Present", "Präsens", true), //
	PRESENT_PERFECT("PRESENT_PERFECT", "Préterito perfecto", "Present Perfect", "Perfekt", true), //
	PRETERITE("PRETERITE", "Pretérito Indefinido", "Preterite", "Präteritum", true), //
	FUTURE("FUTURE", "Futuro", "Future", "Futur", false), //
	IMPERFECT("IMPERFECT", "Pretérito Imperfecto", "Imperfect", "Imperfekt", false), //
	CONDIDIONAL("CONDIDIONAL", "Condicional", "Conditional", "Konditional", false), //
	FUTURE_PERFECT("FUTURE_PERFECT", "Futuro perfecto", "Future Perfect", "", false), //
	PAST_PERFECT("PAST_PERFECT", "Pluscuamperfecto", "Past Perfect", "Präteritum Perfekt", false), //
	PRETERITE_ARCHAIC("PRETERITE_ARCHAIC", "Pretérito anterior", "Preterite (Archaic)", "", false), //
	CONDITIONAL_PERFECT("CONDITIONAL_PERFECT", "Condicional perfecto", "Conditional Perfect", "", false), //
	GERUNDIO("GERUNDIO", "Gerundio", "Gerund", "", true), //
	PASTPARTICIPLE("PASTPARTICIPLE", "Participio", "Pastparticiple", "", true);

	private final String key;
	private final String es;
	private final String en;
	private final String de;
	private boolean active;

	private GrammaticalTense(final String key, final String es, final String en, final String de, final boolean active) {
		this.key = key;
		this.es = es;
		this.en = en;
		this.de = de;
		this.active = active;
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
				.filter(v -> v.getEn().equals(enString)).findFirst();
		return tense.get();
	}
}
