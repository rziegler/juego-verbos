package ch.zir.juegoverbos.app;

import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bazaarvoice.dropwizard.assets.AssetsBundleConfiguration;
import com.bazaarvoice.dropwizard.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Iterables;

public class JuegoVerbosConfiguration extends Configuration implements AssetsBundleConfiguration {

	Logger log = LoggerFactory.getLogger(JuegoVerbosConfiguration.class);

	@Valid
	@NotNull
	@JsonProperty
	private final AssetsConfiguration assets = new AssetsConfiguration();

	@Override
	public AssetsConfiguration getAssetsConfiguration() {
		log.debug("Using assets configuration: " + Iterables.toString(assets.getOverrides()));
		return assets;
	}

}
