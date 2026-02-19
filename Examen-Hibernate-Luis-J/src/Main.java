import models.Pedido;
import org.hibernate.Session;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int idBuscado = teclado.nextInt();

        long idPedido = 1L;
        if (args.length > 0) {
            try {
                idPedido = idBuscado;
            } catch (NumberFormatException e) {
                System.out.println("ID invalido. Usa un numero entero. Ejemplo: 1");
                return;
            }
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Pedido pedido = session.get(Pedido.class, idPedido);

            if (pedido == null) {
                System.out.println("No existe un pedido con id " + idPedido);
                return;
            }

            System.out.println("Pedido leido:");
            System.out.println("ID: " + pedido.getId());
            System.out.println("Cliente: " + pedido.getClienteEmail());
            System.out.println("Fecha: " + pedido.getFecha());
            System.out.println("Estado: " + pedido.getEstado());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // DELETE - Eliminar una línea
            System.out.println("5. DELETE - Eliminar línea ID=" + primeraLineaId + ":");
            eliminarLineaPedido(primeraLineaId);
            System.out.println("✓ Línea eliminada\n");

            // LIST - Final
            System.out.println("6. LIST - Líneas restantes:");
            List<PedidoLinea> lineasFinales = listarLineasPorPedido(2L);
            if (!lineasFinales.isEmpty()) {
                for (PedidoLinea linea : lineasFinales) {
                    System.out.println("   [ID: " + linea.getId() + "] " + linea.getProducto() +
                            " | Cantidad: " + linea.getCantidad());
                }
            } else {
                System.out.println("   No hay líneas para este pedido");
            }
        }
        System.out.println("1. INSERT - Crear nuevas líneas para Pedido ID=2:");
        crearPedidoLinea(2L, "Monitor 27\"", 2, new BigDecimal("299.99"));
        crearPedidoLinea(2L, "Teclado Mecánico", 1, new BigDecimal("129.99"));
        crearPedidoLinea(2L, "Mouse Inalámbrico", 3, new BigDecimal("49.99"));
        System.out.println("✓ Líneas creadas\n");
    }
}
