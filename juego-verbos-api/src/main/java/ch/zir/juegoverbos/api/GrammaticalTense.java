package ch.zir.juegoverbos.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

//@JsonFormat(shape = Shape.OBJECT)
@JsonFormat(shape = Shape.STRING)
public enum GrammaticalTense {

	PRESENT("Presente", "Present", "Präsens"), //
	FUTURE("Futuro", "Future", ""), //
	IMPERFECT("Imperfecto", "Imperfect", ""), //
	PRETERITE("Pretérito", "Preterite", ""), //
	CONDIDIONAL("Condicional", "Conditional", ""), //
	PRESENT_PERFECT("Presente perfecto", "Present Perfect", ""), //
	FUTURE_PERFECT("Futuro perfecto", "Future Perfect", ""), //
	PAST_PERFECT("Pluscuamperfecto", "Past Perfect", ""), //
	PRETERITE_ARCHAIC("Pretérito anterior", "Preterite (Archaic)", ""), //
	CONDITIONAL_PERFECT("Condicional perfecto", "Conditional Perfect", "");

	private final String es;
	private final String en;
	private final String de;

	private GrammaticalTense(final String es, final String en, final String de) {
		this.es = es;
		this.en = en;
		this.de = de;
	}

	public String getEs() {
		return es;
	}

	public String getEn() {
		return en;
	}

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
}
