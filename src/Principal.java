import com.google.gson.JsonSyntaxException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        int opcionElegida = 0;

        // Instancias de clases necesarias
        ConsultaConversion consulta = new ConsultaConversion();
        Calculos calculos = new Calculos(consulta);
        GeneradorDeArchivos generador = new GeneradorDeArchivos();

        List<String> respuestas = new ArrayList<>();

        String menu = """
                \n***************************************************
                *** Sea bienvenido al Conversor de Monedas EGC ***
                
                1) Peso Mexicano ==>> Dólar Estadounidense
                2) Peso Mexicano ==>> Euro
                3) Peso Mexicano ==>> Libra Esterlina
                4) Dólar Estadounidense ==>> Peso Mexicano
                5) Euro ==>> Peso Mexicano
                6) Libra Esterlina ==>> Peso Mexicano
                
                7) Otra opción de conversión
                
                8) Salir
                ***************************************************
                """;

        while (opcionElegida != 8) {
            try {
                System.out.println(menu);
                System.out.print("Seleccione una opción: ");
                opcionElegida = Integer.parseInt(lectura.nextLine());

                // Validar rango de opciones
                if (opcionElegida < 1 || opcionElegida > 8) {
                    System.out.println("Por favor, elija una opción válida del menú.");
                    continue;
                }

                // Obtener la marca de tiempo actual
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                switch (opcionElegida) {
                    case 1 -> {
                        calculos.almacenarValores("MXN", "USD");
                        respuestas.add("[" + formattedDate + "] - " + calculos.obtenerMensajeRespuesta());
                    }
                    case 2 -> {
                        calculos.almacenarValores("MXN", "EUR");
                        respuestas.add("[" + formattedDate + "] - " + calculos.obtenerMensajeRespuesta());
                    }
                    case 3 -> {
                        calculos.almacenarValores("MXN", "GBP");
                        respuestas.add("[" + formattedDate + "] - " + calculos.obtenerMensajeRespuesta());
                    }
                    case 4 -> {
                        calculos.almacenarValores("USD", "MXN");
                        respuestas.add("[" + formattedDate + "] - " + calculos.obtenerMensajeRespuesta());
                    }
                    case 5 -> {
                        calculos.almacenarValores("EUR", "MXN");
                        respuestas.add("[" + formattedDate + "] - " + calculos.obtenerMensajeRespuesta());
                    }
                    case 6 -> {
                        calculos.almacenarValores("GBP", "MXN");
                        respuestas.add("[" + formattedDate + "] - " + calculos.obtenerMensajeRespuesta());
                    }
                    case 7 -> {
                        calculos.almacenarValoresPersonalizados();
                        respuestas.add("[" + formattedDate + "] - " + calculos.obtenerMensajeRespuesta());
                    }
                    case 8 -> System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta luego!");
                }
            } catch (JsonSyntaxException | NullPointerException e) {
                System.out.println("Error. Ingrese solo códigos de moneda válidos.");
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Error. Ingrese un valor numérico válido.");
            }
        }

        // Guardar historial de respuestas
        generador.guardarJson(respuestas);

        // Cerrar el scanner
        lectura.close();

        System.out.println("Programa finalizado.");
    }
}
