/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServices;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;

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

    public <T> T getPlanetas(Class<T> responseType, String token) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("planeta");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, token).get(responseType);
    }

    public <T> T putPlaneta(Object requestEntity, Class<T> responseType, String numPlaneta, String token) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("planeta/{0}", new Object[]{numPlaneta})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, token).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);
    }

    public <T> T deletePlaneta(Class<T> responseType, String numPlaneta, String token) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("planeta/{0}", new Object[]{numPlaneta})).request().header(HttpHeaders.AUTHORIZATION, token).delete(responseType);
    }

    public <T> T postGalaxia(Object requestEntity, Class<T> responseType, String token) throws ClientErrorException {
//        Invocation.Builder header = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, token);
//        System.out.println("El token es "+ token);
//        Invocation buildPost = header.buildPost(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
//        T invoke = buildPost.invoke(responseType);
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, token).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);

//        return invoke;
    }

    public <T> T postPlaneta(Object requestEntity, Class<T> responseType, String token) throws ClientErrorException {
        return webTarget.path("planeta").request(javax.ws.rs.core.MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, token).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);
    }

    public String getPlanetasTexto(String token) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("planeta/texto");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).header(HttpHeaders.AUTHORIZATION, token).get(String.class);
    }

    public <T> T getGalaxia(Class<T> responseType, String token) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, token).get(responseType);
    }

    public <T> T getPlaneta(Class<T> responseType, String numPlaneta, String token) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("planeta/{0}", new Object[]{numPlaneta}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, token).get(responseType);
    }

    public void close() {
        client.close();
    }

}
