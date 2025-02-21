package resources;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpConnectionDemo {

    public static void main(String[] args) {
        //demo1();
        //demo2();
        descargarImagen();
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

            try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("output.json"))) {
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


    static void descargarImagen() {

        String url = "https://images.ctfassets.net/denf86kkcx7r/" +
                "4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/" +
                "gatocomuneuropeo-97?fm=webp&w=913";

        HttpClient client = HttpClient.newBuilder().build();
        try {
            client = HttpClient.newHttpClient();
        } catch (UncheckedIOException e) {
            System.out.println(e.getMessage());
        }

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .GET()
                    .header("Accept", "image/png")
                    .uri(URI.create(url))
                    .build();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }


        HttpResponse<InputStream> response = null;
        try {
            assert client != null;
            response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }


        OutputStream os = null;
        try {
            os = new FileOutputStream("gatito.webp");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        InputStream is = null;
        try {
            assert response != null;
            is = response.body();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        try {
            assert is != null;
            is.transferTo(os);
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

        try {
            is.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        if (client != null) {
            client.close();
        }

    }


}
