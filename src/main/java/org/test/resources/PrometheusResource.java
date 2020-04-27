package org.test.resources;

import io.prometheus.client.Gauge;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/prometheus")
@Produces(MediaType.APPLICATION_JSON)
public class PrometheusResource {

    static final Gauge simpleAlert = Gauge.build()
            .name("simple_alert_guage")
            .help("Simple alert guage").register();

    @GET
    @Path("/startAlert")
    @Produces(MediaType.TEXT_PLAIN)
    public String startAlert(@QueryParam("value") Optional<Double> valueOpt) {
        Double value = valueOpt.orElse(1.0).doubleValue();
        simpleAlert.set(value);
        return "Set simple_alert_guage to " + value;
    }

    @GET
    @Path("/stopAlert")
    @Produces(MediaType.TEXT_PLAIN)
    public String stopAlert() {
        simpleAlert.set(0.0);
        return "Set simple_alert_guage to 0.0";
    }
}
