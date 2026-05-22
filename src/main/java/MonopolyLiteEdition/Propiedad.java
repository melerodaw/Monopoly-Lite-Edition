package MonopolyLiteEdition;

public class Propiedad extends Casilla {
    private int precioCompra;
    private int rentaBase;
    private Jugador propietario;
    private String grupoColor;
    private int casas;
    private boolean hotel;

    public Propiedad() {
        super();
    }

    public Propiedad(int id, String nombre, int precioCompra, int alquilerBase, Jugador propietario) {
        super(id, nombre);
        this.precioCompra = precioCompra;
        this.rentaBase = alquilerBase;
        this.propietario = propietario;
        this.grupoColor = extraerGrupoColor(nombre);
        this.casas = 0;
        this.hotel = false;
    }

    public Propiedad(Propiedad p) {
        super(p.getId(), p.getNombre());
        this.precioCompra = p.precioCompra;
        this.rentaBase = p.rentaBase;
        this.propietario = p.propietario;
        this.grupoColor = p.grupoColor;
        this.casas = p.casas;
        this.hotel = p.hotel;
    }

    public int getPrecioCompra() { return precioCompra; }
    public int getRentaBase() { return rentaBase; }
    public Jugador getPropietario() { return propietario; }
    public String getGrupoColor() { return grupoColor; }
    public int getCasas() { return casas; }
    public boolean isHotel() { return hotel; }

    public int getPrecioCasa() {
        return Math.max(1, (int) Math.round(precioCompra * 0.5));
    }

    public int getRentaActual() {
        if (hotel) {
            return rentaBase * 30;
        }
        switch (casas) {
            case 1:
                return rentaBase * 2;
            case 2:
                return rentaBase * 5;
            case 3:
                return rentaBase * 11;
            case 4:
                return rentaBase * 20;
            default:
                return rentaBase;
        }
    }

    public boolean puedeConstruir() {
        return !hotel;
    }

    public boolean construirMejora() {
        if (hotel) {
            return false;
        }
        if (casas < 4) {
            casas++;
            return true;
        }
        hotel = true;
        return true;
    }

    public String getEstadoConstruccion() {
        return hotel ? "H" : String.valueOf(casas);
    }

    public void setPrecioCompra(int precioCompra) { this.precioCompra = precioCompra; }
    public void setRentaBase(int rentaBase) { this.rentaBase = rentaBase; }
    public void setPropietario(Jugador propietario) { this.propietario = propietario; }
    public void setGrupoColor(String grupoColor) { this.grupoColor = grupoColor; }

    public void reiniciarMejoras() {
        this.casas = 0;
        this.hotel = false;
    }

    private String extraerGrupoColor(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return "SIN_GRUPO";
        }
        char c = Character.toUpperCase(nombre.charAt(0));
        if (Character.isLetter(c)) {
            return String.valueOf(c);
        }
        return "SIN_GRUPO";
    }

    @Override
    public void ejecutarAccion(Jugador j) {
        // La compra/cobro se resuelve desde el motor principal para poder pedir confirmación por consola.
    }

    @Override
    public String toString() {
        return "Propiedad{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", precioCompra=" + precioCompra +
                ", rentaBase=" + rentaBase +
                ", grupo='" + grupoColor + '\'' +
                ", propietario=" + (propietario != null ? propietario.getNombre() : "null") +
                ", casas=" + casas +
                ", hotel=" + hotel +
                '}';
    }
}
