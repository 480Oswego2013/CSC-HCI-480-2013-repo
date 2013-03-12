package edu.oswego.csc480_hci521_2013.client.place;

import java.util.HashMap;
import java.util.Map;

public class TokenParser {
	
	private Map<String, String> args;
	
	public TokenParser() {
		args = new HashMap<String, String>();
	}
	
	/**
	 * Parse a token and return a map of all parameters and their corresponding
	 * values. Assumes that the token follows the following format:
	 * <code>param1=value1&amp;param2=value2&amp;param3=value3</code>
	 * 
	 * @param token	Token to be parsed
	 * @return		Resultant map of parameter and value pairs
	 */
	public static Map<String, String> parse(String token) {
		Map<String, String> map = new HashMap<String, String>();
		
		String[] params = token.split("&");
		for(String param : params) {
			String[] split = param.split("=");
			map.put(split[0], split[1]);
		}
		
		return map;
	}
	
	/**
	 * Add an argument to the internal map.
	 * 
	 * @param param	Name of the parameter
	 * @param value	Argument value
	 */
	public void addArgument(String param, String value) {
		args.put(param, value);
	}
	
	/**
	 * Serialize the internal argument map to a single token string. This token
	 * can then be used with a 
	 * {@link com.google.gwt.place.shared.PlaceTokenizer PlaceTokenizer} for 
	 * appending on the URL.
	 * 
	 * @return
	 */
	public String serialize() {
		String token = "";
		for(Map.Entry<String, String> entry : args.entrySet()) {
			if(token.length() > 0) token += "&";
			token += entry.getKey() + "=" + entry.getValue();
		}
		return token;
	}

}
