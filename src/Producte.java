public class Producte {

    private String nomProducte;
    private String marcaProducte;
    private  double preuProducte;
    private int stock;

    public Producte(String nomProducte, String marcaProducte, double preuProducte, int stock) {
        this.nomProducte = nomProducte;
        this.marcaProducte = marcaProducte;
        this.preuProducte = preuProducte;
        this.stock = stock;
    }

    public String getNomProducte() {
        return nomProducte;
    }

    public void setNomProducte(String nomProducte) {
        this.nomProducte = nomProducte;
    }

    public String getMarcaProducte() {
        return marcaProducte;
    }

    public void setMarcaProducte(String marcaProducte) {
        this.marcaProducte = marcaProducte;
    }

    public double getPreuProducte() {
        return preuProducte;
    }

    public void setPreuProducte(double preuProducte) {
        this.preuProducte = preuProducte;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    //Metode obsolet
    @SuppressWarnings("metode obsolet")
    @Deprecated
    public double retornarPreu(){
        return this.preuProducte;
    }
    @Override
    public String toString() {
        return "Producte{" +
                "nomProducte='" + nomProducte + '\'' +
                ", marcaProducte='" + marcaProducte + '\'' +
                ", preuProducte=" + preuProducte +
                ", stock=" + stock +
                '}';
    }
}
