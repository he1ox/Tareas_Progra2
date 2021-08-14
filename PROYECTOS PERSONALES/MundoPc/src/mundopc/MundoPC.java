package mundopc;

import mx.com.gm.mundopc.*;

public class MundoPC {

    public static void main(String[] args) {
        Raton raton = new Raton("USB", "xTech");
        Teclado teclado = new Teclado("USB", "Logitech");
        Monitor monitor = new Monitor("AOC", 42);

        Computadora computadora = new Computadora("Corsair", monitor, teclado, raton);
        Computadora pc = new Computadora("AORUS", monitor, teclado, raton);

        Orden orden = new Orden();
        orden.agregarComputadora(computadora);
        orden.agregarComputadora(pc);
        orden.MostrarOrden();

    }
}
