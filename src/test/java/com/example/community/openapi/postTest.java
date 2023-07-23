package com.example.community.openapi;

import com.example.community.util.SignatureUtil;
import com.tencent.saas.saassdkjava.config.SaasOpenApiConfig;
import jakarta.annotation.Resource;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: lichengcan
 * @date: 2023-07-20 15:50
 * @description
 **/
@SpringBootTest
public class postTest {
//    @Resource
//    private RestTemplate restTemplate;

    @Resource
    private SaasOpenApiConfig saasOpenApiConfig;


    @Test
    public void callOpenApiPost() throws Exception {
        URIBuilder uriBuilder = new URIBuilder("https://test-open.tanbse.tencent.com/collect/v1/monitoringSoftware/createMonitoringSoftware");
        HttpMethod httpMethod = HttpMethod.POST;
        HttpHeaders headers = new HttpHeaders();


        String postData = "{\n" +
                "    \"isAsync\": true,\n" +
                "    \"statisticalObjectId\": 1,\n" +
                "    \"dataType\": \"dataType_xmroy\",\n" +
                "    \"propertyOne\": \"propertyOne_2o7e2\",\n" +
                "    \"propertyTwo\": \"propertyTwo_yekwh\",\n" +
                "    \"propertyThree\": \"propertyThree_fkyaz\", \n" +
                "    \"aliasOne\": \"aliasOne_plfw6\", \n" +
                "    \"aliasTwo\": \"aliasTwo_d7ufs\", \n" +
                "    \"aliasThree\": \"aliasThree_jf80b\",\n" +
                "    \"paramName\": \"paramName_sux7e\",\n" +
                "    \"paramValue\": 1.0,\n" +
                "    \"paramUnit\": \"paramUnit_sl7lb\", \n" +
                "    \"dataStartTime\": \"2023-07-20\", \n" +
                "    \"dataEndTime\": \"2023-07-20\", \n" +
                "    \"statisticalObjectName\": \"statisticalObjectName_hcbu8\",\n" +
                "    \"accountingBoundaryName\": \"accountingBoundaryName_tm5pl\",\n" +
                "    \"accountingBoundaryId\": 1, \n" +
                "    \"enterpriseName\": \"enterpriseName_4a9cs\", \n" +
                "    \"enterpriseTenantId\": 1, \n" +
                "    \"tenantId\": 1, \n" +
                "    \"type\": 1\n" +
                "}";

        String contentType = "application/json";
        String canonicalHeaders = "content-type:" + contentType + "\n" + "X-SAAS-HOST:" + uriBuilder.getHost() + ":" + uriBuilder.getPort() + "\n";
        //url入参处理
        String canonicalQueryString = resolveQueryString(uriBuilder.getQueryParams().stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue)));
        String signedHeaders = "content-type;X-SAAS-HOST";
        String timestamp = this.getTimestamp();

        //进行加密
        String signature = SignatureUtil.sign(httpMethod.name(), uriBuilder.getPath(), canonicalQueryString, canonicalHeaders, signedHeaders,
                postData, saasOpenApiConfig.getAlgorithm(), timestamp, saasOpenApiConfig.getSecretId(), saasOpenApiConfig.getSecretKey());

        String authorization = SignatureUtil.authorization(saasOpenApiConfig.getAlgorithm(), saasOpenApiConfig.getSecretId(), signedHeaders, signature);
        System.out.println("authorization = " + authorization);
//        // 设置鉴权参数，如果此参数设置错误，将触发“AGW.xxxxx”类型的错误，详见3.4章节
//        headers.add("Content-Type", contentType);
//        headers.add("X-SAAS-HOST", uriBuilder.getHost() + ":" + uriBuilder.getPort());
//        headers.add("X-SAAS-AUTHORIZATION", authorization);
//        headers.add("X-SAAS-TIMESTAMP", timestamp);


//        HttpEntity<String> requestEntity = new HttpEntity<>(postData, headers);
//        ResponseEntity<String> resEntity = restTemplate.exchange(uriBuilder.build(), httpMethod, requestEntity, String.class);
//
//        String resStr = resEntity.getBody();
//        log.info("http post call url:{} headers:{} status:{} resStr:{}", uriBuilder.build(), resEntity.getStatusCode(), headers, resStr);
    }

    private String getTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 解析查询参数字符串
     *
     * @param params 查询参数对象
     */
    private String resolveQueryString(Map<String, String> params) throws Exception {
        if (params == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                sb.append(URLDecoder.decode(entry.getKey(), StandardCharsets.UTF_8.name()))
                        .append("=").append(URLDecoder.decode(entry.getValue(), StandardCharsets.UTF_8.name())).append("&");
            }
        }
        return removeSuffix(sb.toString(), "&");
    }

    private String removeSuffix(String str, String suffix) {
        if (!StringUtils.isEmpty(str) && !StringUtils.isEmpty(suffix)) {
            return str.endsWith(suffix) ? str.substring(0, str.length() - suffix.length()) : str;
        } else {
            return str;
        }
    }
}
