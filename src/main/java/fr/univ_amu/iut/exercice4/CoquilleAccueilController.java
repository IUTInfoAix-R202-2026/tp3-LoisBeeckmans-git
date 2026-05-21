package fr.univ_amu.iut.exercice4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Contrôleur de la vue {@code CoquilleAccueilView.fxml}.
 *
 * <p>Concepts mis en pratique :
 *
 * <ul>
 *   <li>injection de {@link Label} via {@code @FXML} (titre central + statut en bas de fenêtre)
 *   <li>plusieurs handlers reliés au FXML par {@code onAction="#..."} sur les items d'un {@code
 *       MenuBar}
 *   <li>séparation propre : la <b>structure</b> (BorderPane + MenuBar + center + bottom) est dans
 *       le FXML, la <b>logique</b> (changement d'écran) est dans le contrôleur
 * </ul>
 *
 * <p>Aucun écran réel n'est implémenté ici : on se contente d'afficher le nom de la rubrique au
 * centre et de tracer le dernier clic dans la barre de statut. C'est suffisant pour vérifier que la
 * navigation principale fonctionne, sans détourner l'exercice de son objectif (FXML déclaratif).
 */
public class CoquilleAccueilController {

  @FXML private Label labelTitre;

  @FXML private Label labelStatut;

  /** Action du menu « Mes sites ». Affiche le titre correspondant au centre de la coquille. */
  @FXML
  private void onMesSites() {
    labelTitre.setText("Mes sites de suivi");
    labelStatut.setText("Rubrique active : Mes sites");
  }

  /** Action du menu « Importer une nuit ». */
  @FXML
  private void onImporter() {
    labelTitre.setText("Importer une nuit");
    labelStatut.setText("Rubrique active : Importer une nuit");
  }

  /** Action du menu « Vue tabulaire ». */
  @FXML
  private void onVueTabulaire() {
    labelTitre.setText("Vue tabulaire des passages");
    labelStatut.setText("Rubrique active : Vue tabulaire");
  }

  /** Action du menu « Paramètres ». */
  @FXML
  private void onParametres() {
    labelTitre.setText("Paramètres de l'application");
    labelStatut.setText("Rubrique active : Paramètres");
  }

  /** Action du menu « Fichier > Quitter ». Ferme la fenêtre courante via la scène du label. */
  @FXML
  private void onQuitter() {
    labelTitre.getScene().getWindow().hide();
  }
}
