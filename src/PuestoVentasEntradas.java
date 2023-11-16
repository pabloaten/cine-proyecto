import java.util.concurrent.LinkedBlockingQueue;

class PuestoVentaEntradas implements Runnable {
    private LinkedBlockingQueue<EntradaCine> colaClientes;
    private String[] butacasDisponibles;

    public PuestoVentaEntradas(LinkedBlockingQueue<EntradaCine> colaClientes, String[] butacasDisponibles) {
        this.colaClientes = colaClientes;
        this.butacasDisponibles = butacasDisponibles;
    }

    @Override
    public void run() {
        // Simular clientes haciendo cola para sacar entradas
        for (int i = 0; i < 10; i++) {
            // Cada cliente solicita una butaca aleatoria disponible
            String butacaSolicitada = obtenerButacaDisponible();
            EntradaCine entradaCliente = new EntradaCine("Spider-Man: No Way Home", "18:00", butacaSolicitada);
            try {
                colaClientes.put(entradaCliente);  // Utilizamos put para encolar en LinkedBlockingQueue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Marcar la butaca como ocupada
            marcarButacaOcupada(butacaSolicitada);
        }
    }

    // Método para obtener una butaca disponible y marcarla como ocupada
    private synchronized String obtenerButacaDisponible() {
        for (int i = 0; i < butacasDisponibles.length; i++) {
            if (butacasDisponibles[i] != null) {
                String butaca = butacasDisponibles[i];
                butacasDisponibles[i] = null;
                return butaca;
            }
        }
        return null;  // En este caso, no hay butacas disponibles
    }

    // Método para marcar una butaca como ocupada
    private synchronized void marcarButacaOcupada(String butaca) {
        // Simular un proceso de venta que tarda un tiempo
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Butaca ocupada - " + butaca);
    }
}