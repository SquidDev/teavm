/*
 *  Copyright 2014 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.classlib.java.util;

import java.util.Objects;
import org.teavm.classlib.java.util.function.TUnaryOperator;

public interface TList<E> extends TCollection<E> {
    boolean addAll(int index, TCollection<? extends E> c);

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    TListIterator<E> listIterator();

    TListIterator<E> listIterator(int index);

    TList<E> subList(int fromIndex, int toIndex);

    default void replaceAll(TUnaryOperator<E> operator) {
        TListIterator<E> iter = listIterator();
        while (iter.hasNext()) {
            iter.set(operator.apply(iter.next()));
        }
    }

    default void sort(TComparator<? super E> c) {
        TCollections.sort(this, c);
    }

    static <E> TList<E> of() {
        return TCollections.emptyList();
    }

    static <E> TList<E> of(E e) {
        return TCollections.singletonList(e);
    }

    static <E> TList<E> of(E e1, E e2) {
        Objects.requireNonNull(e1);
        Objects.requireNonNull(e2);
        return new TTemplateCollections.TwoElementsList<>(e1, e2);
    }

    static <E> TList<E> of(E e1, E e2, E e3) {
        Objects.requireNonNull(e1);
        Objects.requireNonNull(e2);
        Objects.requireNonNull(e3);
        return new TTemplateCollections.ImmutableArrayList<>(e1, e2, e3);
    }

    static <E> TList<E> of(E e1, E e2, E e3, E e4) {
        Objects.requireNonNull(e1);
        Objects.requireNonNull(e2);
        Objects.requireNonNull(e3);
        Objects.requireNonNull(e4);
        return new TTemplateCollections.ImmutableArrayList<>(e1, e2, e3, e4);
    }

    static <E> TList<E> of(E e1, E e2, E e3, E e4, E e5) {
        Objects.requireNonNull(e1);
        Objects.requireNonNull(e2);
        Objects.requireNonNull(e3);
        Objects.requireNonNull(e4);
        Objects.requireNonNull(e5);
        return new TTemplateCollections.ImmutableArrayList<>(e1, e2, e3, e4, e5);
    }

    static <E> TList<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        Objects.requireNonNull(e1);
        Objects.requireNonNull(e2);
        Objects.requireNonNull(e3);
        Objects.requireNonNull(e4);
        Objects.requireNonNull(e5);
        Objects.requireNonNull(e6);
        return new TTemplateCollections.ImmutableArrayList<>(e1, e2, e3, e4, e5, e6);
    }

    static <E> TList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
        Objects.requireNonNull(e1);
        Objects.requireNonNull(e2);
        Objects.requireNonNull(e3);
        Objects.requireNonNull(e4);
        Objects.requireNonNull(e5);
        Objects.requireNonNull(e6);
        Objects.requireNonNull(e7);
        return new TTemplateCollections.ImmutableArrayList<>(e1, e2, e3, e4, e5, e6, e7);
    }

    static <E> TList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        Objects.requireNonNull(e1);
        Objects.requireNonNull(e2);
        Objects.requireNonNull(e3);
        Objects.requireNonNull(e4);
        Objects.requireNonNull(e5);
        Objects.requireNonNull(e6);
        Objects.requireNonNull(e7);
        Objects.requireNonNull(e8);
        return new TTemplateCollections.ImmutableArrayList<>(e1, e2, e3, e4, e5, e6, e7, e8);
    }

    static <E> TList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        Objects.requireNonNull(e1);
        Objects.requireNonNull(e2);
        Objects.requireNonNull(e3);
        Objects.requireNonNull(e4);
        Objects.requireNonNull(e5);
        Objects.requireNonNull(e6);
        Objects.requireNonNull(e7);
        Objects.requireNonNull(e8);
        Objects.requireNonNull(e9);
        return new TTemplateCollections.ImmutableArrayList<>(e1, e2, e3, e4, e5, e6, e7, e8, e9);
    }

    static <E> TList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        Objects.requireNonNull(e1);
        Objects.requireNonNull(e2);
        Objects.requireNonNull(e3);
        Objects.requireNonNull(e4);
        Objects.requireNonNull(e5);
        Objects.requireNonNull(e6);
        Objects.requireNonNull(e7);
        Objects.requireNonNull(e8);
        Objects.requireNonNull(e9);
        Objects.requireNonNull(e10);
        return new TTemplateCollections.ImmutableArrayList<>(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }

    @SafeVarargs
    static <E> TList<E> of(E... elements) {
        for (E element : elements) {
            Objects.requireNonNull(element);
        }
        return new TTemplateCollections.ImmutableArrayList<>(elements.clone());
    }

    static <E> TList<E> copyOf(TCollection<? extends E> collection) {
        return new TTemplateCollections.ImmutableArrayList<>(collection);
    }
}
