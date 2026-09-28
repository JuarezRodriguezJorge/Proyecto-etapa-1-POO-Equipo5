public class Paciente {
    // Atributos definidos en las especificaciones, agregamos apellidos para el ordenamiento
    String nombre;
    String apellidos;
    String especialidadAtencion;
    int estado; // 0 = Espera, 1 = Consulta, 2 = Tratamiento

    public Paciente(String nombre, String apellidos, String especialidadAtencion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.especialidadAtencion = especialidadAtencion;
        this.estado = 0; 
    }

    public void registroEnSistema() {
        System.out.println("Paciente registrado: " + this.apellidos + " " + this.nombre);
    }

    public void solicitarConsulta() {
        System.out.println("El paciente " + this.nombre + " solicita consulta de " + this.especialidadAtencion);
    }

    public void verTratamiento() {
        if (this.estado == 2) {
            System.out.println("El paciente " + this.nombre + " está recibiendo tratamiento.");
        } else {
            System.out.println("El paciente " + this.nombre + " no tiene tratamiento activo.");
        }
    }
}
