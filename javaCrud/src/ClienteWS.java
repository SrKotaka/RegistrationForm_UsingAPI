import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ClienteWS {

    public static Object getObjeto(Object objeto, String urlWebService, String... parametros) {
        try {
            for (String parametro : parametros) {
                urlWebService = urlWebService + "/" + parametro.replaceAll(" ", "%20");
            }
            URL url = new URL(urlWebService);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            try {
                connection.connect();
            } catch (Exception erro) {
                erro.printStackTrace();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String responseJson = inputStreamToString(connection.getInputStream());
                connection.disconnect();
                responseJson = responseJson.replace("[", "").replace("]", "");
                return fromJson(responseJson, (Class<?>) objeto);
            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println("Recurso não encontrado. Verifique a URL ou os parâmetros.");
            } else if (responseCode == HttpURLConnection.HTTP_CONFLICT) {
                System.out.println("Email já existente.");
            } else if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                System.out.println("Ocorreu um erro interno no servidor.");
            } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                System.out.println("Ocorreu um erro na requisição. Código de resposta: " + responseCode);
            } else {
                System.out.println("Ocorreu um erro na requisição. Código de resposta: " + responseCode);
            }

        } catch (Exception erro) {
            erro.printStackTrace();
        }
        return null;
    }


    public static void postObjeto(String json, String urlWebService, String... parametros) {
        try {
            URL url = new URL(urlWebService);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(json);
            writer.flush();


            connection.getInputStream();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteObjeto(String urlWebService, String email) {
        try {
            urlWebService = urlWebService + "/" + email.replaceAll(" ", "%20");

            URL url = new URL(urlWebService);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setConnectTimeout(15000);
            connection.connect();

            connection.getInputStream();
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void putObjeto(Object objeto, String urlWebService, String email) {
        try {
            urlWebService = urlWebService + "/" + email.replaceAll(" ", "%20");
            String json = toJson(objeto);

            URL url = new URL(urlWebService);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setConnectTimeout(15000);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(json);
            writer.flush();

            connection.getInputStream();
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers(String urlWebService) {
        List<User> users = new ArrayList<>();

        try {
            URL url = new URL(urlWebService);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.connect();

            String responseJson = inputStreamToString(connection.getInputStream());
            connection.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, User.class);
            users = mapper.readValue(responseJson, type);

        } catch (Exception erro) {
            erro.printStackTrace();
        }

        return users;
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }

            return writer.toString();
        } else {
            return "";
        }
    }


    public static String toJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        try {
            json = mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}