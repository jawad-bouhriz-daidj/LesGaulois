package personnages;

public class Musee {
		
    private Trophee[] trophees;
    private int nbTrophee;

    public Musee() {
        this.trophees = new Trophee[200];
        this.nbTrophee = 0;
    }
    
    public void donnerTrophees(Gaulois gaulois, Equipement equipement) {
        if (nbTrophee < 200) {
            Trophee nouveauTrophee = new Trophee(gaulois, equipement);
            trophees[nbTrophee] = nouveauTrophee;
            nbTrophee++;
        }
        else {
            System.out.println("Le mus�e est plein, impossible d'ajouter un nouveau troph�e.");
        }
    }

    public int getNbTrophee() {
        return nbTrophee;
    }

    public Trophee[] getTrophees() {
        return trophees;
    }
    
}