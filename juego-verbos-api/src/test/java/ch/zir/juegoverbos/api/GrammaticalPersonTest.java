package ch.zir.juegoverbos.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GrammaticalPersonTest {

	@Test
	public void toJson() throws JsonProcessingException {
		final String actual = new ObjectMapper().writeValueAsString(GrammaticalPerson.FIRST_SINGULAR);
		assertThat(actual, equalTo("\"FIRST_SINGULAR\""));
	}
}
