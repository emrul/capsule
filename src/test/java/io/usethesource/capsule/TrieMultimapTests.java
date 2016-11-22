/**
 * Copyright (c) Michael Steindorfer <Centrum Wiskunde & Informatica> and Contributors.
 * All rights reserved.
 *
 * This file is licensed under the BSD 2-Clause License, which accompanies this project
 * and is available under https://opensource.org/licenses/BSD-2-Clause.
 */
package io.usethesource.capsule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import io.usethesource.capsule.api.deprecated.ImmutableSetMultimap;
import io.usethesource.capsule.experimental.multimap.TrieSetMultimap_HCHAMP;

public class TrieMultimapTests {

  final static int size = 64;

  @Test
  public void testInsertTwoTuplesThatShareSameKey() {
    ImmutableSetMultimap<Integer, String> map =
        TrieSetMultimap_HCHAMP.<Integer, String>of().__insert(1, "x").__insert(1, "y");

    assertEquals(2, map.size());
    assertTrue(map.containsKey(1));
  }

  @Test
  public void testInsertTwoTuplesWithOneRemoveThatShareSameKeyX() {
    ImmutableSetMultimap<Integer, String> map = TrieSetMultimap_HCHAMP
        .<Integer, String>of().__insert(1, "x").__insert(1, "y").__removeEntry(1, "x");

    assertEquals(1, map.size());
    assertTrue(map.containsKey(1));
  }

  @Test
  public void testInsertTwoTuplesWithOneRemoveThatShareSameKeyY() {
    ImmutableSetMultimap<Integer, String> map = TrieSetMultimap_HCHAMP
        .<Integer, String>of().__insert(1, "x").__insert(1, "y").__removeEntry(1, "y");

    assertEquals(1, map.size());
    assertTrue(map.containsKey(1));
  }

  @Test
  public void testInsertTwoTuplesWithOneRemoveThatShareSameKeyXY() {
    ImmutableSetMultimap<Integer, String> map =
        TrieSetMultimap_HCHAMP.<Integer, String>of().__insert(1, "x").__insert(1, "y")
            .__removeEntry(1, "x").__removeEntry(1, "y");

    assertEquals(0, map.size());
    assertFalse(map.containsKey(1));
  }

  @Test
  public void testInsertTwoTuplesThatShareSameKey_Iterate() {
    ImmutableSetMultimap<Integer, String> map =
        TrieSetMultimap_HCHAMP.<Integer, String>of().__insert(1, "x").__insert(1, "y");

    Collection<String> values = map.values();

    assertEquals(2, values.size());
    assertTrue(values.contains("x"));
    assertTrue(values.contains("y"));
  }

}
