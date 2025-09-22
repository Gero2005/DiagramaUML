package Diagrama;
import net.sourceforge.plantuml.SourceStringReader;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class VerDiagramaUML {
    public static void main(String[] args) {
        try {
            String source = "@startuml\n" +
                    "skinparam groupInheritance 2\n" +
                    "\n" +
                    "package \"Usuarios\" {\n" +
                    "  class Usuario {\n" +
                    "    - login: String\n" +
                    "    - password: String\n" +
                    "    - saldo: double\n" +
                    "    + comprarTiquete()\n" +
                    "    + transferirTiquete()\n" +
                    "  }\n" +
                    "\n" +
                    "  class Comprador { }\n" +
                    "\n" +
                    "  class Organizador {\n" +
                    "    + crearEvento()\n" +
                    "    + configurarLocalidades()\n" +
                    "    + generarReporteFinanciero()\n" +
                    "    + crearOferta()\n" +
                    "  }\n" +
                    "\n" +
                    "  class Administrador {\n" +
                    "    + establecerTarifas()\n" +
                    "    + aprobarVenue()\n" +
                    "    + cancelarEvento()\n" +
                    "    + procesarReembolso()\n" +
                    "  }\n" +
                    "\n" +
                    "  Usuario <|-- Comprador\n" +
                    "  Usuario <|-- Organizador\n" +
                    "  Usuario <|-- Administrador\n" +
                    "}\n" +
                    "\n" +
                    "package \"Tiquetes\" {\n" +
                    "  class Ticket {\n" +
                    "    - id: String\n" +
                    "    - precioBase: double\n" +
                    "    - cargoServicio: double\n" +
                    "    - cuotaFija: double\n" +
                    "    - fechaEvento: Date\n" +
                    "    - transferible: boolean\n" +
                    "    + calcularPrecioFinal()\n" +
                    "  }\n" +
                    "\n" +
                    "  class TicketSimple {\n" +
                    "    - localidad: Localidad\n" +
                    "  }\n" +
                    "\n" +
                    "  class TicketNumerado {\n" +
                    "    - numeroAsiento: String\n" +
                    "  }\n" +
                    "\n" +
                    "  class TicketMultiple {\n" +
                    "    - cantidadEntradas: int\n" +
                    "    - ticketsIncluidos: List<Ticket>\n" +
                    "  }\n" +
                    "\n" +
                    "  class TicketDeluxe {\n" +
                    "    - beneficios: String\n" +
                    "  }\n" +
                    "\n" +
                    "  Ticket <|-- TicketSimple\n" +
                    "  Ticket <|-- TicketNumerado\n" +
                    "  Ticket <|-- TicketMultiple\n" +
                    "  Ticket <|-- TicketDeluxe\n" +
                    "}\n" +
                    "\n" +
                    "package \"Eventos\" {\n" +
                    "  class Evento {\n" +
                    "    - nombre: String\n" +
                    "    - fecha: Date\n" +
                    "    - venue: Venue\n" +
                    "    - localidades: List<Localidad>\n" +
                    "    + estaActivo(): boolean\n" +
                    "  }\n" +
                    "\n" +
                    "  class Localidad {\n" +
                    "    - nombre: String\n" +
                    "    - precio: double\n" +
                    "    - capacidad: int\n" +
                    "    - numerada: boolean\n" +
                    "    - asientosDisponibles: List<String>\n" +
                    "  }\n" +
                    "\n" +
                    "  class Escenario {\n" +
                    "    - nombre: String\n" +
                    "    - ubicacion: String\n" +
                    "    - capacidadMaxima: int\n" +
                    "    - restricciones: String\n" +
                    "    - aprobado: boolean\n" +
                    "  }\n" +
                    "\n" +
                    "  class Oferta {\n" +
                    "    - descuento: double\n" +
                    "    - fechaInicio: Date\n" +
                    "    - fechaFin: Date\n" +
                    "    - localidad: Localidad\n" +
                    "  }\n" +
                    "}\n" +
                    "\n" +
                    "package \"Transacciones\" {\n" +
                    "  class Transaccion {\n" +
                    "    - id: String\n" +
                    "    - fecha: Date\n" +
                    "    - usuario: Usuario\n" +
                    "    - tickets: List<Ticket>\n" +
                    "    - montoTotal: double\n" +
                    "  }\n" +
                    "\n" +
                    "  class Reembolso {\n" +
                    "    - ticket: Ticket\n" +
                    "    - usuario: Usuario\n" +
                    "    - monto: double\n" +
                    "    - fecha: Date\n" +
                    "    - motivo: String\n" +
                    "  }\n" +
                    "}\n" +
                    "\n" +
                    "Organizador \"1\" -- \"*\" Evento : crea >\n" +
                    "Evento \"1\" -- \"1\" Escenario : se realiza en >\n" +
                    "Evento \"1\" -- \"*\" Localidad : tiene >\n" +
                    "Localidad \"1\" -- \"*\" TicketSimple : contiene >\n" +
                    "TicketMultiple \"1\" -- \"*\" Ticket : incluye >\n" +
                    "Transaccion \"1\" -- \"*\" Ticket : incluye >\n" +
                    "Transaccion \"1\" -- \"1\" Usuario : realizada por >\n" +
                    "Oferta \"1\" -- \"1\" Localidad : aplica a >\n" +
                    "Reembolso \"1\" -- \"1\" Ticket : para >\n" +
                    "Reembolso \"1\" -- \"1\" Usuario : beneficia a >\n" +
                    "Administrador \"1\" -- \"*\" Reembolso : autoriza >\n" +
                    "Administrador \"1\" -- \"*\" Escenario : aprueba >\n" +
                    "\n" +
                    "note right of TicketDeluxe\n" +
                    "  No es transferible\n" +
                    "end note\n" +
                    "\n" +
                    "note right of TicketMultiple\n" +
                    "  Transferible solo como paquete completo\n" +
                    "end note\n" +
                    "@enduml";

  
            File tempFile = File.createTempFile("uml-diagrama", ".png");
            try (OutputStream png = new FileOutputStream(tempFile)) {
                SourceStringReader reader = new SourceStringReader(source);
                reader.outputImage(png);
            }

            ImageIcon icon = new ImageIcon(tempFile.getAbsolutePath());
            JLabel label = new JLabel(icon);
            JScrollPane scrollPane = new JScrollPane(label);

            JFrame frame = new JFrame("Diagrama UML");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
            frame.setSize(1000, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
