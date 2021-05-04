import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Log log = new Log("log.txt");
        log.logger.setLevel(Level.ALL);

        String login;
        String password;
        String email;
        String nombreFichero;

        boolean loginValido;
        boolean passwordValida;
        boolean emailValida;
        boolean nombreFicheroValido;

        Scanner scanner = new Scanner(System.in);

        do
        {
            loginValido = false;
            System.out.println("Introduce un nombre de usuario con una longitud de 10 caracteres y compuesto por 8 letras minusculas y 2 caracteres cualesquiera al final:");
            login = scanner.nextLine();
            Pattern patternLogin = Pattern.compile("[a-z]{8}[^a-z]{2}");

            Matcher mat = patternLogin.matcher(login);

            if(mat.matches())
            {
                loginValido = true;
                System.out.println("Nombre de usuario válido");
                log.logger.info("Se ha guardado el usuario correctamente");
            }
            else
            {
                System.out.println("El nombre de usuario no es válido.");
                log.logger.warning("El nombre de usuario que se ha intentado introducir no es valido");
            }
        }while (loginValido == false);

        do
        {
            passwordValida = false;
            System.out.println("Introduce una contraseña que debe tener 8 caracteres de longitud, como mínimo, y contener una mayúscula, una minúscula y un dígito, al menos");
            password = scanner.nextLine();
            Pattern patternLogin = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

            Matcher mat = patternLogin.matcher(password);

            if(mat.matches())
            {
                passwordValida = true;
                System.out.println("Contraseña válida");
                log.logger.info("Se ha guardado la contraseña correctamente");
            }
            else
            {
                System.out.println("La contraseña no es válida.");
                log.logger.warning("La contraseña introducida no es valida");
            }
        }while (passwordValida == false);

        do
        {
            emailValida = false;
            System.out.println("Introduce un email válido");
            email = scanner.nextLine();
            Pattern patternLogin = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");

            Matcher mat = patternLogin.matcher(email);

            if(mat.matches())
            {
                emailValida = true;
                System.out.println("Email válido");
                log.logger.info("Se ha guardado el email correctamente");
            }
            else
            {
                System.out.println("El email no es válido.");
                log.logger.warning("El email que se ha intentado introducir no es valido");
            }
        }while (emailValida == false);

        do
        {
            nombreFicheroValido = false;
            System.out.println("Introduce un nombre para el fichero, ");
            nombreFichero = scanner.nextLine();
            Pattern patternLogin = Pattern.compile("(([a-z0-9]{1,8})\\.([a-z0-9]{3}))");

            Matcher mat = patternLogin.matcher(nombreFichero);

            if(mat.matches())
            {
                nombreFicheroValido = true;
                System.out.println("Nombre fichero válido");
                log.logger.info("Se ha guardado el nombre del fichero correctamente");
            }
            else
            {
                System.out.println("El nombre del fichero no es válido.");
                log.logger.warning("El nombre de fichero que se ha intentado introducir no es valido");
            }
        }while (nombreFicheroValido == false);

        String texto = ("Nombre usuario: " + login + "\nContraseña: " + password + "\nCorreo electronico: " + email);

        escribirFichero(texto,nombreFichero);
        leerFichero(nombreFichero);

    }

    public static void escribirFichero(String texto, String nombreArchivo) throws IOException {
        Log log = new Log("log.txt");
        log.logger.setLevel(Level.ALL);

        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(nombreArchivo);
            pw = new PrintWriter(fichero);

            pw.print(texto);

            log.logger.info("Fichero escrito correctamente");
        } catch (Exception e) {
            log.logger.severe("Error al escribir en el fichero");
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
                log.logger.info("Fichero cerrado correctamente");
            } catch (Exception e2) {
                log.logger.severe("Error al cerrar el fichero");
                e2.printStackTrace();
            }
        }
    }

    public static void leerFichero(String nombreArchivo) throws IOException {
        Log log = new Log("log.txt");
        log.logger.setLevel(Level.ALL);


        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File (nombreArchivo);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            System.out.println("\nLeyendo fichero: \n\n");
            while((linea=br.readLine())!=null)
                System.out.println(linea);
            log.logger.info("Fichero leido correctamente");
        }
        catch(Exception e){
            log.logger.severe("Error al leer el fichero");
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                    log.logger.info("Fichero cerrado correctamente");
                }
            }catch (Exception e2){
                log.logger.severe("Error cerrar el fichero");
                e2.printStackTrace();
            }
        }
    }
}
