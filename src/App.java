import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws NoProducte {

        ArrayList<Maquina> llistaMaquines = new ArrayList<Maquina>();
        int opcions, indexMaquina = -1;
        String nomMaquina, nomProducte, marcaProducte;


        do {
            opcions = menuEntrada();

            if (opcions == 1) { //Sortir programa
                System.out.println("Has sortit del programa.");

            } else {
                crearMaquina(llistaMaquines);
                switch (opcions) {

                    case 2://Afegir producte
                        nomMaquina = introInfo("Introdueix nom de la maquina: ");
                        indexMaquina = buscarMaquina(nomMaquina, llistaMaquines);

                        if (indexMaquina == -1) {
                            System.out.println("Maquina no existeix.");
                        } else {
                            crearProducte(indexMaquina,llistaMaquines);
                            modificarStock(indexMaquina,llistaMaquines );
                        }
                        break;

                    case 3: //Mostrar productes d'una màquina

                        nomMaquina = introInfo("Introdueix nom de la maquina: ");
                        indexMaquina = buscarMaquina(nomMaquina, llistaMaquines);
                        try {
                            mostraProductesMaquina(indexMaquina, llistaMaquines);
                        }catch (Exception error){
                            System.out.println(error.getMessage());
                        }
                        break;

                    case 4: //Mostrar producte d'una màquina
                        nomMaquina = introInfo("Introdueix nom de la maquina: ");
                        indexMaquina = buscarMaquina(nomMaquina, llistaMaquines);
                        if(indexMaquina==-1){
                            System.out.println("Màquina no existeix");
                        }else {
                            marcaProducte = introInfo("Introdueix marca que vols buscar en el carro: ");
                            llistaMaquines.get(indexMaquina).producteMarca(marcaProducte);
                        }
                        break;

                    case 5: //Mostrar stock d'un producte de la màquina

                        break;
                    case 6: //Metode obsolet
                        nomMaquina = introInfo("Introdueix nom de la maquina: ");
                        indexMaquina = buscarMaquina(nomMaquina, llistaMaquines);
                        //llistaMaquines.get(indexMaquina).getLlistaProductes().get(indexProducte).retornarPreu();
                        break;
                    case 7: //Metode mostra total de stock
                        mostrarStockTotal(llistaMaquines);
                        break;
                }
            }
        } while (opcions != 1);

    }
    //Metode mostrar stock total
    static void mostrarStockTotal(ArrayList<Maquina> llistaMaquines){
       int total = 0;
        for (int i=0; i<llistaMaquines.size(); i++){
            total += llistaMaquines.get(i).stockTotal();
        }
        System.out.println(total);
    }

    //Mostrar productes d'una màquina si no hi ha productes salta exception
    static void mostraProductesMaquina(int indexMaquina, ArrayList<Maquina> llistaMaquines) throws NoProducte {

        if(llistaMaquines.get(indexMaquina).getLlistaProductes().isEmpty()){
            throw new NoProducte("La màquina no té productes disponibles.");
        }else{
            System.out.println(llistaMaquines.get(indexMaquina).getLlistaProductes());
        }
    }
    //Metode menu info entrada
    static int menuEntrada() {
        int opcions = introInfoInt("Escull:\n1.Sortir de l'aplicació"
                + "\n2.Afegir producte a l'estock"
                + "\n3.Mostrar productes d'una màquina"
                + "\n4.Mostrar marca d'un producte d'una màquina"
                + "\n5.Mostrar stock d'un producte de la màquina"
                + "\n6.Metode obsolet"
                + "\n7.Metode mostrar total de stock de les màquines"
                );
        return opcions;
    }
    //Metode buscar màquina
    static int buscarMaquina(String nomMaquina, ArrayList<Maquina> llistaMaquines){
        int i=0;
        int indexMaquina=-1;
        boolean encontrado =false;

        while( i<llistaMaquines.size() && encontrado== false){
            if(llistaMaquines.get(i).getNomMaquina().equalsIgnoreCase(nomMaquina)){
                indexMaquina = i;
                encontrado = true;
            }
            i++;
        }
        return indexMaquina;
    }
    //Metode modificar stock
    static void modificarStock (int indexMaquina, ArrayList<Maquina> llistaMaquines) {
        int quantitatProducte, indexProducte;
        double preuProducte;
        String nomProducte, marcaProducte;

        nomProducte = introInfo("Introdueix nom del producte: ");

        //Comprovem que el producte existeix a la màquina
        indexProducte = llistaMaquines.get(indexMaquina).buscarProducte(nomProducte);

        if(indexProducte!=-1){ // si el producte existeix introduïm producte
                //Comprovem stock producte
                llistaMaquines.get(indexMaquina).controlStock(nomProducte);
        }
    }
    //Metode crear Producte
    static void crearProducte (int indexMaquina, ArrayList<Maquina> llistaMaquines) {

        //Afegim producte
        Producte producte1= new Producte("patates","matutano",1,6);
        Producte producte2= new Producte("sandwich","fresc",1,0);
        Producte producte3 = new Producte("cafe","cafe",1,10);
        Producte producte4= new Producte("xocolata","nestle",1,9);
        llistaMaquines.get(0).afegirProducte(producte1);
        llistaMaquines.get(1).afegirProducte(producte2);
        llistaMaquines.get(2).afegirProducte(producte3);
        llistaMaquines.get(2).afegirProducte(producte4);
    }
    //Metode crear màquina
    static void crearMaquina (ArrayList<Maquina> llistaMaquines ) {
        llistaMaquines.add(new Maquina("maquina1"));
        llistaMaquines.add(new Maquina("maquina2"));
        llistaMaquines.add(new Maquina("maquina3"));


    }
    /////ENTRADA DE DATOS

    //Metodes introduïr informació
    static String introInfo(String missatge) {
        Scanner input = new Scanner(System.in);
        System.out.println(missatge);
        return input.nextLine();//Retorna String
    }

    static int introInfoInt(String missatge) {
        Scanner input = new Scanner(System.in);
        System.out.println(missatge);
        return input.nextInt();//Retorna Int
    }
    static double introInfoDouble(String missatge) {
        Scanner input = new Scanner(System.in);
        System.out.println(missatge);
        return input.nextDouble();//Retorna double
    }

}
