package spotifypremium;

import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author Layonel Leal y Carol Barahona
 */
public class SpotifyPremium {
    
    static public ConcurrentSkipListMap<Music, Music> ordenar ( ConcurrentSkipListMap<Music, Music> currentList, int keyOrden){
        ConcurrentSkipListMap<Music, Music> reorderList = new ConcurrentSkipListMap<>((Music1, Music2) -> {
            switch (keyOrden) {
                case 0 -> {
                    String name1 = Music1.getNombre();
                    String name2 = Music2.getNombre();
                    int MusicOrder = name1.compareTo(name2);
                    return MusicOrder;
                }
                case 1-> {
                    String autor1 = Music1.getAutor();
                    String autor2 = Music2.getAutor();
                    int MusicOrder = autor1.compareTo(autor2); 
                    return MusicOrder;
                }
                case 2 -> {
                    Integer year1 = Music1.getYear();
                    Integer year2 = Music2.getYear();
                    int MusicOrder = year1.compareTo(year2); 
                    return MusicOrder;
                }
                case 3 -> {
                    Long reprod1 = Music1.getReprod();
                    Long reprod2 = Music2.getReprod();
                    int MusicOrder = reprod1.compareTo(reprod2); //0 si son el mismo -2 si name1 va antes y me devulve un 2 si va despues
                    return MusicOrder;
                }
                default -> {
                    System.out.println("Hubo un error al organizar el mapa.");
                    return 0;
                }
            }
        });
        
        for (Music value: currentList.values()){
            reorderList.put(value, value);
        }
        
        return reorderList;
    }

    public static void main(String[] args) {
        //Definir variables
        int input = -1; //variable para la opción que ingresa el usuario
        int keyOrden; //variable para guardar el dato por el que se ordenará el mapa
        int order; // ascendente o descendente
        Scanner sc = new Scanner(System.in); //leer datos por teclado
        
        //definir el ListMap donde se guardarán las canciones
        ConcurrentSkipListMap<Music, Music> like_musics = new ConcurrentSkipListMap<>((Music1, Music2)->{
            String name1 = Music1.getNombre(); //obtener el nombre
            String name2 = Music2.getNombre();
            int MusicOrder = name1.compareTo(name2); // retorna un entero que indica si el texto va a antes o despues.
            return MusicOrder;
        });
        
        //Canciones de la base de datos
        like_musics.put(new Music("Morocha", "Milo J", 2023, 21000000), new Music("Morocha", "Milo J", 2023, 21000000));
        like_musics.put(new Music("El Bolero", "Yami Safdie", 2023, 26000000), new Music("El Bolero", "Yami Safdie", 2023, 26000000));
        like_musics.put(new Music("Tres para tres", "Soda Stereo", 2016, 6500000), new Music("Tres para tres", "Soda Stereo", 2016, 6500000));
        like_musics.put(new Music("The Reason", "Hoobastank", 2010, 10590000), new Music("The Reason", "Hoobastank", 2010, 10590000));
        like_musics.put(new Music("yellow", "Coldplay", 2012, 136000000), new Music("yellow", "Coldplay", 2012, 136000000));
        
        do{
            System.out.println("|***********************************|");
            System.out.println("|*********** NotSPOTIFY ************|");
            System.out.println("|***********************************|");
            System.out.println("0) Salir.");
            System.out.println("1) Agregar Cancion a Favoritos.");
            System.out.println("2) Mirar Listado de Favoritos.");
            System.out.println("-------------------------------------");
            System.out.println("Elije la opcion a realizar:");
            input = sc.nextInt();
            
            switch (input) {
                case 0 -> {
                    System.out.println("Hasta pronto...");
                }
                case 1 ->{
                    System.out.println("Ingresa el nombre de la cancion: ");                    
                    String musicName = sc.next();
                    System.out.println("Ingresa el autor de la cancion: ");
                    String musicAutor = sc.next();
                    System.out.println("Ingresa el anio de la cancion: ");
                    int musicYear = sc.nextInt();
                    System.out.println("Ingresa la cantidad de reproducciones de la cancion: ");
                    long musicReprod = sc.nextLong();
                    
                    Music newMusic = new Music(musicName, musicAutor, musicYear, musicReprod);
                    like_musics.put(newMusic, newMusic);
                }
                case 2->{
                    System.out.println("--------------------------------------------------");
                    System.out.println("¿Por cual caracteristica desea organizar el listado de canciones?");
                    System.out.println("1) Por Nombre");
                    System.out.println("2) Por Autor");
                    System.out.println("3) Por Anio");
                    System.out.println("4) Por Reproducciones");
                    System.out.println("--------------------------------------------------");
                    
                    keyOrden = sc.nextInt() - 1;
                    ConcurrentSkipListMap<Music, Music> list_music_order = ordenar(like_musics, keyOrden);
                    
                    System.out.println("--------------------------------------------------");
                    System.out.println("De que forma desea ver la informacion?");
                    System.out.println("1) Ascendente.");
                    System.out.println("2) Descendente.");
                    System.out.println("--------------------------------------------------");
                    
                    order = sc.nextInt();
                    
                    System.out.println("\n\n####### LISTADO DE CANCIONES ########\n\n");
                    
                    if (order == 1){
                        for (Music value: list_music_order.values()){
                            value.printMusic();
                        }
                    }else{
                        for (Music value: list_music_order.descendingMap().values()){
                            value.printMusic();
                        }
                    }
                    
                    System.out.println("\n");
                }
                default -> System.out.println("Error la opcion es invalida.");
            }

        }while(input != 0);
    }
    
}
