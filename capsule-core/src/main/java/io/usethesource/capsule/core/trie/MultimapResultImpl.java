/**
 * Copyright (c) Michael Steindorfer <Centrum Wiskunde & Informatica> and Contributors.
 * All rights reserved.
 *
 * This file is licensed under the BSD 2-Clause License, which accompanies this project
 * and is available under https://opensource.org/licenses/BSD-2-Clause.
 */
package io.usethesource.capsule.core.trie;

import java.util.EnumSet;
import java.util.Optional;

import static io.usethesource.capsule.core.trie.MultimapResult.Modification.NOTHING;

final class MultimapResultImpl<K, V, C> implements
    MultimapResult<K, V, C> {

  private Modification modificationEffect = NOTHING;
  private EnumSet<Modification> modificationDetails = EnumSet.noneOf(Modification.class);
  private Optional<Integer> sizeDelta = Optional.empty();
  private Optional<C> evictedPayload = Optional.empty();

  @Override
  public Modification getModificationEffect() {
    return modificationEffect;
  }

  @Override
  public EnumSet<Modification> getModificationDetails() {
    return modificationDetails;
  }

  @Override
  public Optional<Integer> sizeDelta() {
    return sizeDelta;
  }

  @Override
  public Optional<C> getEvictedPayload() {
    return evictedPayload;
  }

  @Override
  public void modified(Modification modificationEffect,
      EnumSet<Modification> modificationDetails) {
    this.modificationEffect = modificationEffect;
    this.modificationDetails = modificationDetails;
  }

  @Override
  public void modified(Modification modificationEffect,
      EnumSet<Modification> modificationDetails, int sizeDelta) {
    this.modificationEffect = modificationEffect;
    this.modificationDetails = modificationDetails;
    this.sizeDelta = Optional.of(sizeDelta);
  }

  @Override
  public void modified(Modification modificationEffect, EnumSet<Modification> modificationDetails,
      C evictedPayload) {
    this.modificationEffect = modificationEffect;
    this.modificationDetails = modificationDetails;
    this.evictedPayload = Optional.of(evictedPayload);
  }

}
