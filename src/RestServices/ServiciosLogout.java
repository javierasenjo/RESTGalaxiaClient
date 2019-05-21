/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServices;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;

/**
 * Jersey REST client generated for REST resource:ServiciosLogout [logout]<br>
 * USAGE:
 * <pre>
 *        ServiciosLogout client = new ServiciosLogout();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author javie
 */
public class ServiciosLogout {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RestGalaxia/webresources";

    public ServiciosLogout() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("logout");
    }

    public String cerrarSesion(String token) throws ClientErrorException {
        return webTarget.request().header(HttpHeaders.AUTHORIZATION, token).post(null, String.class);
    }

    public String getXml() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
