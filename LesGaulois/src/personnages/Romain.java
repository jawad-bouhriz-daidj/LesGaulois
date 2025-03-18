package personnages;

public class Romain {

	private String nom;
	private int force;
	private Equipement[] equipements;
	private int nbEquipements = 0;

	private boolean isInvariantVerified(int force) {
	    return force > 0;
	}
	
//	private boolean isPostConditionVerified(int forceAvant, int forceApres) {
//	    return forceAvant - forceApres > 0;
//	}
	
	
	public Romain(String nom, int force) {
		this.nom = nom;
		this.force = force;
		this.equipements = new Equipement[2];
		assert isInvariantVerified(this.force);
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getForce() {
		return force;
	}
	
	public String prendreParole() {
		return "Le romain " + nom + " : ";
	}

	public void parler(String texte) {
		System.out.println(prendreParole() + "<" + texte + ">");
	}
	
//	public void recevoirCoup(int forceCoup) {
//		assert isInvariantVerified(forceCoup) : "la force du coup re�u est positive";
//	
//		int forceAvant = force;
//		force -= forceCoup;
//		int forceApres = force;
//	
//		if (force > 0) {
//			parler("Aie !");
//		}
//		else {
//			parler("J'abandonne !");
//		}
//		assert isPostConditionVerified(forceAvant,forceApres) : "la force d�un Romain a diminu�";
//	}
	
	public Equipement[] recevoirCoup(int forceCoup) {
		Equipement[] equipementEjecte = null;
		forceCoup = resistanceEquipement(forceCoup);
		force-= forceCoup;
		if (force >= 0) {
			parler("A�e");
		}
		else {
			equipementEjecte = ejecterEquipement();
			parler("J'abandonne...");
		}
		return equipementEjecte;
	}
	
	private int resistanceEquipement(int forceCoup) {
		String texte;
		texte = "Ma force est de " + this.force + ", et la force du coup est de " + forceCoup;
		int resistanceEquipement = 0;
		if (nbEquipements != 0) {
			texte += "\nMais heureusement, grace � mon �quipement sa force est diminu� de ";
			for (int i = 0; i < nbEquipements;) {
				if ((equipements[i] != null && equipements[i].equals(Equipement.BOUCLIER)) == true) {
					resistanceEquipement += 8;
				}
				else {
					System.out.println("Equipement casque");
					resistanceEquipement += 5;
				}
			}
			texte += resistanceEquipement + "!";
		}
		parler(texte);
		forceCoup-= resistanceEquipement;
		if (forceCoup <= 0) {
			forceCoup = 0;
			parler("Je suis plus fort que toi, tu ne peut pas gagner !");
		}
		return forceCoup;
	
	}
	
	public Equipement[] ejecterEquipement() {
		Equipement[] equipementEjecte = new Equipement[nbEquipements];
		System.out.println("L'�quipement de " + nom + " s'envole sous la force du coup.");
		int nbEquipementEjecte = 0;
		for (int i = 0; i < nbEquipements; i++) {
			if (equipements[i] == null) {
				continue;
			}
			else {
				equipementEjecte[nbEquipementEjecte] = equipements[i];
			}
			nbEquipementEjecte++;
			equipements[i] = null;
		}
		return equipementEjecte;
	}
	
	@Override
	public String toString() {
		return "Romain [nom=" + nom + ", force=" + force + "]";
	}
	
	public void sEquiper(Equipement equipement) {
		if (nbEquipements >= 2) {
			parler("est d�j� bien prot�g� !");
			return;
		}
		for(int i=0;i<nbEquipements;i++) {
			if(equipements[i] == equipement) {
				parler("poss�de d�j� un " + equipement + " !");
				return;
			}
		}
		equipements[nbEquipements] = equipement;
		nbEquipements ++;
		parler("s'�quipe avec " + equipement + ".");
	}
	
	public static void main(String[] args) {
		Romain garde = new Romain("Garde",6);
		Gaulois asterix = new Gaulois("Asterix",8);
		
		garde.parler("Bonjour !");
		asterix.frapper(garde);
		asterix.frapper(garde);
		asterix.frapper(garde);
		
//		System.out.println("TEST DES ASSERTS :");
//		Romain garde2 = new Romain("Garde2",-6);
//		garde2.recevoirCoup(-1);
		
		garde.sEquiper(Equipement.CASQUE);
		garde.sEquiper(Equipement.CASQUE);
		garde.sEquiper(Equipement.BOUCLIER);
		garde.sEquiper(Equipement.CASQUE);
	}
}