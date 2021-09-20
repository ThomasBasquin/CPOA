import java.sql.SQLOutput;
import java.time.Period;
import java.util.Scanner;

public class main {

    private static Scanner clavier = new Scanner(System.in);

    public static void main(String[] args) {

        menuBaseManage();

    }

    //Affichage et entrée clavier du Menu
    public static void menuBaseManage() {
        System.out.println("--------------------------------------- Base de données CPOA --------------------------------------- \r\n");
        System.out.println("Dans quel base voulez-vous apporter des modifications ? Client , Revue , Periodicite , Abonnement ?");
        String baseChoice = clavier.nextLine();
        System.out.println("Quelle opération souhaitez-vous effectuer ? insert , delete , update ?");
        String operationChoice = clavier.nextLine();

        chooseBaseAndOperation(baseChoice, operationChoice);
    }

    //Vérification des entrées saisies pour les bases et les op
    public static void chooseBaseAndOperation(String baseChoice, String operationChoice) {
        int i = 0;
        if (baseChoice.equalsIgnoreCase("Periodicite")) {
            switch (operationChoice) {
                case "insert":
                    System.out.println("Veuillez renseigner le libbelé :");
                    String libelleInsert = clavier.nextLine();
                    Periodicite insertPeriod = new Periodicite();
                    insertPeriod.insert(libelleInsert);
                    break;
                case "delete":
                    System.out.println("Renseignez l'ID à supprimer :");
                    int idDel = clavier.nextInt();
                    Periodicite deletePeriod = new Periodicite();
                    deletePeriod.delete(idDel);
                    break;
                case "update":
                    System.out.println("Le nouveau libellé à modifier :");
                    String libelleUpdate = clavier.nextLine();
                    System.out.println("Et son id :");
                    int idUpdate = clavier.nextInt();
                    Periodicite updatePeriod = new Periodicite();
                    updatePeriod.update(idUpdate, libelleUpdate);
                    break;
            }

        } else if (baseChoice.equalsIgnoreCase("Client")) {
            switch (operationChoice) {
                case "insert":
                    System.out.println("Veuillez renseigner le Nom :");
                    String nomInsert = clavier.nextLine();
                    System.out.println("Le Prénom :");
                    String prenomInsert = clavier.nextLine();
                    System.out.println("Le numéro de rue :");
                    String no_rueInsert = clavier.nextLine();
                    System.out.println("La voie :");
                    String voieInsert = clavier.nextLine();
                    System.out.println("Le code postal :");
                    String code_postalInsert = clavier.nextLine();
                    System.out.println("La ville :");
                    String villeInsert = clavier.nextLine();
                    System.out.println("Le Pays :");
                    String paysInsert = clavier.nextLine();
                    Client insertClient = new Client();
                    insertClient.insert(nomInsert, prenomInsert, no_rueInsert, voieInsert, code_postalInsert, villeInsert, paysInsert);
                    break;
                case "delete":
                    System.out.println("Renseignez l'ID à supprimer :");
                    int idDel = clavier.nextInt();
                    Client deleteClient = new Client();
                    deleteClient.delete(idDel);
                    break;
                case "update":
                    System.out.println("Le nom à modifier :");
                    String nomUpdate = clavier.nextLine();
                    System.out.println("Le prénom :");
                    String prenomUpdate = clavier.nextLine();
                    System.out.println("Le numéro de rue :");
                    String no_rueUpdate = clavier.nextLine();
                    System.out.println("La voie :");
                    String voieUpdate = clavier.nextLine();
                    System.out.println("Le code postal :");
                    String code_postalUpdate = clavier.nextLine();
                    System.out.println("La Ville :");
                    String villeUpdate = clavier.nextLine();
                    System.out.println("Le Pays :");
                    String paysUpdate = clavier.nextLine();
                    System.out.println("L'id du client à modifier :");
                    int idUpdate = clavier.nextInt();
                    Client updateClient = new Client();
                    updateClient.update(idUpdate , nomUpdate, prenomUpdate, no_rueUpdate, voieUpdate, code_postalUpdate, villeUpdate, paysUpdate);
                    break;
            }

        } else if (baseChoice.equalsIgnoreCase("Revue")) {
            switch (operationChoice) {
                case "insert":
                    System.out.println("Veuillez renseigner le Nom :");
                    String nomInsert = clavier.nextLine();
                    System.out.println("Le Prénom :");
                    String prenomInsert = clavier.nextLine();
                    System.out.println("Le numéro de rue :");
                    String no_rueInsert = clavier.nextLine();
                    System.out.println("La voie :");
                    String voieInsert = clavier.nextLine();
                    System.out.println("Le code postal :");
                    String code_postalInsert = clavier.nextLine();
                    System.out.println("La ville :");
                    String villeInsert = clavier.nextLine();
                    System.out.println("Le Pays :");
                    String paysInsert = clavier.nextLine();
                    Client insertClient = new Client();
                    insertClient.insert(nomInsert, prenomInsert, no_rueInsert, voieInsert, code_postalInsert, villeInsert, paysInsert);
                    break;
                case "delete":
                    System.out.println("Renseignez l'ID à supprimer :");
                    int idDel = clavier.nextInt();
                    Client deleteClient = new Client();
                    deleteClient.delete(idDel);
                    break;
                case "update":
                    System.out.println("Le nom à modifier :");
                    String nomUpdate = clavier.nextLine();
                    System.out.println("Le prénom :");
                    String prenomUpdate = clavier.nextLine();
                    System.out.println("Le numéro de rue :");
                    String no_rueUpdate = clavier.nextLine();
                    System.out.println("La voie :");
                    String voieUpdate = clavier.nextLine();
                    System.out.println("Le code postal :");
                    String code_postalUpdate = clavier.nextLine();
                    System.out.println("La Ville :");
                    String villeUpdate = clavier.nextLine();
                    System.out.println("Le Pays :");
                    String paysUpdate = clavier.nextLine();
                    System.out.println("L'id du client à modifier :");
                    int idUpdate = clavier.nextInt();
                    Client updateClient = new Client();
                    //updateClient.update(nomUpdate, prenomUpdate, no_rueUpdate, voieUpdate, code_postalUpdate, villeUpdate, paysUpdate, idUpdate);
                    break;
            }

        } else if (baseChoice.equalsIgnoreCase("Abonnement")) {

        }
    }
}