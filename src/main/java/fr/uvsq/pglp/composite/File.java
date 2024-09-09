package fr.uvsq.pglp.composite;

/**
 * Classe File.
 */
public class File extends Entry {
  private final String name;
  private final int size;

  /**
   * Methode main de la classe.
   */
  public File(String name, int size) {
    if (name == null) {
      throw new NullPointerException("The name can't be null");
    } else if (name.isEmpty()) {
      throw new IllegalArgumentException("The name can't be empty");
    } else if (size < 0) {
      throw new IllegalArgumentException("The file size can't be negative");
    }
    this.name = name;
    this.size = size;
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
    return size;
  }
}
