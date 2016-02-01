package ch.zir.juegoverbos.app.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ch.zir.juegoverbos.api.Verb;
import ch.zir.juegoverbos.app.store.VerbStore;

import com.google.common.base.Optional;

@Path("/verbs")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class VerbsResource {

	private final VerbStore store;

	public VerbsResource(final VerbStore store) {
		this.store = store;
	}

	@GET
	public List<Verb> get(@QueryParam("name") final Optional<String> name) {
		return store.getVerbs();
	}

	@GET
	@Path("/{infinitive}")
	public Verb getVerb(@PathParam("infinitive") final String infinitive) {
		return store.getVerb(infinitive);
	}

	@GET
	@Path("/{infinitive}/{tense}")
	public Verb getVerb(@PathParam("infinitive") final String infinitive, @PathParam("tense") final String tense) {
		return store.getVerb(infinitive, tense);
	}
}
