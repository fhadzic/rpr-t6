package ba.unsa.etf.rpr.tutorijal6;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    public ComboBox mjesto;
    public ComboBox status;
    public ComboBox godina;
    public ComboBox smjer;
    public CheckBox pripadnost;
    public TextField ime;
    public TextField prezime;
    public TextField index;
    public TextField jmbg;
    public TextField datum;
    public TextField email;
    public TextField adresa;
    public TextField telefon;
    private boolean imeValidno;
    private boolean prezimeValidno;
    private boolean indeksValidan;
    private boolean jmbgValidno;
    private boolean datumValidno;
    private boolean emailValidno;
    //private boolean adresaValidno;
    //private boolean telefonValidno;

    public boolean formularValidan() {
        return (imeValidno && prezimeValidno && indeksValidan && jmbgValidno && datumValidno && emailValidno);
    }

    private boolean ispravnoImeiPrezime(String n) {
        if (n.length() < 1 || n.length() > 20) return false;
        for (int i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= 'A' && n.charAt(i) <= 'Z') && !(n.charAt(i) >= 'a' && n.charAt(i) <= 'z'))
                return false;
        }
        return !n.trim().isEmpty();
    }

    private boolean ispravanDatum(String n) {
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) >= '0' && n.charAt(i) <= '9') {
                for (int j = 0; j < n.length(); j++) if(n.charAt(j) == '/' || n.charAt(j) == '.') return true;
            }
        }
        return false;
    }

    private boolean ispravanJMBG(String n) {
        if (n.length() != 13) return false;
        for (int i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= '0' && n.charAt(i) <= '9')) return false;
        }
        return !n.trim().isEmpty();
    }

    private boolean ispravanEmail(String n) {
        for (int i = 0; i < n.length(); i++) if (n.charAt(i) == '@') return true;
        return false;
    }

    private boolean ispravanIndex(String n) {
        if (n.length() > 5) return false;
        for (int i = 0; i < n.length(); i++) if (!(n.charAt(i) >= '0' && n.charAt(i) <= '9')) return false;
        return !n.trim().isEmpty();
    }

    /*private boolean ispravnaAdresa(String n) {
        return !n.trim().isEmpty();
    }

    private boolean ispravanTelefon(String n) {
        for (int i = 0; i < n.length(); i++) if (n.charAt(i) == '/' || n.charAt(i) == '-') return true;
        return false;
    } NEPOTREBNO */

    @FXML
    public void initialize() {
        imeValidno = false;
        prezimeValidno = false;
        indeksValidan = false;
        jmbgValidno = false;
        datumValidno = false;
        emailValidno = false;
        //telefonValidno = false;
        //adresaValidno = false;
        ime.getStyleClass().add("nijepopunjeno");
        prezime.getStyleClass().add("nijepopunjeno");
        index.getStyleClass().add("nijepopunjeno");
        jmbg.getStyleClass().add("nijepopunjeno");
        datum.getStyleClass().add("nijepopunjeno");
        email.getStyleClass().add("nijepopunjeno");
        //telefon.getStyleClass().add("nijepopunjeno"); MOZE BITI PRAZNO
        //adresa.getStyleClass().add("nijepopunjeno"); MOZE BITI PRAZNO

        ime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravnoImeiPrezime(n)) {
                    ime.getStyleClass().removeAll("nijepopunjeno");
                    ime.getStyleClass().add("popunjeno");
                    imeValidno = true;
                } else {
                    ime.getStyleClass().removeAll("popunjeno");
                    ime.getStyleClass().add("nijepopunjeno");
                    imeValidno = false;
                }
            }
        });
        prezime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravnoImeiPrezime(n)) {
                    prezime.getStyleClass().removeAll("nijepopunjeno");
                    prezime.getStyleClass().add("popunjeno");
                    prezimeValidno = true;
                } else {
                    prezime.getStyleClass().removeAll("popunjeno");
                    prezime.getStyleClass().add("nijepopunjeno");
                    prezimeValidno = false;
                }
            }
        });
        index.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanIndex(n)) {
                    index.getStyleClass().removeAll("nijepopunjeno");
                    index.getStyleClass().add("popunjeno");
                    indeksValidan = true;
                } else {
                    index.getStyleClass().removeAll("popunjeno");
                    index.getStyleClass().add("nijepopunjeno");
                    indeksValidan = false;
                }
            }
        });
        jmbg.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanJMBG(n)) {
                    jmbg.getStyleClass().removeAll("nijepopunjeno");
                    jmbg.getStyleClass().add("popunjeno");
                    jmbgValidno = true;
                } else {
                    jmbg.getStyleClass().removeAll("popunjeno");
                    jmbg.getStyleClass().add("nijepopunjeno");
                    jmbgValidno = false;
                }
            }
        });
        datum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanDatum(n)) {
                    datum.getStyleClass().removeAll("nijepopunjeno");
                    datum.getStyleClass().add("popunjeno");
                    datumValidno = true;
                } else {
                    datum.getStyleClass().removeAll("popunjeno");
                    datum.getStyleClass().add("nijepopunjeno");
                    datumValidno = false;
                }
            }
        });
        email.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanEmail(n)) {
                    email.getStyleClass().removeAll("nijepopunjeno");
                    email.getStyleClass().add("popunjeno");
                    emailValidno = true;
                } else {
                    email.getStyleClass().removeAll("popunjeno");
                    email.getStyleClass().add("nijepopunjeno");
                    emailValidno = false;
                }
            }
        });
        /*adresa.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravnaAdresa(n)) {
                    adresa.getStyleClass().removeAll("nijepopunjeno");
                    adresa.getStyleClass().add("popunjeno");
                    adresaValidno = true;
                } else {
                    adresa.getStyleClass().removeAll("popunjeno");
                    adresa.getStyleClass().add("nijepopunjeno");
                    adresaValidno = false;
                }
            }
        });
        telefon.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanTelefon(n)) {
                    telefon.getStyleClass().removeAll("nijepopunjeno");
                    telefon.getStyleClass().add("popunjeno");
                    telefonValidno = true;
                } else {
                    telefon.getStyleClass().removeAll("popunjeno");
                    telefon.getStyleClass().add("nijepopunjeno");
                    telefonValidno = false;
                }
            }
        }); ADRESA I TELEFON IPAK MOGU BITI PRAZNI */
    }


}

