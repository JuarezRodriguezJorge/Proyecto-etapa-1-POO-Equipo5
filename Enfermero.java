import java.util.TreeSet;

public class Enfermero {
    String nombre;
    String cedula;
    String especialidad;
    int noPacientes;
    Paciente[] listaPacientes;

    public Enfermero(String nombre, String cedula, String especialidad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.noPacientes = 0;
        this.listaPacientes = new Paciente[3]; // Límite de 3 pacientes
    }

    public void registroEnSistema() {
        System.out.println("Enfermero " + this.nombre + " registrado. Especialidad: " + this.especialidad);
    }

    // Revisa con un for si el paciente ya está en la lista de este enfermero
    public boolean tienePaciente(Paciente p) {
        for (int i = 0; i < this.noPacientes; i++) {
            if (this.listaPacientes[i] == p) {
                return true;
            }
        }
        return false;
    }

    public void darTratamiento(Paciente p) {
        if (!this.tienePaciente(p)) {
            System.out.println("Error: el paciente " + p.nombre + " no está asignado al enfermero " + this.nombre + ".");
            return;
        }
        p.estado = 2; // Asegura estado de tratamiento
        System.out.println("El enfermero " + this.nombre + " aplica el tratamiento a " + p.nombre);
    }

    public void verListaPacientes() {
        TreeSet<String> ordenadas = new TreeSet<>();
        for (int i = 0; i < this.noPacientes; i++) {
            Paciente p = this.listaPacientes[i];
            ordenadas.add(p.apellidos + " " + p.nombre + " - " + p.especialidadAtencion);
        }

        String[] arregloOrdenado = ordenadas.toArray(new String[0]);
        System.out.println("\nLista de pacientes de " + this.nombre + " (Orden Descendente):");
        for (int i = arregloOrdenado.length - 1; i >= 0; i--) {
            System.out.println(arregloOrdenado[i]);
        }
    }
}
