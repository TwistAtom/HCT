/*
 * Thibault ANIN
 * TP3 E2
 */

public class Arbre {
	private Liste entier;
	private boolean vide;
	private Arbre filsGauche;
	private Arbre filsDroit;
	
	
	public Arbre() {
		vide = true;
	}
	
	
	public Arbre(Liste info, Arbre filsG, Arbre filsD) {
		entier = info;
		filsGauche = filsG;
		filsDroit = filsD;
		vide = false;
	}
	
	
	public boolean vide() 
	{
		return vide;
	}
	
	
	public Liste info() 
	{
		return entier;
	}
	
	
	public Arbre filsGauche() 
	{
		return filsGauche;
	}
	
	
	public Arbre filsDroit() 
	{
		return filsDroit;
	}
	
	
	public void afficheInfixe() 
	{	
		if(!filsGauche.vide())
			filsGauche.afficheInfixe();
	
		if(!entier.reste().vide())
			System.out.println((char) entier.reste().tete() + ":" + entier.tete());
		else
			System.out.println(entier.tete());
		
		if(!filsDroit.vide())
			filsDroit.afficheInfixe();
	}

	
	public void affichePrefixe() 
	{	
		if(!entier.reste().vide())
			System.out.println((char) entier.reste().tete() + ":" + entier.tete());
		else
			System.out.println(entier.tete());
		
		if(!filsGauche.vide())
			filsGauche.affichePrefixe();
		
		if(!filsDroit.vide())
			filsDroit.affichePrefixe();
	}
	
	
	public static boolean contenu(Arbre a, Liste l) 
	{
		if(l.vide())
			return true;
		if(a.vide())
			return false;
		
		if(a.info().tete() == l.tete())
			return contenu(a.filsGauche(),l.reste()) || contenu(a.filsDroit(),l.reste());
		else
			return contenu(a.filsGauche(),l) || contenu(a.filsDroit(),l);		
	}
}
