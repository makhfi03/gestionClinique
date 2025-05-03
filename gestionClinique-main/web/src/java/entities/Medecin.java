package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "medecins")
public class Medecin extends User {

    @Column(name = "specialite")
    private String specialite;

    public Medecin() {}

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Medecin(String specialite, String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
        this.specialite = specialite;
    }
    
}
