import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

// Clase que representa un puesto de venta de entradas
public class PuestoVentaEntradas implements Runnable {
    private String nombre;
    private LinkedBlockingQueue<EntradaCine> colaClientes;
    private String[] butacasDisponibles;
    private Set<String> butacasOcupadas;
    private Semaphore semaforo;
    private int contador = 0;

    public PuestoVentaEntradas(String nombre, LinkedBlockingQueue<EntradaCine> colaClientes,
                               String[] butacasDisponibles, Set<String> butacasOcupadas, Semaphore semaforo) {
        this.nombre = nombre;
        this.colaClientes = colaClientes;
        this.butacasDisponibles = butacasDisponibles;
        this.butacasOcupadas = butacasOcupadas;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        boolean chivato = true;
        while (chivato) {
            try {
                semaforo.acquire(); // Adquirir el permiso del semáforo
                EntradaCine entradaCliente = colaClientes.poll();  // Obtener una entrada de la cola

                if (entradaCliente == null) {
                    // Si la cola está vacía, el puesto termina
                    chivato = false;
                    System.out.println(contador+" entradas vendidas en el puesto "+this.nombre);
                }

                // Asignar butaca al cliente
                asignarButaca(entradaCliente);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforo.release(); // Liberar el permiso del semáforo, incluso si ocurre una excepción
            }
        }
    }

    // Método para asignar una butaca al cliente
    private void asignarButaca(EntradaCine entradaCliente) {
        Cliente cliente = entradaCliente.getCliente();
        String salaPreferida = cliente.getSalaPreferida();

        for (String butaca : butacasDisponibles) {
            if (butaca.startsWith(salaPreferida) && !butacaOcupada(butaca)) {
                entradaCliente = new EntradaCine(
                        entradaCliente.getPelicula(), entradaCliente.getSala(), entradaCliente.getHora(), butaca, cliente);

                System.out.println(nombre + ": Cliente numero: " + cliente.getId() +" "+entradaCliente.getButaca() +
                        " - Película: " + entradaCliente.getPelicula() +" "+ entradaCliente.getHora());

                // Agregar la butaca al conjunto de butacas ocupadas
                butacasOcupadas.add(butaca);
                this.contador+=1;

                // Simular un proceso de venta que tarda un tiempo
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return;
            }
        }

        System.out.println(nombre + ": No hay butacas disponibles en la sala preferida para " + entradaCliente.getSala());
    }

    // Método para verificar si una butaca está ocupada
    private boolean butacaOcupada(String butaca) {
        return butacasOcupadas.contains(butaca);
    }
}
