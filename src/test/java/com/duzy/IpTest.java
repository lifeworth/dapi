package com.duzy;

import cn.hutool.json.JSONArray;
import com.duzy.dao.IpLocationDao;
import com.duzy.model.IpLocationModel;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zhiyuandu
 * @description
 * @since 2022/11/14 13:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IpTest {
    @Autowired
    IpLocationDao ipLocationDao;
    static String returnJson = "[\n" +
            "    {\n" +
            "        \"status\": \"success\",\n" +
            "        \"continent\": \"北美洲\",\n" +
            "        \"continentCode\": \"NA\",\n" +
            "        \"country\": \"美国\",\n" +
            "        \"countryCode\": \"US\",\n" +
            "        \"region\": \"VA\",\n" +
            "        \"regionName\": \"弗吉尼亚州\",\n" +
            "        \"city\": \"Ashburn\",\n" +
            "        \"district\": \"\",\n" +
            "        \"zip\": \"20149\",\n" +
            "        \"lat\": 39.03,\n" +
            "        \"lon\": -77.5,\n" +
            "        \"timezone\": \"America/New_York\",\n" +
            "        \"offset\": -18000,\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"isp\": \"Google LLC\",\n" +
            "        \"org\": \"Google Public DNS\",\n" +
            "        \"as\": \"AS15169 Google LLC\",\n" +
            "        \"asname\": \"GOOGLE\",\n" +
            "        \"mobile\": false,\n" +
            "        \"proxy\": false,\n" +
            "        \"hosting\": true,\n" +
            "        \"query\": \"8.8.8.8\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"status\": \"success\",\n" +
            "        \"continent\": \"北美洲\",\n" +
            "        \"continentCode\": \"NA\",\n" +
            "        \"country\": \"加拿大\",\n" +
            "        \"countryCode\": \"CA\",\n" +
            "        \"region\": \"QC\",\n" +
            "        \"regionName\": \"Quebec\",\n" +
            "        \"city\": \"蒙特利尔\",\n" +
            "        \"district\": \"\",\n" +
            "        \"zip\": \"H1K\",\n" +
            "        \"lat\": 45.6085,\n" +
            "        \"lon\": -73.5493,\n" +
            "        \"timezone\": \"America/Toronto\",\n" +
            "        \"offset\": -18000,\n" +
            "        \"currency\": \"CAD\",\n" +
            "        \"isp\": \"Le Groupe Videotron Ltee\",\n" +
            "        \"org\": \"Videotron Ltee\",\n" +
            "        \"as\": \"AS5769 Videotron Telecom Ltee\",\n" +
            "        \"asname\": \"VIDEOTRON\",\n" +
            "        \"mobile\": false,\n" +
            "        \"proxy\": false,\n" +
            "        \"hosting\": false,\n" +
            "        \"query\": \"24.48.0.1\"\n" +
            "    }\n" +
            "]";

    public  void test() {
        JSONArray jsonArray = new JSONArray(returnJson);
        List<IpLocationModel> userModels = jsonArray.toList(IpLocationModel.class);
        userModels.forEach(System.out::println);
    }

}
