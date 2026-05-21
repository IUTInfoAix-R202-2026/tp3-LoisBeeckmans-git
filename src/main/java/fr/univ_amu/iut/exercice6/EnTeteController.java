package fr.univ_amu.iut.exercice6;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Contrôleur de l'en-tête de la maquette M-Sites.
 *
 * <p>Affiche un titre, un sous-titre dynamique « N site(s) déclaré(s) » et un bouton « + Nouveau
 * site ». L'action déclenchée par le bouton est fournie de l'extérieur par le contrôleur parent via
 * {@link #setActionNouveauSite(Runnable)} : ce contrôleur ne connaît <b>pas</b> directement la
 * liste de cartes ni l'autre contrôleur.
 */
public class EnTeteController {

  @FXML private Label labelSousTitre;

  @FXML private Button boutonNouveauSite;

  private Runnable actionNouveauSite = () -> {};

  /**
   * Méthode invoquée automatiquement par {@link javafx.fxml.FXMLLoader} après injection des
   * composants. On branche ici le clic du bouton sur l'action externe et on initialise le compteur.
   */
  @FXML
  private void initialize() {
    boutonNouveauSite.setOnAction(e -> actionNouveauSite.run());
    mettreAJourCompteur(0);
  }

  /**
   * Permet au contrôleur parent de fournir l'action à exécuter quand l'utilisateur clique sur le
   * bouton « + Nouveau site ». Le parent y branchera typiquement l'ajout d'une carte dans la liste.
   */
  public void setActionNouveauSite(Runnable action) {
    this.actionNouveauSite = action;
  }

  /**
   * Met à jour le sous-titre selon le nombre de sites actuellement déclarés.
   *
   * @param nombreSites le nombre courant de cartes affichées dans la liste
   */
  public void mettreAJourCompteur(int nombreSites) {
    String texte;
    if (nombreSites == 0) {
      texte = "Aucun site déclaré";
    } else if (nombreSites == 1) {
      texte = "1 site déclaré";
    } else {
      texte = nombreSites + " sites déclarés";
    }
    labelSousTitre.setText(texte);
  }
}
