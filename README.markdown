## Criteo Code of Duty 2011

Enoncé du problème disponible à cette [adresse](http://www.guillaumeleone.fr/doc/COD_1_enonce.pdf).

J'ai écrit ce programme juste pour le fun !!? Java etait le langage proposé dont je maitrise le mieux les structures de données.

### Remarques

1. **Ma classe Liste a tous ses attributs public :( #iknowitsbad

2. **L'énoncé n'est pas 100% explicite sur la règle de propagation d'un poid lors d'une étape. Selon un des exemples données, il faut comprendre qu'un poids ne peut être transmis qu'une fois par étape. Dans mon algorithme de parcours de gauche à droite, lorsque je transemet un poids à droite vers une case qui était vide, alors cette case ne put pas transmettre ce poids à son tour lors de la même étape.

Comprenez que ceci est possible:
`0 : (10, 0, 0, 0, 0)
1 : (9, 1, 0, 0, 0)
2 : (8, 1, 1, 0, 0)
3 : (7, 1, 1, 1, 0)
4 : (6, 1, 1, 1, 1)
...`

mais pas:
`0 : (10, 0, 0, 0, 0)
1 : (9, 0, 0, 0, 1)
2 : (8, 0, 0, 0, 2)
3 : (7, 0, 0, 1, 2)
4 : (6, 0, 0, 2, 2)
...`

Pour résoudre ce problème, lors de mon parcours du tableau, j'ai du sauver l'état du tableau à l'étape précédente. Je n'avais jamais effectué cette opération jusqu'a maintenant. Apparemment il existe plusieurs moyens de le faire. J'ai choisi:

``` java start:51 mark:51
int[] step = Arrays.copyOf(previousStep, l.size);
```
