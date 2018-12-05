package ba.unsa.etf.rpr.tutorijal6;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigInteger;

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
    public Button unesi;
    private boolean imeValidno;
    private boolean prezimeValidno;
    private boolean indeksValidan;
    private boolean jmbgValidno;
    private boolean datumValidno;
    private boolean emailValidno;
    private String jedinstveni = "";
    //private boolean adresaValidno;
    //private boolean telefonValidno;

    public boolean formularValidan() {
        return (imeValidno && prezimeValidno && indeksValidan && jmbgValidno && datumValidno && emailValidno);
    }

    private boolean ispravnoImeiPrezime(String n) {
        if (n.length() < 1 || n.length() > 20) return false;
        String znakovi = "ćčžđČĆŽŠĐ";
        for (int i = 0; i < n.length(); i++) {
            if ( !(n.charAt(i) >= 'A' && n.charAt(i) <= 'Z') && !(n.charAt(i) >= 'a' && n.charAt(i) <= 'z')  ) {
                boolean znak = true;
                for(int j=0; j<znakovi.length(); j++){
                    if(n.charAt(i) == znakovi.charAt(j)) znak = false;
                }
                if(znak) {
                    return false;
                }
            }
        }
        return !n.trim().isEmpty();
    }

    private boolean ispravanDatum(String n) {
        if(n.length() > 10) return false;

        for (int i = 0; i < n.length(); i++) {
            if ( i != 2 && i != 5 && !(n.charAt(i) >= '0' && n.charAt(i) <= '9')) {
                return false;
            }
            if((i == 2 || i ==5)&& !(n.charAt(i) == '/' || n.charAt(i) == '.')){
                return false;
            }
        }

        String dateBezRazmaka = n.substring(0, 2) + n.substring(3,5) + n.substring(7,n.length());

        if(dateBezRazmaka.equals(jedinstveni)) return true;

        return false;
    }

    private boolean ispravanJMBG(String n) {
        if (n.length() != 13) return false;

        for (int i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= '0' && n.charAt(i) <= '9')) return false;
        }

        //DDMMGGGRRBBBK = ABVGDĐEŽZIJKL
        //L = 11 - (( 7*(A+E) + 6*(B+Ž) + 5*(V+Z) + 4*(G+I) + 3*(D+J) + 2*(Đ+K) ) % 11)

        int kontrolna_cifra = Character.getNumericValue( n.charAt(n.length()-1) );
        int prvi_dio = Integer.parseInt(n.substring( 6, (n.length()-1) ));
        int drugi_dio = Integer.parseInt(n.substring(0,6));


        int kontrola = 0;
        for(int i=2; i<8; i++) {
           kontrola += i*( (prvi_dio%10) + (drugi_dio%10) );
           prvi_dio /= 10;
           drugi_dio /= 10;
        }
        kontrola = 11 - (kontrola%11);

        if(kontrolna_cifra != kontrola ) return false;

        jedinstveni = n.substring(0, 7);

        return !n.trim().isEmpty();
    }

    private boolean ispravanEmail(String n) {
        boolean et = false;
        int k = 0;

        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '@'){
                if(i!=0 && i < n.length()-4) et = true;
                k++;
            }
        }
        if (( n.substring((n.length() - 4), n.length()).contentEquals(".com") || n.substring((n.length() - 3 ), n.length()).contentEquals(".ba") ) && et && k==1) {
            return true;
        }
        return false;
    }

    private boolean ispravanIndex(String n) {
        if (n.length() > 5) return false;
        for (int i = 0; i < n.length(); i++) if (!(n.charAt(i) >= '0' && n.charAt(i) <= '9')) return false;
        return !n.trim().isEmpty();
    }

    public void validanUnos(ActionEvent actionEvent) {
        if(formularValidan()){
            System.out.println("Unos validan, upravo ste pristupili fakultetskom sistemu. Želimo vam prijatno korištenje!");
        }else{
            System.out.println("Unos nije validan, molimo popravite!");
        }
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

        ime.textProperty().addListener((observableValue, o, n) -> {
            if (ispravnoImeiPrezime(n)) {
                ime.getStyleClass().removeAll("nijepopunjeno");
                ime.getStyleClass().add("popunjeno");
                imeValidno = true;
            } else {
                ime.getStyleClass().removeAll("popunjeno");
                ime.getStyleClass().add("nijepopunjeno");
                imeValidno = false;
            }
        });

        prezime.textProperty().addListener((observableValue, o, n) -> {
            if (ispravnoImeiPrezime(n)) {
                prezime.getStyleClass().removeAll("nijepopunjeno");
                prezime.getStyleClass().add("popunjeno");
                prezimeValidno = true;
            } else {
                prezime.getStyleClass().removeAll("popunjeno");
                prezime.getStyleClass().add("nijepopunjeno");
                prezimeValidno = false;
            }
        });

        index.textProperty().addListener((observableValue, o, n) -> {
            if (ispravanIndex(n)) {
                index.getStyleClass().removeAll("nijepopunjeno");
                index.getStyleClass().add("popunjeno");
                indeksValidan = true;
            } else {
                index.getStyleClass().removeAll("popunjeno");
                index.getStyleClass().add("nijepopunjeno");
                indeksValidan = false;
            }
        });

        jmbg.textProperty().addListener((observableValue, o, n) -> {
            if (ispravanJMBG(n)) {
                jmbg.getStyleClass().removeAll("nijepopunjeno");
                jmbg.getStyleClass().add("popunjeno");
                jmbgValidno = true;
            } else {
                jmbg.getStyleClass().removeAll("popunjeno");
                jmbg.getStyleClass().add("nijepopunjeno");
                jmbgValidno = false;
            }
        });

        datum.textProperty().addListener((observableValue, o, n) -> {
            if (ispravanDatum(n)) {
                datum.getStyleClass().removeAll("nijepopunjeno");
                datum.getStyleClass().add("popunjeno");
                datumValidno = true;
            } else {
                datum.getStyleClass().removeAll("popunjeno");
                datum.getStyleClass().add("nijepopunjeno");
                datumValidno = false;
            }
        });

        email.textProperty().addListener((observableValue, o, n) -> {
            if (ispravanEmail(n)) {
                email.getStyleClass().removeAll("nijepopunjeno");
                email.getStyleClass().add("popunjeno");
                emailValidno = true;
            } else {
                email.getStyleClass().removeAll("popunjeno");
                email.getStyleClass().add("nijepopunjeno");
                emailValidno = false;
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

