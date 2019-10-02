import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasKey;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTest {

    private Response response = null;
    @Test
    public void testcase1(){
       response = given().queryParam("data-version","1562843391575")
                 .queryParam("req","%7B%22type%22%3A%22CLSS%22%2C%22search%22%3A%5B%7B%22property%22%3A%22core_holding_id%22%2C%22values%22%3A%5B%22GEMRE%22%5D%2C%22matchtype%22%3A%22MATCH%22%7D%2C%7B%22property%22%3A%22core_holding_id%22%2C%22values%22%3A%5B%22GEMRE%22%5D%2C%22matchtype%22%3A%22MATCH%22%7D%5D%2C%22include%22%3A%7B%22allocations%22%3A%7B%7D%2C%22statistics%22%3A%7B%7D%2C%22timeseries%22%3A%7B%7D%7D%2C%22culture%22%3A%22en-GB%22%2C%22applyFormats%22%3Afalse%2C%22limit%22%3A0%2C%22fundList%22%3A%22Institutional_NL%22%7D")
                 .when().get("https://api.kurtosys.io/tools/ksys373/api/fund/searchentity");
        Assert.assertEquals(response.getStatusCode(),200,"Correct Status code found");

        response.then().assertThat().body("values[]",hasKey("properties_pub"));
        response.then().assertThat().body("values[].properties_pub",hasKey("'base_currency"));
        response.then().assertThat().body("values[].properties_pub.base_currency.value",notNullValue());
        System.out.println("Response Time : " + response.getTime());

    }
}
