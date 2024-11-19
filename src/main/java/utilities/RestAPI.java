package utilities;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAPI extends Common {
	private RequestSpecification httpRequest;
	private String baseUrl = "";

	public RestAPI(String baseUri, Map<String, String> headers) {
		try {
			if (headers == null) {
				RestAssured.baseURI = baseUri;
				httpRequest = RestAssured.given();
			} else {
				RestAssured.baseURI = baseUri;
				httpRequest = RestAssured.given().relaxedHTTPSValidation().headers(headers);
			}
			this.baseUrl = baseUri;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public RestAPI(String baseUri, Map<String, String> headers, Map<String, String> formParameter) {
		try {
			if (headers == null) {
				RestAssured.baseURI = baseUri;
				httpRequest = RestAssured.given();
			} else {
				RestAssured.baseURI = baseUri;
				httpRequest = RestAssured.given().relaxedHTTPSValidation().headers(headers).formParams(formParameter);
			}
			this.baseUrl = baseUri;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public RestAPI(String baseUri, String userName, String password, Map<String, String> headers) {
		try {
			if (headers == null) {
				RestAssured.baseURI = baseUri;
				httpRequest = RestAssured.given().auth().preemptive().basic(userName, password);
			} else {
				RestAssured.baseURI = baseUri;
				httpRequest = RestAssured.given().auth().preemptive().basic(userName, password).headers(headers);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Response getRequest(String get) throws Exception {
		Response response = null;
		if (get == null || get.trim().equals("")) {
			response = httpRequest.get();
		} else {
			response = httpRequest.get(get);
		}
		logJson(response.getBody().asString());
		return response;
	}

	public Response get(Map<String, String> params) throws Exception {
		Response response = null;
		response = httpRequest.params(params).get();
		return response;
	}

	public static int getStatusCode(Response response) {
		try {
			return response.getStatusCode();
		} catch (Exception e) {
			return -1;
		}
	}

	public static Headers getResponseHeader(Response response) {
		try {
			return response.getHeaders();
		} catch (Exception e) {
			return null;
		}
	}

	public static String getResponseBody(Response response) {
		try {
			return response.getBody().asString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String getJsonPathValueInString(Response response, String path) {
		try {
			return response.jsonPath().get(path).toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static List<Object> getJsonPathValueInList(Response response, String path) {
		try {
			return response.jsonPath().getList(path);
		} catch (Exception e) {
			return null;
		}
	}

	public static Map<String, Object> getJsonPathValueInMap(Response response, String path) {
		try {
			return response.jsonPath().getMap(path);
		} catch (Exception e) {
			return null;
		}
	}

	public Response postRequest(String postUrl, String body) {
		String str = null;
		Response response = null;
		try {
			response = httpRequest.body(body).post(postUrl);
			info("POST request url : " + baseUrl + postUrl);
			logJson(response.getBody().asString());
		} catch (Exception e) {
			fail("Exception caught in postRequest Action, Message is " + e.getMessage());
		}
		return response;
	}

	public Response postRequest(String postUrl, Map<String, String> params) throws Exception {
		Response response = null;
		response = httpRequest.params(params).post(postUrl);
		logJson(response.getBody().asString());
		return response;
	}

	public Response postRequest(String postUrl) {
		String str = null;
		Response response = null;
		try {
			response = httpRequest.post(postUrl);
			info("POST request url : " + baseUrl + postUrl);
			logJson(response.getBody().asString());
		} catch (Exception e) {
			fail("Exception caught in postRequest Action, Message is " + e.getMessage());
		}
		return response;
	}

	public Response putRequest(String putUrl, String body) {
		String str = null;
		Response response = null;
		try {
			response = httpRequest.body(body).put(putUrl);
			info("PUT request url : " + baseUrl + putUrl);
			logJson(response.getBody().asString());
		} catch (Exception e) {
			fail("Exception caught in putRequest Action, Message is " + e.getMessage());
		}
		return response;
	}

	public Response putRequest(String body) {
		String str = null;
		Response response = null;
		try {
			response = httpRequest.body(body).put();
			logJson(response.getBody().asString());
		} catch (Exception e) {
			fail("Exception caught in putRequest Action, Message is " + e.getMessage());
		}
		return response;
	}

	public Response patchRequest(String patchUrl, String body) {
		String str = null;
		Response response = null;
		try {
			response = httpRequest.body(body).patch(patchUrl);
			info("PATCH request url : " + baseUrl + patchUrl);
			logJson(response.getBody().asString());
		} catch (Exception e) {
			fail("Exception caught in patchRequest Action, Message is " + e.getMessage());
		}
		return response;
	}

	public Response deleteRequest(String deleteUrl) {
		Response response = null;
		String str = null;
		try {
			response = httpRequest.delete(deleteUrl);
			info("DELETE request url : " + baseUrl + deleteUrl);
			logJson(response.getBody().asString());
		} catch (Exception e) {
			fail("Exception caught in deleteRequest Action, Message is " + e.getMessage());
		}
		return response;
	}
}
