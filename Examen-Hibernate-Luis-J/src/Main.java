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
    }
}
