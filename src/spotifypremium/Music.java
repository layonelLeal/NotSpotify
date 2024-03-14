package spotifypremium;

/**
 *
 * @author layonel leal y Carol Barahona
 */
class Music {
    private String nombre;
    private String autor;
    private int year;
    private long reprod;
    
    public Music(String name, String autor, int year, long reprod){
        this.nombre = name;
        this.autor = autor;
        this.year = year;
        this.reprod = reprod;
    }
    
    public Music(String name) {
        this.nombre = name;
    }
    
    public void printMusic(){
        System.out.println("| "+ nombre + "      \t| " + autor +
                "      \t| " + reprod + "        \t| " + year);
    }
    
    public String getNombre(){
        return this.nombre.toLowerCase().replace(" ", "");
    }
    
    public String getAutor(){
        return this.autor.toLowerCase().replace(" ", "");
    }
    
    public int getYear(){
        return this.year;
    }
    
    public long getReprod(){
        return this.reprod;
    }
}
