package ch.zir.juegoverbos.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ch.zir.juegoverbos.app.resources.VerbsResource;
import ch.zir.juegoverbos.app.store.VerbStore;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;

public class JuegoVerbosApplication extends Application<JuegoVerbosConfiguration> {

	private VerbStore store;

	@Override
	public String getName() {
		return "juego-verbos";
	}

	@Override
	public void initialize(final Bootstrap<JuegoVerbosConfiguration> bootstrap) {
		super.initialize(bootstrap);

		// bootstrap.addBundle(new FileAssetsBundle("../../../../Work/Projects/juego-verbos-ui/app/", "/ui", "index.html"));
		bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/ui/"));

		store = new VerbStore();
		store.load();
	}

	@Override
	public void run(final JuegoVerbosConfiguration configuration, final Environment environment) throws Exception {
		final VerbsResource resource = new VerbsResource(store);
		environment.jersey().register(resource);

		// TemplateHealthCheck healthCheck = new
		// TemplateHealthCheck(configuration.getTemplate());
		// environment.healthChecks().register("template", healthCheck);
	}

	public static void main(final String[] args) throws Exception {
		new JuegoVerbosApplication().run(args);
	}

}
