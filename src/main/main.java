package main;

import Métier.Abonnement;
import Métier.Client;
import Métier.Periodicite;
import Métier.Revue;

import java.util.Scanner;

public class main {

    private static final Scanner clavier = new Scanner(System.in);

    public static void main(String[] args) {

        menuBaseManage();

    }

    //Affichage et entrée clavier du Menu
    public static void menuBaseManage() {
        System.out.println("--------------------------------------- Base de données CPOA --------------------------------------- \r\n");
        System.out.println("Dans quel base voulez-vous apporter des modifications ? Métier.Client , Métier.Revue , Métier.Periodicite , Métier.Abonnement ?");
        String baseChoice = clavier.nextLine();
        System.out.println("Quelle opération souhaitez-vous effectuer ? insert , delete , update ?");
        String operationChoice = clavier.nextLine();

        chooseBaseAndOperation(baseChoice, operationChoice);
    }

    //Vérification des entrées saisies pour les bases et les op
    public static void chooseBaseAndOperation(String baseChoice, String operationChoice) {
        if (baseChoice.equalsIgnoreCase("Métier.Periodicite")) {
            switch (operationChoice) {
                case "insert" -> {
                    System.out.println("Veuillez renseigner le libbelé :");
                    String libelleInsert = clavier.nextLine();
                    Periodicite insertPeriod = new Periodicite();
                    insertPeriod.insert(libelleInsert);
                }
                case "delete" -> {
                    System.out.println("Renseignez l'ID à supprimer :");
                    int idDel = clavier.nextInt();
                    Periodicite deletePeriod = new Periodicite();
                    deletePeriod.delete(idDel);
                }
                case "update" -> {
                    System.out.println("Le nouveau libellé à modifier :");
                    String libelleUpdate = clavier.nextLine();
                    System.out.println("Et son id :");
                    int idUpdate = clavier.nextInt();
                    Periodicite updatePeriod = new Periodicite();
                    updatePeriod.update(idUpdate, libelleUpdate);
                }
            }

        } else if (baseChoice.equalsIgnoreCase("Métier.Client")) {
            switch (operationChoice) {
                case "insert" -> {
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
                }
                case "delete" -> {
                    System.out.println("Renseignez l'ID à supprimer :");
                    int idDel = clavier.nextInt();
                    Client deleteClient = new Client();
                    deleteClient.delete(idDel);
                }
                case "update" -> {
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
                    updateClient.update(idUpdate, nomUpdate, prenomUpdate, no_rueUpdate, voieUpdate, code_postalUpdate, villeUpdate, paysUpdate);
                }
            }

        } else if (baseChoice.equalsIgnoreCase("Métier.Abonnement")) {
            switch (operationChoice) {
                case "insert" -> {
                    System.out.println("Veuillez renseigner l'ID client :");
                    int idClientInsert = clavier.nextInt();
                    System.out.println("Ainsi que l'ID revue :");
                    int idRevueInsert = clavier.nextInt();
                    Abonnement insertAbo = new Abonnement();
                    insertAbo.insert(idClientInsert, idRevueInsert);
                }
                case "delete" -> {
                    System.out.println("Renseignez l'ID à supprimer :");
                    int idDel = clavier.nextInt();
                    Abonnement deleteAbo = new Abonnement();
                    deleteAbo.delete(idDel);
                }
                case "update" -> {
                    System.out.println("L'id abonnement à modifier :");
                    int idAboUpdate = clavier.nextInt();
                    System.out.println("L'id revue :");
                    int idRevueUpdate = clavier.nextInt();
                    System.out.println("L'id client :");
                    int idClientUpdate = clavier.nextInt();
                    Abonnement updateAbo = new Abonnement();
                    updateAbo.update(idAboUpdate, idRevueUpdate, idClientUpdate);
                }
            }

        } else if (baseChoice.equalsIgnoreCase("Métier.Revue")){
            switch (operationChoice) {
                case "insert" -> {
                    System.out.println("Veuillez renseigner le Titre :");
                    String titreInsert = clavier.nextLine();
                    System.out.println("Ainsi que la desscription de la revue :");
                    String descInsert = clavier.nextLine();
                    System.out.println("Le tarif de la revue :");
                    float tarifInsert = clavier.nextFloat();
                    System.out.println("Ainsi que le visuel :");
                    String visuelInsert = clavier.nextLine();
                    System.out.println("L'id périodicité de la revue :");
                    int idPeriodInsert = clavier.nextInt();
                    Revue insertRevue = new Revue();
                    insertRevue.insert(titreInsert, descInsert, tarifInsert, visuelInsert, idPeriodInsert);
                }
                case "delete" -> {
                    System.out.println("Renseignez l'ID à supprimer :");
                    int idDel = clavier.nextInt();
                    Abonnement deleteRevue = new Abonnement();
                    deleteRevue.delete(idDel);
                }
                case "update" -> {
                    System.out.println("L'id Métier.Revue à modifier :");
                    int idRevueUpdate = clavier.nextInt();
                    clavier.nextLine();
                    System.out.println("Le titre à modifier :");
                    String titreUpdate = clavier.nextLine();
                    System.out.println("La description à modifier :");
                    String descUpdate = clavier.nextLine();
                    System.out.println("Le tarif :");
                    float tariftUpdate = clavier.nextFloat();
                    clavier.nextLine();
                    System.out.println("Le visuel :");
                    String visuelUpdate = clavier.nextLine();
                    System.out.println("L'id Périodicité :");
                    int idPeriodtUpdate = clavier.nextInt();
                    Revue updateRevue = new Revue();
                    updateRevue.update(idRevueUpdate, titreUpdate, descUpdate, tariftUpdate, visuelUpdate, idPeriodtUpdate);
                }
            }
        }
    }
}