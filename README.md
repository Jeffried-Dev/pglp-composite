[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/-FYP8HtX)
# Pattern Composite

On veut simuler le fonctionnement d’un système de fichiers.
Un fichier est représenté par son nom et sa taille.
Un répertoire est défini par son nom et peut contenir des fichiers et/ou des répertoires.
La base de l’arborescence du système de fichier est le répertoire racine.

On veut pouvoir calculer la taille totale d’un répertoire, i.e. la somme des tailles des fichiers qu'il contient (directement et indirectement).

Le pattern [Composite](https://en.wikipedia.org/wiki/Composite_pattern) sera utilisé pour cela.
Le pattern [Iterator](https://en.wikipedia.org/wiki/Iterator_pattern) sera également implémenté pour permettre l'itération sur les répertoires.

**Remarque** : Vous devez respecter les conventions de codage _Checkstyle_ intégrées dans le build maven.

1. Faites le nécessaire pour que le programme compile (**tous les tests doivent échouer**).
2. **Validez les changements avec `git`.**
3. Vous implémenterez **un à un** les tests unitaires fournis dans les fichiers `XXXTest.java` en prenant soin de valider après chaque réalisation.
