package model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.API;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        divisa div;
        String divisaInicial = "";
        double valorFinal = 0;
        String divisaDestino = "";


        System.out.println("Sea bienvenido/a al Conversor de Moneda");

        while (true) {

            System.out.println("1) Dólar =>> Peso Argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real Brasileño");
            System.out.println("4) Real Brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso Colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            int opcion = s.nextInt();
            System.out.println("Ingrese el valor que deseas convertir");
            int val = s.nextInt();

            if (opcion == 1){
                divisaInicial = "USD";
                divisaDestino = "ARS";
                div = new divisa(connection(divisaInicial, gson));
                valorFinal = div.conversor(val, divisaDestino);

            }else if(opcion == 2){
                divisaInicial = "ARS";
                divisaDestino = "USD";
                div = new divisa(connection(divisaInicial, gson));
                valorFinal = div.conversor(val, divisaDestino);

            }else if(opcion == 3){
                divisaInicial = "USD";
                divisaDestino = "BRL";
                div = new divisa(connection(divisaInicial, gson));
                valorFinal = div.conversor(val, divisaDestino);

            }else if(opcion == 4){
                divisaInicial = "BRL";
                divisaDestino = "USD";
                div = new divisa(connection(divisaInicial, gson));
                valorFinal = div.conversor(val, divisaDestino);

            }else if(opcion == 5){
                divisaInicial = "USD";
                divisaDestino = "COP";
                div = new divisa(connection(divisaInicial, gson));
                valorFinal = div.conversor(val, divisaDestino);

            }else if(opcion == 6){
                divisaInicial = "COP";
                divisaDestino = "USD";
                div = new divisa(connection(divisaInicial, gson));
                valorFinal = div.conversor(val, divisaDestino);

            }else if(opcion == 7){
                break;
            }

            System.out.println("El valor " + val + " [" +
                    divisaInicial + "] corresponde al valor final de =>>> " +
                    valorFinal + " [" + divisaDestino+ "]");

        }

    }

    public static API connection(String moneda, Gson gson){

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/e003740d728c03b5f5268eb5/latest/"+moneda+"/"))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            API api = gson.fromJson(json, API.class);
            return api;
        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}