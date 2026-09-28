import java.util.Scanner;

public class PruebaHospital {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sistema sistema = new Sistema("Hospital General POO");
        int opcion;

        do {
            System.out.println("\n===== MENU HOSPITAL =====");
            System.out.println("1. Registrar medico");
            System.out.println("2. Registrar enfermero");
            System.out.println("3. Registrar paciente");
            System.out.println("4. Asignar paciente a medico");
            System.out.println("5. Asignar paciente a enfermero");
            System.out.println("6. Ver lista de pacientes de un medico");
            System.out.println("7. Ver lista de pacientes de un enfermero");
            System.out.println("8. Paciente: Ver estado de tratamiento");
            System.out.println("9. Paciente: Solicitar consulta");
            System.out.println("10. Medico: Solicitar paciente y dar consulta");
            System.out.println("11. Medico/Enfermero: Dar tratamiento");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del medico: ");
                    String nombreMed = sc.nextLine();
                    System.out.print("Cedula: ");
                    String cedulaMed = sc.nextLine();
                    System.out.print("Especialidad: ");
                    String especialidadMed = sc.nextLine();
                    sistema.registroMedico(new Medico(nombreMed, cedulaMed, especialidadMed));
                    break;

                case 2:
                    System.out.print("Nombre del enfermero: ");
                    String nombreEnf = sc.nextLine();
                    System.out.print("Cedula: ");
                    String cedulaEnf = sc.nextLine();
                    System.out.print("Especialidad: ");
                    String especialidadEnf = sc.nextLine();
                    sistema.registroEnfermero(new Enfermero(nombreEnf, cedulaEnf, especialidadEnf));
                    break;

                case 3:
                    System.out.print("Nombre del paciente: ");
                    String nombrePac = sc.nextLine();
                    System.out.print("Apellidos del paciente: ");
                    String apellidosPac = sc.nextLine();
                    System.out.print("Especialidad que necesita: ");
                    String especialidadPac = sc.nextLine();
                    sistema.registroPaciente(new Paciente(nombrePac, apellidosPac, especialidadPac));
                    break;

                case 4:
                    System.out.print("Nombre del medico: ");
                    Medico medicoEncontrado = sistema.buscarMedicoPorNombre(sc.nextLine());
                    System.out.print("Nombre del paciente a asignar: ");
                    Paciente pacienteParaMedico = sistema.buscarPacientePorNombre(sc.nextLine());

                    if (medicoEncontrado != null && pacienteParaMedico != null) {
                        sistema.asignarPaciente(medicoEncontrado, pacienteParaMedico);
                    } else {
                        System.out.println("No se encontro al medico o al paciente.");
                    }
                    break;

                case 5:
                    System.out.print("Nombre del enfermero: ");
                    Enfermero enfermeroEncontrado = sistema.buscarEnfermeroPorNombre(sc.nextLine());
                    System.out.print("Nombre del paciente a asignar: ");
                    Paciente pacienteParaEnf = sistema.buscarPacientePorNombre(sc.nextLine());

                    if (enfermeroEncontrado != null && pacienteParaEnf != null) {
                        sistema.asignarPaciente(enfermeroEncontrado, pacienteParaEnf);
                    } else {
                        System.out.println("No se encontro al enfermero o al paciente.");
                    }
                    break;

                case 6:
                    System.out.print("Nombre del medico: ");
                    Medico medicoElegido = sistema.buscarMedicoPorNombre(sc.nextLine());
                    if (medicoElegido != null) {
                        medicoElegido.verListaPacientes();
                    } else {
                        System.out.println("No se encontro ese medico.");
                    }
                    break;

                case 7:
                    System.out.print("Nombre del enfermero: ");
                    Enfermero enfermeroElegido = sistema.buscarEnfermeroPorNombre(sc.nextLine());
                    if (enfermeroElegido != null) {
                        enfermeroElegido.verListaPacientes();
                    } else {
                        System.out.println("No se encontro ese enfermero.");
                    }
                    break;

                case 8:
                    // Comportamiento del Paciente: verTratamiento
                    System.out.print("Nombre del paciente: ");
                    Paciente p8 = sistema.buscarPacientePorNombre(sc.nextLine());
                    if (p8 != null) {
                        p8.verTratamiento();
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;

                case 9:
                    // Comportamiento del Paciente: solicitarConsulta
                    System.out.print("Nombre del paciente: ");
                    Paciente p9 = sistema.buscarPacientePorNombre(sc.nextLine());
                    if (p9 != null) {
                        p9.solicitarConsulta();
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;

                case 10:
                    // Comportamiento del Médico: solicitarPaciente y darConsulta
                    System.out.print("Nombre del medico: ");
                    Medico m10 = sistema.buscarMedicoPorNombre(sc.nextLine());
                    System.out.print("Nombre del paciente: ");
                    Paciente p10 = sistema.buscarPacientePorNombre(sc.nextLine());

                    if (m10 != null && p10 != null) {
                        // Primero se solicita (asigna) al paciente; solo si queda asignado se da la consulta
                        if (m10.solicitarPaciente(sistema, p10)) {
                            m10.darConsulta(p10); // Esto cambia el estado a 1 (En consulta)
                        }
                    } else {
                        System.out.println("Datos incorrectos.");
                    }
                    break;

                case 11:
                    // Comportamiento de Médico/Enfermero: darTratamiento
                    System.out.print("¿Quien dara el tratamiento? (1=Medico, 2=Enfermero): ");
                    int tipo = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer
                    System.out.print("Nombre del profesional: ");
                    String nombreProf = sc.nextLine();
                    System.out.print("Nombre del paciente: ");
                    Paciente p11 = sistema.buscarPacientePorNombre(sc.nextLine());

                    if (p11 != null) {
                        if (tipo == 1) {
                            Medico m11 = sistema.buscarMedicoPorNombre(nombreProf);
                            if (m11 != null) {
                                m11.darTratamiento(p11); // Cambia estado a 2
                            } else {
                                System.out.println("Medico no encontrado.");
                            }
                        } else if (tipo == 2) {
                            Enfermero e11 = sistema.buscarEnfermeroPorNombre(nombreProf);
                            if (e11 != null) {
                                e11.darTratamiento(p11); // Cambia estado a 2
                            } else {
                                System.out.println("Enfermero no encontrado.");
                            }
                        } else {
                            System.out.println("Opcion de profesional no valida.");
                        }
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del simulador...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
