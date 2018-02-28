eCommerce

Damien GAIGA et Aurélie CUNY

***************************
/ Répartition des tâches  /
***************************

Répartition globale des tâches à 50/50


Fonctionnalités du programme:


***************************
/ Navigation		  /
***************************

Passer d'un onglet à l'autre:

- Swipe sur les listView pour passer d'un onglet à l'autre
- Clic sur le titre de l'onglet (pas de filtrage des données)
- Clic sur l'icone Burger et puis sur le nom de l'onglet voulu (pas de filtrage des données)



***************************
/ Gestion des catégories. /
***************************

La listView affiche l'îcone associée ainsi que le nom de la catégorie.

Un simple clic sur une catégorie bascule vers l'onglet des articles en appliquant un filtre par rapport à la catégorie choisie.

Un clic long sur une catégorie permet d'afficher les détails de la catégorie.
L'icone crayon permet de modifier la catégorie. Il est possible de choisir une image sur l'appareil et de l'uploader sur le seveur. L'image sera récupéré par l'application lorsque nécessaire.

L'icone corbeille permet de supprimer une catégorie (affiche un avertissement au préalable).

Le floating action button permet la création d'une nouvelle catégorie. Lors de la création, un contrôle des données est effectué avant validation (le nom de la catégorie ne peut-être vide).C'est aussi le cas pour la modification.



************************
/ Gestion des articles /
************************

La listView affiche l'îcone associée ainsi que le nom de l'article.

Un simple clic sur un article bascule vers l'onglet des promotions en appliquant un filtre par rapport à l'article choisi. Il n'y qu'une promotion possible par article.

Un clic long sur un article permet d'afficher les détails de l'article.

L'icone crayon permet de modifier l'article. Il est possible de choisir une image sur l'appareil et de l'uploader sur le seveur. L'image sera récupéré par l'application lorsque nécessaire.

L'icone corbeille permet de supprimer un article (affiche un avertissement au préalable).

Le floating action button permet la création d'un nouvel article. Lors de la création, un contrôle des données est effectué avant validation (le nom de l'article, la référence et le prix ne peuvent-être vides). Le prix impose une valeur numérique supérieure ou égale à 0. Une image doit-être ajouté à la création de l'article. La catégorie doit être choisie par l'utilisateur, dans le cas contraire, c'est le premier élément de la liste qui sera utilisé comme catégorie.C'est aussi le cas pour la modification.


**************************
/ Gestion des promotions /
**************************

La listView affiche la promotion accordée en pourcent ainsi que la date de début et de fin de la promotion.

PAS d'action au simple clic

Un clic long sur une promotion permet d'afficher les détails de la promotion.

L'icone crayon permet de modifier la promotion.

L'icone corbeille permet de supprimer une promtion (affiche un avertissement au préalable).

Le floating action button permet la création d'une nouvelle promotion. Lors de la création, un contrôle des données est effectué avant validation (La date de début ne peut-être postérieure à la date de fin, la remise est une valeur numérique qui ne peut-être supérieur à 100% ou inférieur à 0%). La promotion doit s'appliquer à au moins 1 article. C'est aussi le cas pour la modification.



***************************
/ Amélioration à apporter /
***************************

Revoir le passage d'un écran à l'autre (ex: retour de modification d'un article renvoie sur l'écran des catégories)
Améliorer le formatage des prix.
Meilleur gestion de la rotation d'écran (perte de la fonction filtre aprés rotation, on doit pouvoir conserver le fragment ou l'état du fragment)
Améliorer les performances (par exemple, en chargeant les images sur l'écran de démarrage (pour le moment uniquement esthétique)
Partie Clients/Ventes à faire
Refactoring
