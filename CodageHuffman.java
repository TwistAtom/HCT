/*
 * Thibault ANIN
 * TP3 E2
 */

import java.util.ArrayList;

public class CodageHuffman {
	private ListeArbre racines;
	private Arbre arbre;
	private ArrayList<String[]> codes;
	private boolean valide;
	
	public CodageHuffman(char caracteres[], double frequences[]) 
	{
		setRacines(caracteres, frequences);
	}
	
	
	public void setRacines(char caracteres[], double frequences[])
	{
		if(caracteres.length == frequences.length)
		{
			valide = true;
			Liste noeud;
			racines = new ListeArbre();
			for(int i = 0; i < caracteres.length; i++)
			{
				noeud = new Liste();
				noeud = noeud.prefixer((int) caracteres[i]);
				noeud = noeud.prefixer(frequences[i]);
				racines = ListeArbre.insererOrd(racines, new Arbre(noeud, new Arbre(), new Arbre()));
			}
			arbre = construireArbre(racines);
			codes = calculerCodage(arbre, "");
		}
		else
		{
			System.out.println("\n[Opération impossible] : il n'y a pas le même nombre de fréquences et de caractères.\n");
			valide = false;
		}
	}

	
	private Arbre construireArbre(ListeArbre liste)
	{
		if(!liste.reste().vide())
		{
			Liste noeud = new Liste();
			Arbre arbre, arbreGauche = liste.tete(), arbreDroit = liste.reste().tete();
			
			noeud = noeud.prefixer(arbreGauche.info().tete() + arbreDroit.info().tete());
			arbre = new Arbre(noeud, arbreGauche, arbreDroit);
			
			arbreGauche = liste.tete();
			
			return construireArbre(ListeArbre.insererOrd(liste.reste().reste(), arbre));		
		}
		else
			return liste.tete();
	}
	
	
	private ArrayList<String[]> calculerCodage(Arbre arbre, String code)
	{	
		ArrayList<String[]> codes = new ArrayList<String[]>();
		if(arbre.vide())
			return null;
		if(!arbre.info().reste().vide())
		{
			codes.add(new String[]{String.valueOf((char) arbre.info().reste().tete()), code});
			return codes;
		}
		else
		{
			codes.addAll(calculerCodage(arbre.filsGauche(), code + "0"));
			codes.addAll(calculerCodage(arbre.filsDroit(), code + "1"));
			return codes;
		}
	}
	
	public String encoder(String text)
	{	
		if(!valide)
			return "/!\\ Impossible d'encoder le text, veillez à correctement initialiser les racines. /!\\";
		String textEncode = "";
		for(int i = 0; i < text.length(); i++)
		{
			for(String code[] : codes)
			{
				if(code[0].equals(String.valueOf(text.charAt(i))))
					textEncode += code[1];
			}
		}
		return textEncode;
	}
	
	
	public String decoder(String texte)
	{
		if(!valide)
			return "/!\\ Impossible de décoder le text, veillez à correctement initialiser les racines. /!\\";
		int cpt = 0;
		String textDecode = "";

		while(cpt < texte.length())
		{
			
			for(String code[] : codes)
			{
				if(texte.indexOf(code[1], cpt) == cpt)
				{
					cpt+=code[1].length()-1;
					textDecode += code[0];
					break;
				}
			}
			cpt++;
		}
		return textDecode;
	}
	
	
	public ListeArbre getRacines() 
	{
		if(valide)
			return racines;
		return new ListeArbre();
	}
	
	
	public Arbre getArbre()
	{
		if(valide)
			return arbre;
		return new Arbre();
	}
	
	
	public ArrayList<String[]> getCodes()
	{
		if(valide)
			return codes;
		return new ArrayList<String[]>();
	}


	public static void main(String[] args)
	{
		String texteInitial = "Tu n'es encore pour moi qu'un petit garçon tout semblable à cent mille petits garçons. "
				+ "Et je n'ai pas besoin de toi. Et tu n'as pas besoin de moi non plus. Je ne suis pour toi qu'un renard semblable à cent mille renards. "
				+ "Mais, si tu m'apprivoises, nous aurons besoin l'un de l'autre. Tu seras pour moi unique au monde. Je serai pour toi unique au monde.";
		
		char caracteres[] = {'T', 'u', ' ', 'n', '\'', 'e', 's', 'c', 'o', 'r', 'p', 'm', 'i', 'q', 't', 'g', 'a', 'ç', 'b', 'l', 'à', '.', 'E', 'j', 'd', 'J', 'M', ',', 'v'};
		double frequences[] = {2, 25, 66, 25, 8, 33, 24, 3, 23, 16, 11, 10, 22, 4, 16, 2, 18, 2, 7, 11, 2, 7, 2, 1, 7, 2, 1, 2, 1};

		CodageHuffman secret = new CodageHuffman(caracteres, frequences);
		
		String texteEncode = secret.encoder(texteInitial);
		String texteDecode = secret.decoder(texteEncode);
		
		System.out.println("\nTexte n°1 :");
		System.out.println("\n-----------------------| Texte initial |----------------------\n");
		System.out.println(texteInitial);
		System.out.println("\n------------------------| Texte codé |------------------------\n");
		System.out.println(texteEncode);
		System.out.println("\n-----------------------| Texte décodé |-----------------------\n");
		System.out.println(texteDecode);
		
		
		
		/* Tableau des fréquences de la langue française */	
		char caracteresFR[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',' ','~', '_', '-', ',', ';', ':', '!', '?', '.', '·', '\'', '"', '«', '»', '(', ')', '[', ']', '{', '}', '*', '/', '&', '#', '%', '°', '+', '<', '=', '>', '²', 'a', 'á', 'à', 'â', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'ê', 'ë', 'f', 'g', 'h', 'i', 'í', 'î', 'ï', 'j', 'k', 'l', 'm', 'n', 'o', 'ô', 'ö', 'p', 'q', 'r', 's', 't', 'u', 'ù', 'û', 'ü', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N' , 'O' , 'P' , 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		double frequencesFR[] = {0.67, 0.95, 0.57, 0.36, 0.35, 0.36, 0.36, 0.34, 0.38, 0.67, 1.56, 0.04, 0.01, 0.6, 1.02, 0.04, 0.33, 0.01, 0.23, 0.83, 0.05, 0.76, 0.08, 0.04, 0.04, 0.38, 0.38, 0.03, 0.03, 0.03, 0.02, 0.01, 0.07, 0.01, 0.05, 0.01, 0.02, 0.01, 0.05, 0.01, 0.05, 0.01, 5.688, 0.01, 0.31, 0.03, 0.912, 2.544, 0.06, 2.936, 9.68, 1.94, 0.31, 0.08, 0.01, 0.888, 0.984, 0.888, 5.272, 0.01, 0.03, 0.01, 0.272, 0.232, 3.968, 2.096, 5.112, 4.016, 0.04, 0.01, 1.992, 0.52, 4.856, 5.208, 4.736, 3.592, 0.02, 0.02, 0.01, 0.888, 0.136, 0.304, 0.368, 0.12, 1.422, 0.228, 0.636, 0.734, 2.42, 0.222, 0.246, 0.222, 1.318, 0.068, 0.058, 0.992, 0.524, 1.278, 1.004, 0.498, 0.13, 1.214, 1.302, 1.184, 0.898, 0.222, 0.034, 0.076, 0.092, 0.03};	
		
		String texteInitial2 = "Candide, chassé du paradis terrestre, marcha longtemps sans savoir où, pleurant, levant les yeux au ciel, les " + 
				"tournant souvent vers le plus beau des châteaux qui renfermait la plus belle des baronnettes ; il se coucha sans " + 
				"souper au milieu des champs entre deux sillons ; la neige tombait à gros flocons. Candide, tout transi, se " + 
				"traîna le lendemain vers la ville voisine, qui s'appelle Valdberghoff-trarbk-dikdorff, n'ayant point d'argent, " + 
				"mourant de faim et de lassitude. Il s'arrêta tristement à la porte d'un cabaret. Deux hommes habillés de bleu le " + 
				"remarquèrent : « Camarade, dit l'un, voilà un jeune homme très bien fait, et qui a la taille requise. » Ils " + 
				"s'avancèrent vers Candide et le prièrent à dîner très civilement. « Messieurs, leur dit Candide avec une " + 
				"modestie charmante, vous me faites beaucoup d'honneur, mais je n'ai pas de quoi payer mon écot.  Ah ! " + 
				"monsieur, lui dit un des bleus, les personnes de votre figure et de votre mérite ne payent jamais rien : " + 
				"n'avez−vous pas cinq pieds cinq pouces de haut ?  Oui, messieurs, c'est ma taille, dit−il en faisant la " + 
				"révérence.  Ah ! monsieur, mettez−vous à table ; non seulement nous vous défrayerons, mais nous ne " + 
				"souffrirons jamais qu'un homme comme vous manque d'argent ; les hommes ne sont faits que pour se " + 
				"secourir les uns les autres.  Vous avez raison, dit Candide : c'est ce que M. Pangloss m'a toujours dit, et je " + 
				"vois bien que tout est au mieux. » On le prie d'accepter quelques écus, il les prend et veut faire son billet ; on " + 
				"n'en veut point, on se met à table : « N'aimez−vous pas tendrement ?...  Oh ! oui, répondit−il, j'aime " + 
				"tendrement Mlle Cunégonde.  Non, dit l'un de ces messieurs, nous vous demandons si vous n'aimez pas " + 
				"tendrement le roi des Bulgares.  Point du tout, dit-il, car je ne l'ai jamais vu.  Comment ! c'est le plus " + 
				"charmant des rois, et il faut boire à sa santé.  Oh ! très volontiers, messieurs » ; et il boit. « C'en est assez, lui " + 
				"dit−on, vous voilà l'appui, le soutien, le défenseur, le héros des Bulgares ; votre fortune est faite, et votre " + 
				"gloire est assurée. » On lui met sur−le−champ les fers aux pieds, et on le mène au régiment. On le fait tourner " + 
				"à droite, à gauche, hausser la baguette, remettre la baguette, coucher en joue, tirer, doubler le pas, et on lui " + 
				"donne trente coups de bâton ; le lendemain il fait l'exercice un peu moins mal, et il ne reçoit que vingt coups ; " + 
				"le surlendemain on ne lui en donne que dix, et il est regardé par ses camarades comme un prodige. ";
		
		secret.setRacines(caracteresFR, frequencesFR);
		texteEncode = secret.encoder(texteInitial2);
		texteDecode = secret.decoder(texteEncode);
		
		System.out.println("\n\nTexte n°2 :");
		System.out.println("\n-----------------------| Texte initial |----------------------\n");
		System.out.println(texteInitial2);
		System.out.println("\n------------------------| Texte codé |------------------------\n");
		System.out.println(texteEncode);
		System.out.println("\n-----------------------| Texte décodé |-----------------------\n");
		System.out.println(texteDecode);
		
		
		/* optionnel */
		/*	
		secret.getArbre().affichePrefixe();
		for(String[] code : secret.getCodes())
			System.out.println(code[0] + " - " + code[1]);
		ListeArbre.affiche(secret.getRacines());	
		*/
	}
}
