package clases.comisiones;

import java.util.*;

public class TablaComision {

    private List<Empleado> empleados;

    public TablaComision(List<Empleado> listaEmpleados) {
        this.empleados = listaEmpleados;
    }

    public TablaComision() {
    }

    public Empleado obtenerIndice(int index){
        Empleado emp = new Empleado();
        
        try{
            emp = this.empleados.get(index);
        }
        catch(Exception e){
            System.out.println("Indíce no valido.");
        }
        
        return emp;
    }
    
    public void ventasEnero() {
        Empleado EmpMaxVenta = empleados
                .stream()
                .max(Comparator.comparing(Empleado::getEnero))
                .orElseThrow(NoSuchElementException::new);

        System.out.println("Persona que mas vendió en Enero: " + EmpMaxVenta.getNombre());
        System.out.println("Ventas : Q" + EmpMaxVenta.getEnero());

        Empleado EmpMinVenta = empleados
                .stream()
                .min(Comparator.comparing(Empleado::getEnero))
                .orElseThrow(NoSuchElementException::new);

        if (!EmpMaxVenta.equals(EmpMinVenta)) {
            System.out.println("Persona que menos vendió en Enero: " + EmpMinVenta.getNombre());
            System.out.println("Ventas : Q" + EmpMinVenta.getEnero());
        }

    }

    public void ventasFebrero() {
        Empleado EmpMaxVenta = empleados
                .stream()
                .max(Comparator.comparing(Empleado::getFebrero))
                .orElseThrow(NoSuchElementException::new);

        System.out.println("Persona que mas vendió en Febrero: " + EmpMaxVenta.getNombre());
        System.out.println("Ventas : Q" + EmpMaxVenta.getFebrero());

        Empleado EmpMinVenta = empleados
                .stream()
                .min(Comparator.comparing(Empleado::getFebrero))
                .orElseThrow(NoSuchElementException::new);

        if (!EmpMaxVenta.equals(EmpMinVenta)) {
            System.out.println("Persona que menos vendió en Febrero: " + EmpMinVenta.getNombre());
            System.out.println("Ventas : Q" + EmpMinVenta.getFebrero());
        }

    }

    public void ventasMarzo() {
        Empleado EmpMaxVenta = empleados
                .stream()
                .max(Comparator.comparing(Empleado::getMarzo))
                .orElseThrow(NoSuchElementException::new);

        System.out.println("Persona que mas vendió en Marzo: " + EmpMaxVenta.getNombre());
        System.out.println("Ventas : Q" + EmpMaxVenta.getMarzo());

        Empleado EmpMinVenta = empleados
                .stream()
                .min(Comparator.comparing(Empleado::getMarzo))
                .orElseThrow(NoSuchElementException::new);

        if (!EmpMaxVenta.equals(EmpMinVenta)) {
            System.out.println("Persona que menos vendió en Marzo: " + EmpMinVenta.getNombre());
            System.out.println("Ventas : Q" + EmpMinVenta.getMarzo());
        }
    }

    public void imprimir() {
        this.empleados.forEach(empleado -> {
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Enero: Q." + empleado.getEnero());
            System.out.println("Febrero: Q" + empleado.getFebrero());
            System.out.println("Marzo: Q" + empleado.getMarzo());
        });
    }
}
