Structure de proposition :
1.	D’abord nous allons proposer des règles en basant sur le dictionnaire des données de taxi jaune fourni par le ‘TLC’ (l’agence).
2.	Ensuite, nous allons calculer une distribution [34] pour éliminer certaines règles qui n’influencent pas réellement le travail, en comparant la mesure de ‘confiance’ attribut à chaque règle, c’est le point ajouter par rapport au méthodes discutées à l’état d’art.
3.	Finalement, nous comparons les résultats, et nous gardons qu’influence trop.
Fonctionnement:
Après la définition des règles nous les appliquerons, les résultats obtenus seront gardés en deux façon (1) la première : le résultat de chaque règle dans un tableau à part, (2) la deuxième : tous les résultats dans un seul tableau (fusionnement des tableaux et élimination des doublons), nous allons par la suite classés les tableaux à part dans une matrice, ou nous aurons toutes les règles appliquées  sur le même document, après nous calculons le support et la confiance à partir de la matrice obtenue.
Pour la distribution nous calculons d’abord la moyenne, ensuite nous allons présenter les indicateurs étudiés graphiquement, d’après ses statistiques nous comparons les résultats et on élimine les enregistrements qui appartient à une fréquence très lointe de la moyenne.
A l'étape suivante de distribution nous retenons l'apparition des règles pour chaque enregistrement, les règles qu’apparient fréquemment, qui ont un degré de confiance élevé en le retient, sinon on les élimine, exemple : conf(R1) > conf(R6), alors la règle 6 sera éliminée.




We need
Nous avons besoin de représenter graphiquement, passenger_ count, trip-distance, trip_time, ou l'axe (x) représente ces derniers et l'axe (y) et leurs fréquences. 
