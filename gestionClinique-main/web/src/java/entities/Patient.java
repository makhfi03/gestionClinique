package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "patients")
public class Patient extends User{

    @Column(name = "age")
    private int age;

    @Column(name = "sexe")
    private String sexe;

    public Patient() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Patient(int age, String sexe) {
        this.age = age;
        this.sexe = sexe;
    }

    public Patient(int age, String sexe, String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
        this.age = age;
        this.sexe = sexe;
    }
    
    
}

