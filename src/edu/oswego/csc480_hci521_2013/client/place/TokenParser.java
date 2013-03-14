package edu.oswego.csc480_hci521_2013.client.place;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple class for parsing and serializing (marshalling/unmarshalling) a
 * Place's token. All tokens will be of the format
 * <code>param1=value1&amp;param2=value2a,value2b&amp;param3=value3</code>
 * 
 * @author Nicholas Ibarluzea
 */
public class TokenParser {
	
	private Map<String, String[]> args;
	
	private static final String EQUAL_CHAR	= "=";
	private static final String CONCAT_CHAR	= "&";
	private static final String LIST_CHAR	= ",";
	
	/**
	 * Create a parser with an empty internal map. This constructor will most
	 * likely be used when building a new token from scratch.
	 */
	public TokenParser() {
		args = new HashMap<String, String[]>();
	}
	
	/**
	 * The supplied token will be parsed and used to populate the internal map
	 * of the parser. This constructor will likely be used to serialize
	 * (unmarshal) a received token or to add on to an existing one.
	 * @param token
	 */
	public TokenParser(String token) {
		args = parse(token);
	}
	
	/**
	 * Add an argument to the internal map.
	 * 
	 * @param param	Name of the parameter
	 * @param value	Argument value(s)
	 */
	public void addArgument(String param, String... value) {
		args.put(param, value);
	}
	
	/**
	 * Get the argument value for a particular parameter.
	 * 
	 * For an argument with multiple values, only the first one is returned.
	 * 
	 * @param param	Name of parameter
	 * @return	Corresponding value
	 */
	public String getValue(String param) {
		return args.get(param)[0];
	}
	
	/**
	 * Get all argument values for a particular parameter.
	 * 
	 * @param param	Name of parameter
	 * @return	Corresponding values
	 */
	public String[] getAllValues(String param) {
		return args.get(param);
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
		for(Map.Entry<String, String[]> entry : args.entrySet()) {
			if(token.length() > 0) token += CONCAT_CHAR;
			String value =  "";
			for(String val : entry.getValue()) {
				if(value.length() > 0) value += LIST_CHAR;
				value += val;
			}
			token += entry.getKey() + EQUAL_CHAR + value;
		}
		return token;
	}
	
	/**
	 * Parse a token and return a map of all parameters and their corresponding
	 * values. Assumes that the token follows the following format:
	 * <code>param1=value1&amp;param2=value2a,value2b&amp;param3=value3</code>
	 * 
	 * @param token	Token to be parsed
	 * @return		Resultant map of parameter and value pairs
	 */
	private static Map<String, String[]> parse(String token) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		
		String[] params = token.split(CONCAT_CHAR);
		for(String param : params) {
			String[] split = param.split(EQUAL_CHAR);
			String[] values = split[1].split(LIST_CHAR);
			map.put(split[0], values);
		}
		
		return map;
	}

}
