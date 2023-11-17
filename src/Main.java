import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        LinkedBlockingQueue<EntradaCine> colaClientes = new LinkedBlockingQueue<>();
        String[] butacasDisponibles = new String[8 * 40];
        Set<String> butacasOcupadas = new HashSet<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 40; j++) {
                butacasDisponibles[i * 40 + j] = "Sala" + (i + 1) + "-Butaca" + (j + 1);
            }
        }

        String[] peliculas = {"Spider-Man: No Way Home", "The Matrix Resurrections", "Dune", "Inception","Tetanic", "El Padrino", "Openjaime", "NPC"};
        String[] salas = {"Sala1", "Sala2", "Sala3", "Sala4","Sala5", "Sala6", "Sala7", "Sala8"};

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int salaAleatoria = random.nextInt(8);
            String salaAsignada = salas[salaAleatoria];

            Cliente cliente = new Cliente(salaAsignada,i);
            EntradaCine entradaCliente = new EntradaCine(
                    peliculas[salaAleatoria], salaAsignada, "18:00", null, cliente);

            colaClientes.offer(entradaCliente);
        }

        Semaphore semaforo = new Semaphore(1);

        PuestoVentaEntradas puesto1 = new PuestoVentaEntradas("Puesto 1", colaClientes, butacasDisponibles, butacasOcupadas, semaforo);
        PuestoVentaEntradas puesto2 = new PuestoVentaEntradas("Puesto 2", colaClientes, butacasDisponibles, butacasOcupadas, semaforo);
        PuestoVentaEntradas puesto3 = new PuestoVentaEntradas("Puesto 3", colaClientes, butacasDisponibles, butacasOcupadas, semaforo);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(puesto1);
        executorService.submit(puesto2);
        executorService.submit(puesto3);

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
