/**
 * Copyright (c) Michael Steindorfer <Centrum Wiskunde & Informatica> and Contributors.
 * All rights reserved.
 *
 * This file is licensed under the BSD 2-Clause License, which accompanies this project
 * and is available under https://opensource.org/licenses/BSD-2-Clause.
 */
package io.usethesource.capsule.core.converter;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

import io.usethesource.capsule.api.experimental.Set;
import io.usethesource.capsule.util.collection.AbstractImmutableSet;

public class SetToLegacySetConverter<K> extends AbstractImmutableSet<K> implements io.usethesource.capsule.Set.Immutable<K> {

  private final Set.Immutable<K> immutableSet;

  private SetToLegacySetConverter(final Set.Immutable<K> immutableSet) {
    this.immutableSet = immutableSet;
  }

  public static final <K> io.usethesource.capsule.Set.Immutable<K> adapt(final Set.Immutable<K> immutableSet) {
    return new SetToLegacySetConverter<K>(immutableSet);
  }

  @Override
  public boolean containsAll(Collection<?> collection) {
    if (collection instanceof Set) {
      final Set<K> set = (Set<K>) collection;
      return immutableSet.containsAll(set);
    } else {
      return collection.stream().allMatch(immutableSet::contains);
    }
  }

  @Override
  public K get(Object o) {
    return (K) immutableSet.apply((K) o);
  }

  @Override
  public io.usethesource.capsule.Set.Immutable<K> __insert(K key) {
    return adapt(immutableSet.insert(key));
  }

  @Override
  public io.usethesource.capsule.Set.Immutable<K> __insertAll(java.util.Set<? extends K> set) {
    final Set.Transient<K> tmp = immutableSet.asTransient();
    set.forEach(tmp::insert);
    return adapt(tmp.asImmutable());
  }

  @Override
  public io.usethesource.capsule.Set.Immutable<K> __remove(K key) {
    return adapt(immutableSet.remove(key));
  }

  @Override
  public io.usethesource.capsule.Set.Immutable<K> __removeAll(java.util.Set<? extends K> set) {
    final Set.Transient<K> tmp = immutableSet.asTransient();
    set.forEach(tmp::remove);
    return adapt(tmp.asImmutable());
  }

  @Override
  public io.usethesource.capsule.Set.Immutable<K> __retainAll(java.util.Set<? extends K> set) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public Iterator<K> keyIterator() {
    return immutableSet.iterator();
  }

  @Override
  public boolean isTransientSupported() {
    return false;
  }

  @Override
  public io.usethesource.capsule.Set.Transient<K> asTransient() {
    return null;
  }

  @Override
  public int size() {
    return Math.toIntExact(immutableSet.size());
  }

  @Override
  public boolean isEmpty() {
    return immutableSet.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return immutableSet.contains(o);
  }

  @Override
  public Iterator<K> iterator() {
    return immutableSet.iterator();
  }

  @Override
  public Object[] toArray() {
    return immutableSet.stream().toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return immutableSet.stream().toArray(
        size -> (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size));
  }

  @Override
  public int hashCode() {
    return stream().mapToInt(K::hashCode).sum();
  }

  @Override
  public boolean equals(Object other) {
    /**
     * NOTE: different semantic between Immutable and Set.Immutable (the former
     * {@link #equals(Object) with {@link java.util.Set}, the latter does not).
     */
    // return immutableSet.equals(other);

    throw new UnsupportedOperationException("Unsupported difference in semantics.");
  }

  @Override
  public Spliterator<K> spliterator() {
    return immutableSet.spliterator();
  }

  @Override
  public Stream<K> stream() {
    return immutableSet.stream();
  }

  @Override
  public Stream<K> parallelStream() {
    return immutableSet.parallelStream();
  }

  @Override
  public void forEach(Consumer<? super K> action) {
    immutableSet.forEach(action);
  }

}
