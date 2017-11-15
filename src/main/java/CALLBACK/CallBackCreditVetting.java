package CALLBACK;

import static io.restassured.RestAssured.*;

import SQLDev.DBOperations;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.specification.RequestSpecification;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Test;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;

public class CallBackCreditVetting {
    //this you have to change
    String url = "https://localhost:9002/vodafonegenericintegration/vetting";


    @Test
    public void postExample() throws SQLException {

        DBOperations dbOperations;
        dbOperations = new DBOperations();

        String randomCorrID = dbOperations.changeCorrelID();
        callBack1(randomCorrID);


    }


    public void callBack1(String corrID) {
        callBack request = new callBack();

        RestAssured.config = RestAssured
                .config()
                .sslConfig(new SSLConfig()
                        .x509HostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER));
        try {
            RequestSpecification requestSpec = given()
                    .with()
                    .header("SOAPAction", "authenticate")
                    .contentType("text/xml; charset=UTF-8;")
                    .body(request.setOrder(corrID, "CallBackCreditVetting.xml").done());
            requestSpec.auth().basic("hybrisuser", "hybrisuser");
            requestSpec.post(url).then().statusCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}