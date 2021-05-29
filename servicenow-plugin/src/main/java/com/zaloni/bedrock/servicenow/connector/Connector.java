package com.zaloni.bedrock.servicenow.connector;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Sends network request
 */
public class Connector {

    public static final Logger LOGGER = LoggerFactory.getLogger(Connector.class);

    /**
     * Sends a POST request to the url defined in HttpPost instance
     *
     * @param httpPost the HttpPost instance that will contain the payload, url and headers, if any.
     * @return ImmutablePair of Status Code and JSON response.
     * @throws IOException
     */
    public Pair<Integer, String> post(HttpPost httpPost) throws IOException, ParseException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity entity = httpResponse.getEntity();
        String jsonResponse = EntityUtils.toString(entity);
        LOGGER.info("ServiceNow Response: {}", jsonResponse);
        try {
            return new ImmutablePair<>(httpResponse.getCode(), jsonResponse);
        } finally {
            httpClient.close();
            httpResponse.close();
        }
    }
}
