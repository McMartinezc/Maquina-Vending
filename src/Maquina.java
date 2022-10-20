import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class Maquina {

   private ArrayList<Producte> llistaProductes;
    private String nomMaquina;
    private final int MAX_STOCK = 10;

    public Maquina(String nomMaquina) {
        this.nomMaquina = nomMaquina;
        llistaProductes = new ArrayList<Producte>();
    }

    public ArrayList<Producte> getLlistaProductes() {
        return llistaProductes;
    }

    public void setLlistaProducte(ArrayList<Producte> llistaProductes) {
        this.llistaProductes = llistaProductes;
    }

    public String getNomMaquina() {
        return nomMaquina;
    }

    public void setNomMaquina(String nomMaquina) {
        this.nomMaquina = nomMaquina;
    }
    //Metode afegir producte a la maquina
    public void afegirProducte(Producte producte){
        llistaProductes.add(producte);
    }

    //Metode control stock de producte d'una màquina
    public void controlStock(String nomProducte, String marcaProducte){
        int indexProducte=-1, i=0;
        int stock, stockInicial, stockFinal;

        indexProducte = buscarProducte(nomProducte, marcaProducte);

            //Si existeix comprovem stock i el modifiquem
            if (llistaProductes.get(indexProducte).getStock() < MAX_STOCK) {
                stockInicial = llistaProductes.get(indexProducte).getStock();
                System.out.println("Estoc actual de " + nomProducte + " " + stockInicial);
                stock = MAX_STOCK - llistaProductes.get(indexProducte).getStock();
                System.out.println("Necessites omplir l'estoc del producte: " + stock);
                stockFinal = stock + stockInicial;
                llistaProductes.get(indexProducte).setStock(stockFinal);
                System.out.println("Estoc del producte actualitzat " + llistaProductes.get(indexProducte).getStock());
            } else if (llistaProductes.get(indexProducte).getStock() == MAX_STOCK) { //Si el stock està ple no fem res
                System.out.println("Estoc ple. Màxim " +MAX_STOCK+ " productes");
            }
    }

    //Metode buscar producte
    public int buscarProducte(String nomProducte, String marcaProducte){
        int indexProducte =-1;
        int i =0;
        boolean encontrado = false;

        while((i < llistaProductes.size()) && (encontrado == false)){
            if (llistaProductes.get(i).getNomProducte().equalsIgnoreCase(nomProducte) && marcaProducte.equalsIgnoreCase(llistaProductes.get(i).getMarcaProducte())){
                indexProducte = i;
                encontrado = true;
            }
            i++;
        }
        if(!encontrado){
            System.out.println("Producte no existeix.");
        }
        return indexProducte;
    }

    //Metode buscar per marca amb lambda e imprimeix el producte trobat
    public void producteMarca(String marcaProducte){
       /* Mostra productes de la mateixa marca
       Stream<Producte> producteStream = llistaProductes.stream()
                .filter(producte -> producte.getMarcaProducte().equalsIgnoreCase(marcaProducte));
        producteStream.forEach(producte -> System.out.println(producte.toString()));*/
        Optional<Producte> producteOptional = llistaProductes.stream()
                        .filter(producte -> producte.getMarcaProducte().equalsIgnoreCase(marcaProducte)).findFirst();
        if (producteOptional.isEmpty()) {
            System.out.println("No hi ha productes d'aquesta marca");
        } else {
            System.out.println(producteOptional.get().toString());
        }

    }
    //Mostra el numero de producte d'una marca concreta amb una lambda.
    public void quantitatProducteMarca(String marcaProducte){
        long quantitat = llistaProductes.stream()
                .filter(productes -> productes.getMarcaProducte().equalsIgnoreCase(marcaProducte))
                .count();
        System.out.println("La quantitat de productes d'aquesta marca són: " +quantitat);
    }

    //Metode total stock
    public int stockTotal(){
        int stockTotal=0;

        for(int i=0; i<llistaProductes.size(); i++){
            stockTotal += llistaProductes.get(i).getStock() ;
        }
        return stockTotal;
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "llistaProductes=" + llistaProductes +
                ", nomMaquina='" + nomMaquina + '\'' +
                ", MAX_STOCK=" + MAX_STOCK +
                '}';
    }
}
