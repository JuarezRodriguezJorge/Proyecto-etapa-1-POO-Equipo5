public class Sistema {
    String nombreHospital;
    int noMedicos;
    int noEnfermeros;
    int noPacientes;

    // Estructuras lineales requeridas por las especificaciones
    Medico[] listaMedicos;
    Enfermero[] listaEnfermeros;
    Paciente[] listaPacientesTotales;

    public Sistema(String nombreHospital) {
        this.nombreHospital = nombreHospital;
        this.noMedicos = 0;
        this.noEnfermeros = 0;
        this.noPacientes = 0;
        this.listaMedicos = new Medico[50];
        this.listaEnfermeros = new Enfermero[50];
        this.listaPacientesTotales = new Paciente[100];
    }

    public void registroMedico(Medico m) {
        if (this.noMedicos >= this.listaMedicos.length) {
            System.out.println("Error: ya no hay espacio para más médicos en el sistema.");
            return;
        }
        this.listaMedicos[this.noMedicos] = m;
        this.noMedicos++;
        m.registroEnSistema();
    }

    public void registroEnfermero(Enfermero e) {
        if (this.noEnfermeros >= this.listaEnfermeros.length) {
            System.out.println("Error: ya no hay espacio para más enfermeros en el sistema.");
            return;
        }
        this.listaEnfermeros[this.noEnfermeros] = e;
        this.noEnfermeros++;
        e.registroEnSistema();
    }

    public void registroPaciente(Paciente p) {
        if (this.noPacientes >= this.listaPacientesTotales.length) {
            System.out.println("Error: ya no hay espacio para más pacientes en el sistema.");
            return;
        }
        this.listaPacientesTotales[this.noPacientes] = p;
        this.noPacientes++;
        p.registroEnSistema();
    }

    // MÉTODOS SOBRECARGADOS
    public void asignarPaciente(Medico m, Paciente p) {
        if (!m.especialidad.equals(p.especialidadAtencion)) {
            System.out.println("Error: La especialidad no coincide.");
        } else if (m.tienePaciente(p)) {
            System.out.println("Error: El paciente ya está asignado a este médico.");
        } else if (m.noPacientes >= 10) {
            System.out.println("Error: El médico ya tiene 10 pacientes.");
        } else {
            m.listaPacientes[m.noPacientes] = p;
            m.noPacientes++;
            System.out.println("Paciente asignado al médico " + m.nombre);
        }
    }

    public void asignarPaciente(Enfermero e, Paciente p) {
        if (!e.especialidad.equals(p.especialidadAtencion)) {
            System.out.println("Error: La especialidad no coincide.");
        } else if (e.tienePaciente(p)) {
            System.out.println("Error: El paciente ya está asignado a este enfermero.");
        } else if (e.noPacientes >= 3) {
            System.out.println("Error: El enfermero ya tiene 3 pacientes.");
        } else {
            e.listaPacientes[e.noPacientes] = p;
            e.noPacientes++;
            System.out.println("Paciente asignado al enfermero " + e.nombre);
        }
    }

    // Búsquedas básicas usando for (Práctica 3)
    public Medico buscarMedicoPorNombre(String nombre) {
        for (int i = 0; i < this.noMedicos; i++) {
            if (this.listaMedicos[i].nombre.equals(nombre)) {
                return this.listaMedicos[i];
            }
        }
        return null;
    }

    public Enfermero buscarEnfermeroPorNombre(String nombre) {
        for (int i = 0; i < this.noEnfermeros; i++) {
            if (this.listaEnfermeros[i].nombre.equals(nombre)) {
                return this.listaEnfermeros[i];
            }
        }
        return null;
    }

    public Paciente buscarPacientePorNombre(String nombre) {
        for (int i = 0; i < this.noPacientes; i++) {
            if (this.listaPacientesTotales[i].nombre.equals(nombre)) {
                return this.listaPacientesTotales[i];
            }
        }
        return null;
    }
}
