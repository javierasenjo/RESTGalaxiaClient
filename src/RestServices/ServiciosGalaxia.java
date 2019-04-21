/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServices;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ServiciosGalaxia [galaxia]<br>
 * USAGE:
 * <pre>
 *        ServiciosGalaxia client = new ServiciosGalaxia();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author javie
 */
public class ServiciosGalaxia {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RestGalaxia/webresources";

    public ServiciosGalaxia() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("galaxia");
    }

    public String putPlaneta(Object requestEntity, String num) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("planetas/{0}", new Object[]{num})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), String.class);
    }

    public String getPlanetas() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("planetas");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String deletePlaneta(String num) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("planetas/{0}", new Object[]{num})).request().delete(String.class);
    }

    public String postGalaxia(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), String.class);
    }

    public String postPlaneta(Object requestEntity) throws ClientErrorException {
        return webTarget.path("planetas").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), String.class);
    }

    public <T> T getGalaxia(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getPlaneta(Class<T> responseType, String num) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("planetas/{0}", new Object[]{num}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
