package fr.univ_amu.iut.exercice6;

import fr.univ_amu.iut.exercice5.SiteCarte;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * Contrôleur du panneau central qui contient la liste de cartes de sites.
 *
 * <p>Expose une méthode {@link #ajouterSiteDemo()} qui ajoute dynamiquement une nouvelle {@link
 * SiteCarte} (composant écrit à l'exercice 5) dans la liste. Le contenu des cartes est généré
 * automatiquement à partir d'un compteur interne, pour les besoins de la démo.
 *
 * <p>Aucun lien direct avec l'en-tête : c'est le contrôleur parent qui orchestre les deux.
 */
public class ListeSitesController {

  @FXML private VBox conteneurCartes;

  private int compteurDemo = 0;

  /**
   * Ajoute une carte de site dans la liste avec des données démonstratives qui varient à chaque
   * appel (numéro de carré incrémenté, nombre de jours, nombre de points...).
   *
   * @return le nombre total de cartes après ajout
   */
  public int ajouterSiteDemo() {
    compteurDemo++;
    SiteCarte carte = new SiteCarte();
    carte.setNumeroCarre("Carré " + String.format("%06d", 640000 + compteurDemo));
    carte.setNomConvivial("📍 Site de démonstration #" + compteurDemo);
    carte.setNombrePoints((compteurDemo % 3) + 1);
    carte.setNombrePassages(compteurDemo * 2);
    carte.setJoursDepuisDernierPassage(compteurDemo * 4);
    conteneurCartes.getChildren().add(0, carte);
    return conteneurCartes.getChildren().size();
  }

  /** Retourne le nombre courant de cartes affichées (utile pour les tests). */
  public int getNombreCartes() {
    return conteneurCartes.getChildren().size();
  }
}
