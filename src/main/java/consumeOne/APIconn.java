package consumeOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

public class APIconn {

	static String BASEURL = "https://labb3db.herokuapp.com/DB";

	public String getAll() throws IOException {
		URL url = new URL(BASEURL + "/all");
		String method = "GET";
		connSetup(url, method);
		String jsonText = read(url);
		return jsonText;

	}

	private void connSetup(URL url, String method) throws IOException, ProtocolException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		int responsecode = conn.getResponseCode();
		if (responsecode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		} else {
			System.out.println("Ok!");
		}
	}

	private String read(URL url) throws IOException {
		InputStream is = url.openStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public String getId(int id) throws IOException {
		URL url = new URL(BASEURL + "/user/id/" + id);
		String method = "GET";
		connSetup(url, method);
		String jsonText = read(url);
		return jsonText;
	}

	public String getName(String name) throws IOException {
		URL url = new URL(BASEURL + "/user/name/" + name);
		String method = "GET";
		connSetup(url, method);
		String jsonText = read(url);
		return jsonText;
	}

	public void addPerson(String name, String profession) throws IOException {
		URL url = new URL(BASEURL + "/user/add/" + name + "/" + profession);
		String method = "POST";
		connSetup(url, method);
	}

	public void updatePerson(int id, String name, String profession) throws IOException {
		URL url = new URL(BASEURL + "/user/update/" + id + "/" + name + "/" + profession);
		String method = "PUT";
		connSetup(url, method);
	}

	public void deletePerson(int id)  throws IOException {
		URL url = new URL(BASEURL + "/user/delete/" + id);
		String method = "DELETE";
		connSetup(url, method);		
	}

}
