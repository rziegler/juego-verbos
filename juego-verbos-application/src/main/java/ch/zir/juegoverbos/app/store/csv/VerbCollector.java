package ch.zir.juegoverbos.app.store.csv;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import ch.zir.juegoverbos.api.GrammaticalConjugation;
import ch.zir.juegoverbos.api.Verb;

public class VerbCollector implements Collector<CsvLine, Verb, Verb> {

	@Override
	public Supplier<Verb> supplier() {
		return Verb::new;
	}

	@Override
	public BiConsumer<Verb, CsvLine> accumulator() {
		return (verb, line) -> verb.getConjugations().add(new GrammaticalConjugation());
	}

	@Override
	public BinaryOperator<Verb> combiner() {
		return (left, right) -> {
			left.getConjugations().addAll(right.getConjugations());
			return left;
		};
	}

	@Override
	public Function<Verb, Verb> finisher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return EnumSet.of(Characteristics.UNORDERED);
	}

	public static <T> Collector<CsvLine, Verb, Verb> toVerb() {
		return new VerbCollector();
	}
}
