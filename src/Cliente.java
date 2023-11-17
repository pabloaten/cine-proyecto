// Clase que representa un cliente
public class Cliente {
    private String salaPreferida;
    private int id;

    public Cliente(String salaPreferida,int id) {
        this.salaPreferida = salaPreferida;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalaPreferida() {
        return salaPreferida;
    }
}
