package baserelacionala;


public class BaserelacionalA {
public static String codigot;
    
    public static void main(String[] args) {
      
        BaseDatos bd = new BaseDatos();
       codigot= "hola";
        bd.connect();
        Product p1 = new Product("p1", "parafusos", 3.0);
        bd.insireProducto(p1);

        bd.connect();
        Product p2 = new Product("p2", "cravos", 4.0);
        bd.insireProducto(p2);

        bd.connect();
        Product p3 = new Product("p3", "tachas", 6.0);
        bd.insireProducto(p3);
        //bd.disconnect();
        bd.actualizaPre("p2", 5.0);
        System.out.println(bd.mostrarProductos());
        System.out.println(bd.amosarFila("p3"));
        bd.amosarFila2("p2");
    }

}
