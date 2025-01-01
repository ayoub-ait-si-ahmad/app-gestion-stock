package com.enset;

import java.util.Scanner;

public class GestionStock {

    private int[] codesProduits = new int[100];
    private String[] nomsProduits = new String[100];
    private int[] quantites = new int[100];
    private double[] prix = new double[100];

    private int produitCount = 0;

    public static void printMenu() {
        System.out.println("---------- Gestion de Stock ----------");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("0. Quitter");
        System.out.print("Choisissez une option : ");
    }

    public void ajouterProduit(int code, String nom, int quantite, double prixProduit) {
        if (produitCount < 100) {
            codesProduits[produitCount] = code;
            nomsProduits[produitCount] = nom;
            quantites[produitCount] = quantite;
            prix[produitCount] = prixProduit;
            produitCount++;
            System.out.println("Produit ajouté avec succès.");
        } else {
            System.out.println("Capacité maximale atteinte.");
        }
    }

    public void modifierProduit(int code, String nouveauNom, int nouvelleQuantite, double nouveauPrix) {
        for (int i = 0; i < produitCount; i++) {
            if (codesProduits[i] == code) {
                nomsProduits[i] = nouveauNom;
                quantites[i] = nouvelleQuantite;
                prix[i] = nouveauPrix;
                System.out.println("Produit modifié avec succès.");
                return;
            }
        }
        System.out.println("Produit non trouvé.");
    }

    public void supprimerProduit(int code) {
        for (int i = 0; i < produitCount; i++) {
            if (codesProduits[i] == code) {
                for (int j = i; j < produitCount - 1; j++) {
                    codesProduits[j] = codesProduits[j + 1];
                    nomsProduits[j] = nomsProduits[j + 1];
                    quantites[j] = quantites[j + 1];
                    prix[j] = prix[j + 1];
                }
                produitCount--;
                System.out.println("Produit supprimé avec succès.");
                return;
            }
        }
        System.out.println("Produit non trouvé.");
    }

    public void afficherProduits() {
        if (produitCount == 0) {
            System.out.println("Aucun produit dans le stock.");
            return;
        }
        for (int i = 0; i < produitCount; i++) {
            System.out.println("Code: " + codesProduits[i] + ", Nom: " + nomsProduits[i] + ", Quantité: " + quantites[i] + ", Prix: " + prix[i]);
        }
    }

    public void rechercherProduit(String nom) {
        for (int i = 0; i < produitCount; i++) {
            if (nomsProduits[i].equalsIgnoreCase(nom)) {
                System.out.println("Produit trouvé: " + nomsProduits[i] + ", Code: " + codesProduits[i] + ", Quantité: " + quantites[i] + ", Prix: " + prix[i]);
                return;
            }
        }
        System.out.println("Produit non trouvé.");
    }

    public void calculerValeurStock() {
        double totalValue = 0;
        for (int i = 0; i < produitCount; i++) {
            totalValue += quantites[i] * prix[i];
        }
        System.out.println("Valeur totale du stock: " + totalValue);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionStock gestionStock = new GestionStock();

        while (true) {
            gestionStock.printMenu();
            int userOption = scanner.nextInt();

            switch (userOption) {
                case 1:
                    System.out.print("Entrez le code du produit: ");
                    int code = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Entrez le nom du produit: ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez la quantité du produit: ");
                    int quantite = scanner.nextInt();
                    System.out.print("Entrez le prix du produit: ");
                    double prixProduit = scanner.nextDouble();
                    gestionStock.ajouterProduit(code, nom, quantite, prixProduit);
                    break;

                case 2:
                    System.out.print("Entrez le code du produit à modifier: ");
                    int codeModifier = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Entrez le nouveau nom: ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Entrez la nouvelle quantité: ");
                    int nouvelleQuantite = scanner.nextInt();
                    System.out.print("Entrez le nouveau prix: ");
                    double nouveauPrix = scanner.nextDouble();
                    gestionStock.modifierProduit(codeModifier, nouveauNom, nouvelleQuantite, nouveauPrix);
                    break;

                case 3:
                    System.out.print("Entrez le code du produit à supprimer: ");
                    int codeSupprimer = scanner.nextInt();
                    gestionStock.supprimerProduit(codeSupprimer);
                    break;

                case 4:
                    gestionStock.afficherProduits();
                    break;

                case 5:
                    scanner.nextLine();
                    System.out.print("Entrez le nom du produit à rechercher: ");
                    String nomRecherche = scanner.nextLine();
                    gestionStock.rechercherProduit(nomRecherche);
                    break;

                case 6:
                    gestionStock.calculerValeurStock();
                    break;

                case 0:
                    System.out.println("Au revoir!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
    }
}
