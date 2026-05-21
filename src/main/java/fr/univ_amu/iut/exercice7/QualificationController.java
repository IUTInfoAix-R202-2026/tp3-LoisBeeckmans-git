package fr.univ_amu.iut.exercice7;

import java.time.LocalTime;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

/**
 * Contrôleur de la pierre angulaire MVC (parcours P3 - vérification d'une nuit de capture par
 * échantillonnage).
 *
 * <p>L'instance possède son propre modèle ({@link NuitVerification}). Le FXML s'occupe de la
 * structure, le contrôleur du câblage modèle ↔ vue.
 */
public class QualificationController {

  @FXML private TableView<Sequence> tableView;

  @FXML private TableColumn<Sequence, LocalTime> colHorodatage;

  @FXML private TableColumn<Sequence, Number> colFrequence;

  @FXML private TableColumn<Sequence, Number> colDuree;

  @FXML private TableColumn<Sequence, String> colStatut;

  @FXML private Label labelSelection;

  @FXML private Button boutonEcouter;

  @FXML private Label labelLecture;

  @FXML private ChoiceBox<String> choiceBoxVerdict;

  @FXML private TextArea zoneCommentaire;

  @FXML private Label labelVerdictGlobal;

  private final NuitVerification nuit = NuitVerification.genererJeu(10);

  /**
   * Méthode appelée automatiquement après injection des champs {@code @FXML}. Tout le câblage MVC
   * se passe ici.
   */
  @FXML
  private void initialize() {
    // Étape 1 : Alimenter la TableView avec les séquences de la nuit
    colHorodatage.setCellValueFactory(c -> c.getValue().horodatageProperty());
    colFrequence.setCellValueFactory(c -> c.getValue().frequenceDominanteKHzProperty());
    colDuree.setCellValueFactory(c -> c.getValue().dureeSecondesProperty());
    colStatut.setCellValueFactory(c -> c.getValue().statutProperty());
    tableView.setItems(nuit.getSequences());

    // Étape 2 : Afficher la séquence sélectionnée dans labelSelection
    labelSelection.setText("(sélectionnez une séquence dans le tableau)");
    labelLecture.setText("");
    tableView
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (obs, ancien, nouveau) -> {
              if (nouveau == null) {
                labelSelection.setText("(sélectionnez une séquence dans le tableau)");
              } else {
                String text =
                    String.format(
                        "Séquence %s - %.1f kHz",
                        nouveau.getHorodatage(), nouveau.getFrequenceDominanteKHz());
                labelSelection.setText(text);
              }
            });

    // Étape 3 : Désactiver le bouton "Écouter" tant qu'aucune séquence n'est
    // sélectionnée
    boutonEcouter
        .disableProperty()
        .bind(tableView.getSelectionModel().selectedItemProperty().isNull());

    // Étape 4 : Peupler la ChoiceBox avec les trois verdicts
    choiceBoxVerdict.getItems().addAll("OK", "Douteux", "À jeter");

    // Étape 5 : Lier labelVerdictGlobal au verdict du modèle
    labelVerdictGlobal
        .textProperty()
        .bind(
            Bindings.when(nuit.verdictGlobalProperty().isEmpty())
                .then("Verdict global : (à saisir)")
                .otherwise(Bindings.concat("Verdict global : ", nuit.verdictGlobalProperty())));

    // Étape 6 : Lier la TextArea au modèle de manière bidirectionnelle
    zoneCommentaire.textProperty().bindBidirectional(nuit.commentaireProperty());
  }

  /** Action du bouton « Écouter ». Lecture audio simulée : statut → "Écoutée" + label éphémère. */
  @FXML
  private void ecouter() {
    Sequence selectionnee = tableView.getSelectionModel().getSelectedItem();
    if (selectionnee != null) {
      selectionnee.setStatut("Écoutée");
      labelLecture.setText("Lecture en cours...");
      PauseTransition pause = new PauseTransition(Duration.millis(600));
      pause.setOnFinished(e -> labelLecture.setText(""));
      pause.play();
    }
  }

  /** Action du bouton « Enregistrer le verdict ». Écrit le verdict choisi dans le modèle. */
  @FXML
  private void enregistrerVerdict() {
    String verdict = choiceBoxVerdict.getValue();
    if (verdict != null) {
      nuit.setVerdictGlobal(verdict);
    }
  }

  /** Exposé pour les tests : permet de vérifier l'état du modèle après actions sur la vue. */
  public NuitVerification getNuit() {
    return nuit;
  }
}
