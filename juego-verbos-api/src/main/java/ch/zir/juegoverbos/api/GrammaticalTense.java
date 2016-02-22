package ch.zir.juegoverbos.api;

import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonFormat(shape = Shape.OBJECT)
@JsonFormat(shape = Shape.OBJECT)
public enum GrammaticalTense {

	PRESENT("PRESENT", "Presente", "Present", "Präsens"), //
	FUTURE("FUTURE", "Futuro", "Future", "Futur"), //
	IMPERFECT("IMPERFECT", "Pretérito Imperfecto", "Imperfect", "Imperfekt"), //
	PRETERITE("PRETERITE", "Pretérito Indefinido", "Preterite", "Präteritum"), //
	CONDIDIONAL("CONDIDIONAL", "Condicional", "Conditional", "Konditional"), //
	PRESENT_PERFECT("PRESENT_PERFECT", "Préterito perfecto", "Present Perfect", "Perfekt"), //
	FUTURE_PERFECT("FUTURE_PERFECT", "Futuro perfecto", "Future Perfect", ""), //
	PAST_PERFECT("PAST_PERFECT", "Pluscuamperfecto", "Past Perfect", "Präteritum Perfekt"), //
	PRETERITE_ARCHAIC("PRETERITE_ARCHAIC", "Pretérito anterior", "Preterite (Archaic)", ""), //
	CONDITIONAL_PERFECT("CONDITIONAL_PERFECT", "Condicional perfecto", "Conditional Perfect", ""), //
	GERUNDIO("GERUNDIO", "Gerundio", "Gerund", ""), //
	PASTPARTICIPLE("PASTPARTICIPLE", "Participio", "Pastparticiple", "");

	private final String key;
	private final String es;
	private final String en;
	private final String de;

	private GrammaticalTense(final String key, final String es, final String en, final String de) {
		this.key = key;
		this.es = es;
		this.en = en;
		this.de = de;
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
