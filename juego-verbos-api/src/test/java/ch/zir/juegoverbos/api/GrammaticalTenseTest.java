package ch.zir.juegoverbos.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GrammaticalTenseTest {

	@Test
	public void toJson() throws JsonProcessingException {
		final GrammaticalTense testee = GrammaticalTense.FUTURE;
		final String actual = new ObjectMapper().writeValueAsString(testee);

		// assertThat(actual,
		// equalTo("{\"es\":\"Futuro\",\"en\":\"Future\",\"de\":\"\"}"));
		assertThat(actual, CoreMatchers.containsString("\"key\":\"FUTURE\""));
	}

	@Test
	public void from() {
		final GrammaticalTense actual = GrammaticalTense.from("Present");
		assertThat(actual, equalTo(GrammaticalTense.PRESENT));
	}
}
