/*
 * Thibault ANIN
 * TP3 E2
 */

public class ListeArbre {
	boolean listeVide ;
	Arbre info;
	ListeArbre reste;

	
	ListeArbre(Arbre arbre, ListeArbre l) 
	{
		listeVide=false;
		info = arbre;
		reste= l;
	}

	
	ListeArbre() 
	{
		listeVide = true;
	}

	
	public boolean vide()
	{
		return listeVide;
	}


	public Arbre tete()
	{
		if (!(listeVide)) 
			return info;
		return new Arbre();
	}

	
	public ListeArbre reste()
	{
		if (!(listeVide))
			return reste;
		return new ListeArbre();
	}
	
	
	public ListeArbre prefixer(Arbre arbre)
	{
		return new ListeArbre(arbre, this);
	}
	
	
	public static ListeArbre insererOrd(ListeArbre liste, Arbre arbre)
	{
		if(liste.vide())
			return liste.prefixer(arbre);
		else if(arbre.info().tete() < liste.tete().info().tete())
			return liste.prefixer(arbre);
		else
			return insererOrd(liste.reste(), arbre).prefixer(liste.tete());
	}
	
	
	public static void affiche(ListeArbre uneListe) 
	{
		if(!(uneListe.vide()))
		{
			uneListe.tete().afficheInfixe();
			affiche (uneListe.reste());
		}
	}	
}


