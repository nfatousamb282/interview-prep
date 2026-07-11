# Container With Most Water

## Pattern

Two Pointers

---

# Définition

Le problème consiste à trouver les deux lignes qui permettent de contenir la plus grande quantité d'eau.

L'aire dépend de deux paramètres :

* la distance entre les deux lignes ;
* la plus petite des deux hauteurs.

La formule est :

```
aire = largeur × min(hauteur_gauche, hauteur_droite)
```

---

# Solution naïve

Tester toutes les paires de lignes.

Pour chaque couple :

* calculer la largeur ;
* calculer la hauteur minimale ;
* calculer l'aire.

Conserver la plus grande.

Complexité :

Temps :

```
O(n²)
```

Mémoire :

```
O(1)
```

---

# Observation importante

L'aire dépend de deux paramètres :

* la largeur ;
* la hauteur.

À chaque déplacement d'un pointeur, la largeur diminue forcément.

La seule manière de compenser cette perte est d'espérer obtenir une hauteur plus importante.

---

# Pourquoi déplacer la plus petite ligne ?

Supposons :

```text
1               7
↑               ↑
```

La hauteur est limitée par :

```
1
```

Déplacer la ligne de hauteur 7 ne changera jamais cette limite.

La largeur diminuera.

L'aire ne pourra donc jamais augmenter.

En revanche, déplacer la ligne de hauteur 1 permet éventuellement de trouver une ligne plus haute.

On perd de la largeur, mais on peut gagner suffisamment en hauteur pour obtenir une aire plus grande.

C'est le seul déplacement qui peut améliorer la solution.

---

# Algorithme

1. Initialiser deux pointeurs aux extrémités.
2. Calculer l'aire courante.
3. Mettre à jour l'aire maximale.
4. Déplacer uniquement le pointeur correspondant à la plus petite hauteur.
5. Répéter jusqu'à ce que les deux pointeurs se croisent.

---

# Complexité

Temps :

```
O(n)
```

Chaque pointeur parcourt le tableau une seule fois.

Mémoire :

```
O(1)
```

Aucune structure supplémentaire n'est utilisée.

---

# Bonnes pratiques

Utiliser des noms explicites :

```java
left
right
width
currentArea
maxArea
```

Calculer les valeurs intermédiaires lorsque cela améliore la lisibilité.

Utiliser :

```java
while (left < right)
```

comme condition principale de la boucle.

---

# Pièges

* croire qu'il faut choisir les deux plus grandes hauteurs ;
* oublier que la largeur intervient dans le calcul ;
* déplacer le mauvais pointeur ;
* retenir la règle sans comprendre pourquoi elle fonctionne ;
* oublier de mettre à jour l'aire maximale à chaque itération.

---

# Questions d'entretien

Pourquoi déplace-t-on la plus petite hauteur ?

Pourquoi ne pas déplacer la plus grande ?

Pourquoi cette solution est-elle en O(n) ?

Pourquoi n'a-t-on pas besoin d'une HashMap ?

Que représente réellement l'aire ?

---

# Ce que j'ai appris

Au départ, j'avais tendance à penser que les deux plus grandes hauteurs donneraient forcément la meilleure réponse.

J'ai compris que la largeur est tout aussi importante que la hauteur.

Le véritable raisonnement consiste à comprendre que déplacer la plus grande hauteur ne peut jamais améliorer l'aire puisque la hauteur reste limitée par la plus petite ligne alors que la largeur diminue.

J'ai également retenu qu'un pattern ne doit jamais être appliqué mécaniquement : il faut toujours être capable d'expliquer pourquoi il fonctionne.
