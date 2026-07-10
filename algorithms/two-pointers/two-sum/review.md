# Two Sum II — Input Array Is Sorted

## Pattern

Two Pointers

---

## Définition

Le pattern Two Pointers consiste à utiliser deux indices qui se déplacent dans une structure de données afin de réduire progressivement l’espace de recherche.

Dans cet exercice, un pointeur commence au début du tableau et l’autre à la fin.

---

## Quand utiliser ce pattern ?

Ce pattern est particulièrement adapté lorsque :

* le tableau est trié ;
* on recherche une paire d’éléments ;
* on compare des valeurs situées à deux extrémités ;
* le déplacement d’un pointeur permet d’éliminer certaines possibilités ;
* on veut éviter une double boucle ou une structure de données supplémentaire.

---

## Comment reconnaître ce pattern ?

Indices présents dans l’énoncé :

* le tableau est trié par ordre croissant ;
* il faut trouver deux nombres ;
* la somme des deux nombres doit atteindre une cible ;
* une seule solution est attendue.

Questions à se poser :

* Le tableau est-il trié ?
* Puis-je commencer avec un pointeur à chaque extrémité ?
* Puis-je savoir quel pointeur déplacer selon la somme obtenue ?
* Ai-je réellement besoin d’une HashMap ?

---

## Solution naïve

La solution la plus simple consiste à tester toutes les paires avec deux boucles imbriquées.

Pour chaque élément, on teste sa somme avec les éléments suivants.

Complexité temporelle :

```text
O(n²)
```

Complexité mémoire :

```text
O(1)
```

Cette solution fonctionne, mais elle ne profite pas du fait que le tableau est trié.

---

## Solution avec une HashMap

Il serait également possible de parcourir le tableau et de mémoriser les valeurs déjà rencontrées dans une HashMap.

Pour chaque nombre, on rechercherait son complément :

```text
complement = target - currentNumber
```

Complexité temporelle moyenne :

```text
O(n)
```

Complexité mémoire :

```text
O(n)
```

Cette solution fonctionne, mais elle utilise une structure de données supplémentaire alors que le tri permet de résoudre le problème avec une mémoire constante.

---

## Solution optimale

On place deux pointeurs :

```text
left  = début du tableau
right = fin du tableau
```

À chaque itération, on calcule :

```text
sum = numbers[left] + numbers[right]
```

Trois situations sont possibles.

### La somme est égale à la cible

On retourne les deux indices.

### La somme est inférieure à la cible

On avance `left`.

Le tableau étant trié, la nouvelle valeur située à gauche sera supérieure ou égale à la précédente. Cela permet d’augmenter la somme.

### La somme est supérieure à la cible

On recule `right`.

Le tableau étant trié, la nouvelle valeur située à droite sera inférieure ou égale à la précédente. Cela permet de diminuer la somme.

---

## Pourquoi le tri est-il important ?

Le tri permet de prendre une décision après chaque comparaison.

Lorsque la somme est trop grande, déplacer le pointeur gauche augmenterait encore la somme. Cela nous éloignerait de la cible.

Il faut donc déplacer le pointeur droit pour réduire la somme.

Lorsque la somme est trop petite, déplacer le pointeur droit diminuerait encore la somme. Il faut donc déplacer le pointeur gauche pour l’augmenter.

Sans tableau trié, il serait impossible de prévoir l’effet du déplacement d’un pointeur.

---

## Complexité

Complexité temporelle :

```text
O(n)
```

À chaque itération, l’un des deux pointeurs se déplace. Chaque pointeur traverse le tableau au maximum une fois.

Complexité mémoire :

```text
O(1)
```

Seuls deux pointeurs et une variable contenant la somme sont nécessaires.

Cette solution a la même complexité temporelle que la solution avec une HashMap, mais utilise moins de mémoire.

---

## Première implémentation

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] res = new int[2];

        while ((numbers[left] + numbers[right]) != target) {
            if ((numbers[left] + numbers[right]) > target) {
                right--;
            } else {
                left++;
            }
        }

        res[0] = left + 1;
        res[1] = right + 1;

        return res;
    }
}
```

---

## Ce qui était bien dans cette implémentation

* Le pattern Two Pointers a été identifié.
* La propriété de tri du tableau a été utilisée.
* La solution fonctionne en `O(n)`.
* Aucune structure de données supplémentaire n’est utilisée.
* Les noms `left` et `right` expriment clairement le rôle des pointeurs.
* La différence entre les indices Java et les indices demandés par l’énoncé a été prise en compte avec `+ 1`.

---

## Points à améliorer

### Éviter de recalculer la somme

L’expression suivante était calculée plusieurs fois :

```java
numbers[left] + numbers[right]
```

Il est préférable de la calculer une seule fois par itération :

```java
long sum = (long) numbers[left] + numbers[right];
```

Cela améliore la lisibilité et évite la répétition.

### Simplifier la condition de boucle

La condition :

```java
while ((numbers[left] + numbers[right]) != target)
```

suppose qu’une solution sera forcément trouvée.

Il est plus explicite d’écrire :

```java
while (left < right)
```

Cette condition représente directement la validité des deux pointeurs.

### Retourner immédiatement le résultat

Il n’est pas nécessaire de créer un tableau `res` avant de connaître la réponse.

On peut retourner directement :

```java
return new int[]{left + 1, right + 1};
```

### Gérer l’absence de solution

L’énoncé LeetCode garantit qu’une solution existe, mais dans du code de production, il est préférable de gérer le cas où aucune paire n’est trouvée.

---

## Version refactorée

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            long sum = (long) numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }

            if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        throw new IllegalArgumentException("No solution found");
    }
}
```

---

## Bonnes pratiques retenues

* Utiliser des noms qui expriment le rôle des variables.
* Calculer une seule fois une expression répétée.
* Utiliser une condition de boucle simple et structurelle.
* Retourner immédiatement lorsqu’un résultat est trouvé.
* Éviter les variables temporaires inutiles.
* Utiliser les propriétés des données avant d’ajouter une structure de données.
* Comparer plusieurs solutions et leurs compromis en temps et en mémoire.

---

## Attention à l’overflow en Java

Une addition entre deux `int` produit également un `int`.

Si le résultat dépasse `Integer.MAX_VALUE`, Java ne lève pas automatiquement d’exception. La valeur déborde et peut devenir négative.

Exemple :

```java
int value = Integer.MAX_VALUE;
System.out.println(value + 1);
```

Résultat :

```text
-2147483648
```

Pour effectuer l’addition dans un type plus grand :

```java
long sum = (long) numbers[left] + numbers[right];
```

Le cast doit être effectué avant l’addition.

Pour détecter explicitement un overflow :

```java
Math.addExact(a, b);
```

Cette méthode lève une `ArithmeticException` en cas de dépassement.

---

## Pièges

* oublier que les indices demandés commencent à `1` ;
* utiliser une double boucle sans exploiter le tri ;
* utiliser une HashMap alors qu’elle n’est pas nécessaire ;
* déplacer le mauvais pointeur ;
* mémoriser la règle sans comprendre pourquoi elle fonctionne ;
* recalculer plusieurs fois la même somme ;
* oublier les risques d’overflow lors de l’addition de deux `int` ;
* utiliser une condition de boucle qui suppose implicitement qu’une solution existe.

---

## Formulation en entretien

> Je commence avec un pointeur au début du tableau et un autre à la fin. Comme le tableau est trié, je peux ajuster la somme en déplaçant le bon pointeur. Si la somme est trop petite, j’avance le pointeur gauche pour l’augmenter. Si elle est trop grande, je recule le pointeur droit pour la diminuer. Lorsqu’elle correspond à la cible, je retourne les indices. Cette solution fonctionne en O(n) en temps et O(1) en mémoire.

---

## Questions d’entretien

* Pourquoi le tableau doit-il être trié ?
* Pourquoi avancer `left` augmente-t-il la somme ?
* Pourquoi reculer `right` diminue-t-il la somme ?
* Une HashMap fonctionnerait-elle ?
* Pourquoi Two Pointers est-il préférable à une HashMap ici ?
* Quelle est la complexité temporelle ?
* Quelle est la complexité mémoire ?
* Pourquoi les indices retournés ont-ils un décalage de `1` ?
* Que peut-il se passer si l’addition de deux `int` dépasse `Integer.MAX_VALUE` ?
* Comment gérerais-tu le cas où aucune solution n’existe ?

---

## Ce que j’ai appris

* Une solution en `O(n)` n’est pas automatiquement optimale sur tous les critères.
* Une HashMap donne une solution en `O(n)`, mais utilise `O(n)` de mémoire.
* Le tableau trié permet d’obtenir `O(n)` en temps et `O(1)` en mémoire.
* Le tri permet de prévoir l’effet du déplacement de chaque pointeur.
* Il faut comparer les compromis entre les solutions, pas uniquement leur complexité temporelle.
* Les types numériques ont des limites et une addition de deux `int` peut déborder.
* La lisibilité du code peut être améliorée même lorsque l’algorithme est déjà correct.
