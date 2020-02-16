/*
 * Thibault ANIN
 * TP3 E2
 */

public class Liste {
	boolean listeVide ;
	double info;
	Liste reste;

	
	Liste (double e, Liste l) 
	{
		listeVide=false;
		info = e;
		reste= l;
	}

	
	Liste () 
	{
		listeVide = true;
	}

	
	public boolean vide ()
	{
		return listeVide;
	}

	
	public double tete ()
	{
		if (!(listeVide)) 
			return info;
		return 0;
	}

	
	public Liste reste ()
	{
		if (!(listeVide))
			return reste;
		return new Liste ();
	}
	
	
	public Liste prefixer(double e)
	{
		return new Liste (e, this);
	}
	
	
	public Liste insererOrd(Liste l, double e)
	{
		if(l.vide())
			return l.prefixer(e);

		else if(e < l.tete())
			return l.prefixer(e);

		else
			return insererOrd(l.reste(), e).prefixer(l.tete());
	}
	
	
	public static void affiche (Liste uneListe) 
	{
		if (!(uneListe.vide()))
		{ 
			System.out.print(uneListe.tete() + " ");
			affiche (uneListe.reste()); 
		}
	}
}

