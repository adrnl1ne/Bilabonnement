package com.eksamen.Service;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Model.Bil.Biltilstand;
import com.eksamen.Repository.BilRepository;
import com.eksamen.utilities.CheckingupNoneRentedCarException;
import com.eksamen.utilities.DamagingNoneCheckedCarException;
import com.eksamen.utilities.FixingNoneDamagedCarException;
import com.eksamen.utilities.ReadyingNoneCheckedCarException;

import java.util.List;


public class BilService {

    private final BilRepository bilRepo = new BilRepository();
    //Marcus
    public List<Bil> viewSkadetBiler() {
        return bilRepo.viewSkadedeBiler();
    }
    //Marcus
    public Bil viewBil(String stelnummer) {
        return bilRepo.viewBil(stelnummer);
    }
    //Jakob
    public void updateBil(Bil bil) {
        bilRepo.updateBil(bil);
    }

    // TODO: denne metode vil udskifte nødvendigheden for logikken i MekanikerController for at kun udlejet biler kan blive registreret som hjemvendte
    // Marcus
    public void updateUdlejetTilCheckup (String stelnummer) throws CheckingupNoneRentedCarException {
        Bil bilen = this.viewBil(stelnummer);
        Biltilstand bilensTilstand = bilen.getBiltilstand();
        if (bilensTilstand == Biltilstand.UDLEJET) {
            bilen.setBiltilstand(Biltilstand.CHECKUP);
            this.updateBil(bilen);
        } else {
            System.err.println("Det var ikke muligt at update en bil fra UDLEJET til CHECKIN. Stelnummer: " + stelnummer + "\n");
            throw new CheckingupNoneRentedCarException(bilen.toString());
        }
    }
    // TODO: denne overloadet metode tager et bil objekt og finder dets stelnummer, så den samme metode der tager imod en string kan skifte tilstanden for den bil
    public void updateUdlejetTilCheckup (Bil bil) throws CheckingupNoneRentedCarException {
        String stelnummer = bil.getStelnummer();
        updateUdlejetTilCheckup(stelnummer);
    }

    // TODO: Denne metode gør så en hjemvendt bil kan få sat sin tilstand til at være enten klar eller skadet
    // Marcus
    public void updateHjemvendtBilTilKlarEllerSkadet (String stelnummer, int tilstands_ID) throws ReadyingNoneCheckedCarException, DamagingNoneCheckedCarException {
        // finder/viewer bilen i databasen ud fra stelnummer
        Bil bil = this.viewBil(stelnummer);
        // finder den ønskede biltilstand som der skal updates til, hvis det ikke er en valid int der sendes over, så vil Biltilstand throw en exception
        Biltilstand biltilstand = Biltilstand.getEnum(tilstands_ID);
        // Vælger hvilken privat metode der skal bruges for at ændre biltilstand: Klar eller Skadet
        switch (biltilstand) {

            case KLAR -> this.updateCheckedTilKlar(bil);
            case SKADET -> this.updateCheckedTilSkadet(bil);
            default -> throw new RuntimeException("BilService metoden updateHjemvendtBilTilKlarEllerSkadet forsøgte at ændre en hjemvendt bils tilstand til: " + biltilstand);
        }
    }

    // TODO: denne private metode vil udskifte nødvendigheden for logikken i MekanikerController for at kun CHECKUP biler kan blive registreret som at være KLAR igen til udlejning
    // Marcus
    private void updateCheckedTilKlar (Bil bil) throws ReadyingNoneCheckedCarException {
        // Finder bilens tilstand
        Biltilstand tilstand = bil.getBiltilstand();
        // Hvis den tilstand er at bilen er hjemvendt så ændre den tilstanden til KLAR ellers throw en exception
        if (tilstand == Biltilstand.CHECKUP) {
            bil.setBiltilstand(Biltilstand.KLAR);
            this.updateBil(bil);
        } else {
            System.err.println("Det var ikke muligt at update en bil med tilstanden: " + tilstand + " til KLAR.");
            throw new ReadyingNoneCheckedCarException(bil.toString());
        }
    }

    // TODO: denne private metode vil udskifte nødvendigheden for logikken i MekanikerController for at kun CHECKUP biler kan blive registreret som at være SKADET og ikke kan udlejes
    // Marcus
    private void updateCheckedTilSkadet (Bil bil) throws DamagingNoneCheckedCarException {
        // Finder bilens tilstand
        Biltilstand tilstand = bil.getBiltilstand();
        // Hvis den tilstand er at bilen er hjemvendt så ændre den tilstanden til KLAR ellers throw en exception
        if (tilstand == Biltilstand.CHECKUP) {
            bil.setBiltilstand(Biltilstand.SKADET);
            this.updateBil(bil);
        } else {
            System.err.println("Det var ikke muligt at update en bil med tilstanden: " + tilstand + " til SKADET.");
            throw new DamagingNoneCheckedCarException(bil.toString());
        }
    }

    // TODO: denne metode vil udskifte nødvendigheden for logikken i MekanikerController for at kun SKADET biler kan blive registreret som at være KLAR og kan udlejes igen
    // Marcus
    public void updateSkadetTilKlar (Bil bil) throws FixingNoneDamagedCarException {
        // Finder bilens tilstand
        Biltilstand tilstand = bil.getBiltilstand();
        // Hvis den tilstand er at bilen er skadet så ændre den tilstanden til KLAR ellers throw en exception
        if (tilstand == Biltilstand.SKADET) {
            bil.setBiltilstand(Biltilstand.KLAR);
            this.updateBil(bil);
        } else {
            System.err.println("Det var ikke muligt at update en bil med tilstanden: " + tilstand + " til KLAR.");
            throw new FixingNoneDamagedCarException(bil.toString());
        }
    }
}
