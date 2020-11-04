package com.wuzz.demo.config;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/11/4 10:03
 * @since 1.0
 **/
public class RestTemplateConfig {

    private RestTemplate restTemplate = new RestTemplate(getHttpComponentsClientHttpRequestFactory());

    // 获取流 ResponseEntity<org.springframework.core.io.Resource>
    // inputStream = entity.getBody().getInputStream();

    public HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory(){
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient());
        return httpComponentsClientHttpRequestFactory;
    }

    /**
     * 功能描述: <br>
     * 自定义重定向策略
     * @Param:
     * @Return:
     * @Author: wuzhenzhao
     * @Date: 2020/11/4 10:54
     */
    class GetUriRedirectStrategy extends DefaultRedirectStrategy {

        @Override
        public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
            boolean redirected = super.isRedirected(request, response, context);
            Header[] allHeaders = response.getAllHeaders();
            for (Header header : allHeaders) {
                if (StringUtils.equals(header.getName(), "Location")) {
                    context.setAttribute("uri", header.getValue());
                }
            }
            Object uri = context.getAttribute("uri");
            response.setHeader("uri", String.valueOf(uri));
            return redirected;
        }
    }

    private HttpClient httpClient() {
        try {
            TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory connectionSocketFactory =
                    new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
            // 支持HTTP、HTTPS
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", connectionSocketFactory)
                    .build();
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
            connectionManager.setMaxTotal(200);
            connectionManager.setDefaultMaxPerRoute(100);
            connectionManager.setValidateAfterInactivity(2000);
            RequestConfig requestConfig = RequestConfig.custom()
                    // 服务器返回数据(response)的时间，超时抛出read timeout
                    .setSocketTimeout(65000)
                    // 连接上服务器(握手成功)的时间，超时抛出connect timeout
                    .setConnectTimeout(5000)
                    // 从连接池中获取连接的超时时间，超时抛出ConnectionPoolTimeoutException
                    .setConnectionRequestTimeout(1000)
                    .build();
            return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager).build();
        } catch (Exception e) {

        }
        return null;
    }
}
