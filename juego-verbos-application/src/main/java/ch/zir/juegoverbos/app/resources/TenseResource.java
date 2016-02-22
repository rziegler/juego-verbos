package ch.zir.juegoverbos.app.resources;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.zir.juegoverbos.api.GrammaticalTense;

@Path("/tenses")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class TenseResource {

	@GET
	public List<GrammaticalTense> get() {
		return Arrays.asList(GrammaticalTense.values());
	}
}
