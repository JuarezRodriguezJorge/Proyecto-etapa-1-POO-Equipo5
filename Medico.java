import java.util.TreeSet;

public class Medico {
    String nombre;
    String cedula;
    String especialidad;
    int noPacientes;
    Paciente[] listaPacientes;   // Arreglo lineal para el registro (máximo 10)
    Paciente pacienteEnConsulta; // Paciente que atiende ahora (null si está libre)

    public Medico(String nombre, String cedula, String especialidad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.noPacientes = 0;
        this.listaPacientes = new Paciente[10]; // Límite de 10 pacientes de la especialidad
        this.pacienteEnConsulta = null;
    }

    public void registroEnSistema() {
        System.out.println("Médico " + this.nombre + " registrado. Especialidad: " + this.especialidad);
    }

    // Revisa con un for si el paciente ya está en la lista de este médico
    public boolean tienePaciente(Paciente p) {
        for (int i = 0; i < this.noPacientes; i++) {
            if (this.listaPacientes[i] == p) {
                return true;
            }
        }
        return false;
    }

    // El médico pide al sistema que le asigne al paciente.
    // Devuelve true si al terminar el paciente está en su lista.
    public boolean solicitarPaciente(Sistema sistema, Paciente p) {
        System.out.println("El médico " + this.nombre + " solicita al paciente " + p.nombre);
        if (!this.tienePaciente(p)) {
            sistema.asignarPaciente(this, p);
        }
        return this.tienePaciente(p);
    }

    public void darConsulta(Paciente p) {
        if (!this.tienePaciente(p)) {
            System.out.println("Error: el paciente " + p.nombre + " no está asignado al médico " + this.nombre + ".");
            return;
        }
        if (this.pacienteEnConsulta != null) {
            System.out.println("Error: el médico " + this.nombre + " ya está dando consulta a "
                    + this.pacienteEnConsulta.nombre + ". Solo puede atender a un paciente a la vez.");
            return;
        }
        this.pacienteEnConsulta = p;
        p.estado = 1; // Cambia estado a 'En consulta'
        System.out.println("El médico " + this.nombre + " está dando consulta a " + p.nombre);
    }

    public void darTratamiento(Paciente p) {
        if (!this.tienePaciente(p)) {
            System.out.println("Error: el paciente " + p.nombre + " no está asignado al médico " + this.nombre + ".");
            return;
        }
        p.estado = 2; // Cambia estado a 'En tratamiento'
        // Solo se libera la consulta si este era el paciente que estaba en consulta
        if (this.pacienteEnConsulta == p) {
            this.pacienteEnConsulta = null;
        }
        System.out.println("El médico receta tratamiento a " + p.nombre);
    }

    public void verListaPacientes() {
        // Usamos TreeSet para ordenar alfabéticamente (visto en Práctica 3)
        TreeSet<String> ordenadas = new TreeSet<>();

        for (int i = 0; i < this.noPacientes; i++) {
            Paciente p = this.listaPacientes[i];
            // Formato: Apellidos Nombres Especialidad
            ordenadas.add(p.apellidos + " " + p.nombre + " - " + p.especialidadAtencion);
        }

        // Convertimos a arreglo para imprimir en orden descendente usando un for inverso
        String[] arregloOrdenado = ordenadas.toArray(new String[0]);
        System.out.println("\nLista de pacientes de " + this.nombre + " (Orden Descendente):");
        for (int i = arregloOrdenado.length - 1; i >= 0; i--) {
            System.out.println(arregloOrdenado[i]);
        }
    }
}
