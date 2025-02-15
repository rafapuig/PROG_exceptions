package resources;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpConnectionDemo {

    public static void main(String[] args) {
        demo1();
        demo2();
    }

    public static void demo1() {

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Accept", "application/json")
                    .uri(URI.create("https://rickandmortyapi.com/api/character"))
                    .build();
            HttpResponse<String> response;
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());

            try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("output.json"))) {
                osw.write(response.body());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void demo2() {

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Accept", "image/png")
                    .uri(URI.create("https://raw.githubusercontent.com/rafapuig/PMDM7N_2024/refs/heads/master/escudos/levante-ud-logo-escudo-1.png"))
                    .build();
            HttpResponse<InputStream> response;
            response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            System.out.println(response.statusCode());

            final InputStream is = response.body();
            final OutputStream os = new FileOutputStream("escudo.png");
            try (is; os) {
                //os.write(is.readAllBytes());
                //os.flush();
                is.transferTo(os);
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
