package io.github.lolens.inno1.listener;


public interface RepositoryListener<E> {
  default void onSave(E savedElement, E oldElement) {}
  default void onDelete(E element) {}
}