package fr.gsb.cr.entites;

public class Visiteur {

	private String matricule ;
	private String mdp ;
	private String nom ;
	private String prenom ;
	
	public Visiteur(String matricule, String mdp, String nom, String prenom) {
		super();
		this.matricule = matricule;
		this.mdp = mdp ;
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Visiteur [matricule=" + matricule + ", mdp=" + mdp + ", nom="
				+ nom + ", prenom=" + prenom + "]";
	}
	
}