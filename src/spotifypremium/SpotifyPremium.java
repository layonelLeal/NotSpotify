package spotifypremium;

import java.util.Scanner;
import java.util.concurrent.ConcurrentNavigableMap;
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
                    if (MusicOrder == 0){
                        String name1 = Music1.getNombre();
                        String name2 = Music2.getNombre();
                        MusicOrder = name1.compareTo(name2);
                        return MusicOrder; 
                    }
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
        
        //for (Music value: currentList.values()){
          //  reorderList.put(value, value);
        //}
        
        reorderList.putAll(currentList);
        
        return reorderList;
    }

    public static void main(String[] args) {
        //Definir variables
        int input; //variable para la opción que ingresa el usuario
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
        like_musics.put(new Music("Morocha", "Milo J", 2023, 21000000), new Music("Morocha  ", "Milo J  ", 2023, 21000000));
        like_musics.put(new Music("El Bolero", "Yami Safdie", 2023, 26000000), new Music("El Bolero", "Yami Safdie", 2023, 26000000));
        like_musics.put(new Music("Tres para tres", "Soda Stereo", 2016, 6500000), new Music("Tres para tres", "Soda Stereo", 2016, 6500000));
        like_musics.put(new Music("The Reason", "Hoobastank", 2010, 10590000), new Music("The Reason", "Hoobastank", 2010, 10590000));
        like_musics.put(new Music("yellow", "Coldplay", 2012, 1036000000), new Music("yellow  ", "Coldplay", 2012, 136000000));
        like_musics.put(new Music("Bonsai", "Alan Sutton", 2023, 6000000), new Music("Bonsai  ", "Alan Sutton", 2023, 6000000));
        like_musics.put(new Music("Human nature", "Michael Jackson", 1982, 21000000), new Music("Human nature", "Michael Jackson", 1983, 21000000));
        like_musics.put(new Music("Sol   ", "Willian", 2022, 10000000), new Music("Sol      ", "Willian ", 2022, 10000000));
        like_musics.put(new Music("Carrie", "Europe", 1986, 22900000), new Music("Carrie  ", "Europe  ", 1986, 22900000));
        like_musics.put(new Music("Alguien como tu", "Josean log", 2020, 25000000), new Music("Alguien como tu", "Josean Log", 2020, 25000000));
        
        do{
            System.out.println("|***********************************|");
            System.out.println("|*********** NotSPOTIFY ************|");
            System.out.println("|***********************************|");
            System.out.println("0) Salir.");
            System.out.println("1) Agregar Cancion a Favoritos.");
            System.out.println("2) Mirar Listado de Favoritos.");
            System.out.println("3) Buscar una canción en Favoritos.");
            System.out.println("4) Limitar Listado a un rango");
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
                    System.out.println("5) Por un rango de letras (A - M)");
                    System.out.println("--------------------------------------------------");
                    
                    keyOrden = sc.nextInt() - 1;
                    
                    ConcurrentSkipListMap<Music, Music> list_music_order = ordenar(like_musics, keyOrden);
                    
                    System.out.println("--------------------------------------------------");
                    System.out.println("De que forma desea ver la informacion?");
                    System.out.println("1) Ascendente.");
                    System.out.println("2) Descendente.");
                    System.out.println("--------------------------------------------------");
                    
                    order = sc.nextInt();
                    
                    System.out.println("\n\n\t\t\t####### LISTADO DE CANCIONES ########");
                    System.out.println("|--------------------------------------------------------------------------------|");
                    System.out.println("| NOMBRE \t\t| AUTOR \t\t| REPRODUCCIONES \t| ANIO \t |");
                    System.out.println("|--------------------------------------------------------------------------------|");
                    
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
                    break;
                }
                case 3->{
                    System.out.println("Ingresa el nombre de la canción que deseas buscar: ");
                    String searchName = sc.next();
                    Music resultName = like_musics.ceilingEntry(new Music(searchName)).getValue();
                    System.out.println("\n\n\t\t\t####### Cancion Encontrada ########");
                    System.out.println("|--------------------------------------------------------------------------------|");
                    System.out.println("| NOMBRE \t\t| AUTOR \t\t| REPRODUCCIONES \t| ANIO \t |");
                    System.out.println("|--------------------------------------------------------------------------------|");
                    resultName.printMusic();
                }
                case 4->{
                    System.out.println("Ingrese la letra de inicio, luego un espacio y la letra final:");
                    String inicio = sc.next();
                    String fin = sc.next();
                    System.out.println("\n\n\t\t\t####### Canciones Encontradas ########");
                    System.out.println("|--------------------------------------------------------------------------------|");
                    System.out.println("| NOMBRE \t\t| AUTOR \t\t| REPRODUCCIONES \t| ANIO \t |");
                    System.out.println("|--------------------------------------------------------------------------------|");
                    ConcurrentNavigableMap<Music, Music> like_musics_range = like_musics.subMap(new Music(inicio), new Music(fin));
                    for (Music value: like_musics_range.values()){
                        value.printMusic();
                    }
                }
                default -> System.out.println("Error la opcion es invalida.");
            }
            System.out.println("\nPreciona alguna tecla para continuar...");
            try {
                System.in.read();
            } catch (Exception e) {
                System.out.println("No c pudo");
            }
        }while(input != 0);
    }
    
}
