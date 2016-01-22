package ch.zir.juegoverbos.app.resources;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ch.zir.juegoverbos.api.Verb;
import ch.zir.juegoverbos.app.store.VerbStore;

import com.google.common.base.Optional;

@Path("/verbs")
@Produces(MediaType.APPLICATION_JSON)
public class VerbsResource {
	private final AtomicLong counter;

	private final VerbStore store;

	public VerbsResource(final VerbStore store) {
		this.store = store;
		this.counter = new AtomicLong();
	}

	@GET
	public List<Verb> get(@QueryParam("name") final Optional<String> name) {
		return store.getVerbs();
	}
}
