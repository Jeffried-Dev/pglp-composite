package fr.uvsq.pglp.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe Directory.
 */
public class Directory extends Entry implements Iterable<Entry> {
  private final String name;
  private final List<Entry> entries;

  /**
   * Methode main de la classe.
   *
   * @param name le nom
   */
  public Directory(String name) {
    if (name == null) {
      throw new NullPointerException("The name can't be null");
    } else if (name.isEmpty()) {
      throw new IllegalArgumentException("The name can't be empty");
    }
    this.name = name;
    this.entries = new ArrayList<>();
  }

  /**
   * Methode pour la récursivité de la classe.
   *
   * @return Iterator de Entry.
   */
  @Override
  public Iterator<Entry> iterator() {
    return entries.iterator();
  }

  /**
   * Methode pour récupérer le nom.
   *
   * @return String le nom.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Methode pour récupérer la taille.
   *
   * @return int la taille.
   */
  @Override
  public int getSize() {
    int totalSize = 0;
    for (Entry entry : entries) {
      totalSize += entry.getSize();
    }
    return totalSize;
  }

  /**
   * Methode pour ajouter un entry.
   *
   * @param entry L'entrée à ajouter.
   */
  public void add(Entry entry) {
    if (isDescendant(entry)) {
      throw new IllegalArgumentException("A directory can't be added to a descendant of itself");
    }else if (entry == this) {
      throw new IllegalArgumentException("A directory can't be added to itself");
    }
    entries.add(entry);
  }

  /**
   * Methode verifier la descendance.
   *
   * @param entry1 l'entree
   */
  private boolean isDescendant(Entry entry1) {
    for (Entry entry : entries) {
      if (entry instanceof Directory) {
        Directory subDirectory = (Directory) entry;
        if(entry == entry1 || subDirectory.isDescendant(entry1)){
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Methode verifier l'existence d'un fichier.
   *
   * @param file le fichier
   *
   * @return boolean.
   */
  public boolean contains(File file) {
    for (Entry entry : entries) {
      if (entry instanceof File && entry.getName().equals(file.getName())) {
        return true;
      } else if (entry instanceof Directory subDirectory) {
        if (subDirectory.contains(file)) {
          return true;
        }
      }
    }
    return false;
  }

  private class DirectoryIterator implements Iterator<Entry> {
    private Iterator<Entry> it;

    private DirectoryIterator() {
      this.it = entries.iterator();
    }

    @Override
    public boolean hasNext() {
      return it.hasNext();
    }

    @Override
    public Entry next() {
      Entry entry = it.next();
      if (entry instanceof Directory subDirectory) {
        for (Entry subEntry : subDirectory) {
          it = subDirectory.entries.iterator();
          return subEntry;
        }
      }
      return entry;
    }
  }
}
