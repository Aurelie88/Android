eCommerce

Damien GAIGA et Aur�lie CUNY

***************************
/ R�partition des t�ches  /
***************************

R�partition globale des t�ches � 50/50


Fonctionnalit�s du programme:


***************************
/ Navigation		  /
***************************

Passer d'un onglet � l'autre:

- Swipe sur les listView pour passer d'un onglet � l'autre
- Clic sur le titre de l'onglet (pas de filtrage des donn�es)
- Clic sur l'icone Burger et puis sur le nom de l'onglet voulu (pas de filtrage des donn�es)



***************************
/ Gestion des cat�gories. /
***************************

La listView affiche l'�cone associ�e ainsi que le nom de la cat�gorie.

Un simple clic sur une cat�gorie bascule vers l'onglet des articles en appliquant un filtre par rapport � la cat�gorie choisie.

Un clic long sur une cat�gorie permet d'afficher les d�tails de la cat�gorie.
L'icone crayon permet de modifier la cat�gorie. Il est possible de choisir une image sur l'appareil et de l'uploader sur le seveur. L'image sera r�cup�r� par l'application lorsque n�cessaire.

L'icone corbeille permet de supprimer une cat�gorie (affiche un avertissement au pr�alable).

Le floating action button permet la cr�ation d'une nouvelle cat�gorie. Lors de la cr�ation, un contr�le des donn�es est effectu� avant validation (le nom de la cat�gorie ne peut-�tre vide).C'est aussi le cas pour la modification.



************************
/ Gestion des articles /
************************

La listView affiche l'�cone associ�e ainsi que le nom de l'article.

Un simple clic sur un article bascule vers l'onglet des promotions en appliquant un filtre par rapport � l'article choisi. Il n'y qu'une promotion possible par article.

Un clic long sur un article permet d'afficher les d�tails de l'article.

L'icone crayon permet de modifier l'article. Il est possible de choisir une image sur l'appareil et de l'uploader sur le seveur. L'image sera r�cup�r� par l'application lorsque n�cessaire.

L'icone corbeille permet de supprimer un article (affiche un avertissement au pr�alable).

Le floating action button permet la cr�ation d'un nouvel article. Lors de la cr�ation, un contr�le des donn�es est effectu� avant validation (le nom de l'article, la r�f�rence et le prix ne peuvent-�tre vides). Le prix impose une valeur num�rique sup�rieure ou �gale � 0. Une image doit-�tre ajout� � la cr�ation de l'article. La cat�gorie doit �tre choisie par l'utilisateur, dans le cas contraire, c'est le premier �l�ment de la liste qui sera utilis� comme cat�gorie.C'est aussi le cas pour la modification.


**************************
/ Gestion des promotions /
**************************

La listView affiche la promotion accord�e en pourcent ainsi que la date de d�but et de fin de la promotion.

PAS d'action au simple clic

Un clic long sur une promotion permet d'afficher les d�tails de la promotion.

L'icone crayon permet de modifier la promotion.

L'icone corbeille permet de supprimer une promtion (affiche un avertissement au pr�alable).

Le floating action button permet la cr�ation d'une nouvelle promotion. Lors de la cr�ation, un contr�le des donn�es est effectu� avant validation (La date de d�but ne peut-�tre post�rieure � la date de fin, la remise est une valeur num�rique qui ne peut-�tre sup�rieur � 100% ou inf�rieur � 0%). La promotion doit s'appliquer � au moins 1 article. C'est aussi le cas pour la modification.



***************************
/ Am�lioration � apporter /
***************************

Revoir le passage d'un �cran � l'autre (ex: retour de modification d'un article renvoie sur l'�cran des cat�gories)
Am�liorer le formatage des prix.
Meilleur gestion de la rotation d'�cran (perte de la fonction filtre apr�s rotation, on doit pouvoir conserver le fragment ou l'�tat du fragment)
Am�liorer les performances (par exemple, en chargeant les images sur l'�cran de d�marrage (pour le moment uniquement esth�tique)
Partie Clients/Ventes � faire
Refactoring
