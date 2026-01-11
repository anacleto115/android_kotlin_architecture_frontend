
package com.example.lib_utilities.Utilities

import java.util.Collections
import java.util.function.Function
import java.util.function.Predicate
import java.util.function.ToIntFunction
import java.util.stream.Collectors
import java.util.stream.Stream

public class KLinqList<T>
{
    private var iterable: Collection<T>? = null;
    private var listGeneric: List<T>? = null;

    public constructor(iterable: Collection<T>) { this.iterable = iterable; }
    public constructor(iterable: Collection<T>, listGeneric: List<T>)
    {
        this.iterable = iterable;
        this.listGeneric = listGeneric;
    }

    public fun setList(iterable: Collection<T>)
    {
        this.iterable = iterable;
        listGeneric = listGeneric;
    }

    public fun toList(): List<T>
    {
        if (listGeneric == null)
            listGeneric = ArrayList(iterable);
        return listGeneric as List<T>;
    }

    public fun toArray(): Array<Any?>
    {
        var response: Array<Any?> = Array(iterable!!.size) { null };
        var iterator = iterable!!.iterator();
        var pos = 0;
        while (iterator.hasNext())
        {
            response[pos] = iterator.next();
            pos++;
        }
        return response;
    }

    public fun where(expresion: Predicate<in T?>? = null): KLinqList<T>
    {
        if (expresion == null)
            return this;

        listGeneric = ArrayList();
        var iterator: Iterator<*>? = iterable!!.iterator();
        var item: T? = null;
        while (iterator!!.hasNext())
        {
            item = iterator.next() as T;
            if (expresion.test(item))
                (listGeneric!! as ArrayList).add(item);
        }
        iterator = null;
        setList(listGeneric!!);
        return this; // KLinqList<T>(listGeneric!!, listGeneric!!);
    }

    public fun select(expresion: Predicate<in T?>? = null): List<Boolean>
    {
        if (expresion == null)
            return java.util.ArrayList();

        var response: MutableList<Boolean> = java.util.ArrayList();
        var iterator: Iterator<*>? = iterable!!.iterator();
        var item: T? = null;
        while (iterator!!.hasNext())
        {
            item = iterator.next() as T;
            if (expresion.test(item))
                response.add(true);
            else
                response.add(false);
        }
        iterator = null;
        return response;
    }

    public fun orderBy(expresion: Comparator<in T>?): List<T>?
    {
        if (expresion == null)
            return listGeneric;

        listGeneric = java.util.ArrayList(iterable);
        Collections.sort(listGeneric, expresion);
        return listGeneric;
    }

    public fun orderByDesc(expresion: Comparator<in T>?): List<T>?
    {
        if (expresion == null)
            return listGeneric;

        listGeneric = java.util.ArrayList(iterable);
        Collections.sort(listGeneric, expresion);
        Collections.reverse(listGeneric);
        return listGeneric;
    }

    public fun reverse(): List<T>?
    {
        Collections.reverse(listGeneric);
        return listGeneric;
    }

    // Any, All, Contains -----------------------------------------------------
    public fun all(expresion: Predicate<in T?>?): Boolean
    {
        if (expresion == null)
            return false;
        var response: List<Boolean> = java.util.ArrayList();
        var iterator: Iterator<*>? = iterable!!.iterator();
        var item: T? = null;
        while (iterator!!.hasNext())
        {
            item = iterator.next() as T;
            if (!expresion.test(item))
            {
                iterator = null;
                return false;
            }
        }
        iterator = null;
        return true;
    }

    public fun constain(obj: T): Boolean
    {
        listGeneric = java.util.ArrayList(iterable);
        listGeneric = listGeneric!!.stream()
            .filter { x: T -> x === obj }.collect(Collectors.toList());
        return false;
    }

    public fun any(expresion: Predicate<in T?>?): Boolean
    {
        if (expresion == null)
            return false;
        var response: List<Boolean> = java.util.ArrayList();
        var iterator: Iterator<*>? = iterable!!.iterator();
        var item: T? = null;
        while (iterator!!.hasNext())
        {
            item = iterator.next() as T;
            if (expresion.test(item))
            {
                iterator = null;
                return true;
            }
        }
        iterator = null;
        return false;
    }

    // Join -------------------------------------------------------------------
    public fun join(expression: Function<in T, out Stream<out T>?>?): KLinqList<T>
    {
        listGeneric = java.util.ArrayList(iterable);
        listGeneric = listGeneric!!.toList().stream().flatMap(expression).collect(Collectors.toList());
        setList(listGeneric!!);
        return this; // KLinqList<T>(listGeneric!!, listGeneric!!);
    }

    // GroupBy, ToLookup ------------------------------------------------------
    public fun groupBy(expresion: (T) -> Any): Map<Any, Int> { return toLookup(expresion); }

    public fun toLookup(expresion: (T) -> Any): Map<Any, Int>
    {
        listGeneric = java.util.ArrayList(iterable);
        return listGeneric!!.groupingBy(expresion).eachCount();
    }

    // Take, Skip, TakeWhile, SkipWhile ---------------------------------------
    public fun take(size: Int): KLinqList<T>
    {
        listGeneric = java.util.ArrayList();
        var iterator: Iterator<*>? = iterable!!.iterator();
        var item: T? = null;
        var count = 0;
        while (iterator!!.hasNext())
        {
            count++;
            item = iterator.next() as T;
            (listGeneric as java.util.ArrayList).add(item);
            if (count >= size)
            {
                iterator = null;
                return KLinqList<T>(listGeneric!!, listGeneric!!);
            }
        }
        iterator = null;
        setList(listGeneric!!);
        return this; // KLinqList<T>(listGeneric!!, listGeneric!!);
    }

    public fun skip(size: Int): KLinqList<T>
    {
        listGeneric = java.util.ArrayList();
        var iterator: Iterator<*>? = iterable!!.iterator();
        var item: T? = null;
        var count = 0;
        while (iterator!!.hasNext())
        {
            count++;
            item = iterator.next() as T;
            if (count <= size)
                continue;
            (listGeneric as java.util.ArrayList).add(item);
        }
        iterator = null;
        setList(listGeneric!!);
        return this; // KLinqList<T>(listGeneric!!, listGeneric!!);
    }

    public fun takeWhile(expresion: Predicate<in T?>?): KLinqList<T> { return where(expresion); }

    public fun skipWhile(expresion: Predicate<in T?>?): KLinqList<T>
    {
        if (expresion == null)
            return this;

        listGeneric = java.util.ArrayList();
        var iterator: Iterator<*>? = iterable!!.iterator();
        var item: T? = null;
        while (iterator!!.hasNext())
        {
            item = iterator.next() as T;
            if (!expresion.test(item))
                (listGeneric as java.util.ArrayList).add(item);
        }
        iterator = null;
        setList(listGeneric!!);
        return this; // KLinqList<T>(listGeneric!!, listGeneric!!);
    }

    // Distinct, Union, Intersect, Except -------------------------------------
    public fun union(list: List<T>?): KLinqList<T>
    {
        if (list == null)
            return this;

        listGeneric = java.util.ArrayList(iterable);
        (listGeneric as java.util.ArrayList).addAll(list);
        setList(listGeneric!!);
        return this; // KLinqList<T>(listGeneric!!, listGeneric!!);
    }

    public fun intersect(list: List<T>?): KLinqList<T>
    {
        if (list == null)
            return this;

        listGeneric = java.util.ArrayList(iterable);
        (listGeneric as java.util.ArrayList).retainAll(list);
        setList(listGeneric!!);
        return this; // KLinqList<T>(listGeneric!!, listGeneric!!);
    }

    public fun distinct(): KLinqList<T>
    {
        listGeneric = java.util.ArrayList(iterable);
        listGeneric = listGeneric!!.stream().distinct().collect(Collectors.toList());
        setList(listGeneric!!);
        return this; // KLinqList<T>(listGeneric!!, listGeneric!!);
    }

    public fun except(list: List<T>?): KLinqList<T>
    {
        if (list == null)
            return this;

        listGeneric = java.util.ArrayList(iterable);
        (listGeneric as java.util.ArrayList).removeAll(list);
        setList(listGeneric!!);
        return this; // KLinqList<T>(listGeneric!!, listGeneric!!);
    }


    // First, FirstOrDefault, Last, LastOrDefault, ElementAt, ElementAtOrDefault
    // Single, SingleOrDefault -------------------------------------------------
    public fun first(): T? { return first(null); }
    public fun firstOrDefault(): T?  { return first(null); }
    public fun firstOrDefault(expresion: Predicate<in T?>?): T? { return first(expresion); }
    public fun first(expresion: Predicate<in T?>?): T?
    {
        listGeneric = java.util.ArrayList();
        var iterator: Iterator<*>? = iterable!!.iterator();
        var item: T? = null;
        while (iterator!!.hasNext())
        {
            item = iterator.next() as T;
            if (expresion == null || expresion.test(item))
            {
                iterator = null;
                return item;
            }
        }
        iterator = null;
        return null;
    }

    public fun last(): T? { return last(null); }
    public fun lastOrDefault(): T? { return last(null); }
    public fun lastOrDefault(expresion: Predicate<in T?>?): T? { return last(expresion); }
    public fun last(expresion: Predicate<in T?>?): T?
    {
        listGeneric = java.util.ArrayList(iterable);
        Collections.reverse(listGeneric);
        var iterator: Iterator<*>? = listGeneric!!.iterator();
        var item: T? = null;
        while (iterator!!.hasNext())
        {
            item = iterator.next() as T;
            if (expresion == null || expresion.test(item))
            {
                iterator = null;
                return item;
            }
        }
        iterator = null;
        return item;
    }

    public fun elementAtOrDefault(position: Int): T? { return elementAt(position); }
    public fun elementAt(position: Int): T?
    {
        listGeneric = java.util.ArrayList(iterable);
        if (position > listGeneric!!.size)
            return null;
        return listGeneric!!.get(position);
    }

    public fun single(): T { return single(null); }
    public fun singleOrDefault(): T { return single(null); }
    public fun singleOrDefault(expresion: Predicate<in T?>?): T { return single(expresion); }
    public fun single(expresion: Predicate<in T?>?): T
    {
        val temp = where(expresion).toList();
        if (temp.size > 1)
            throw Exception("The list has more than one item");
        return temp[0];
    }

    // Count, Sum, Min, Max, Average, Aggregate ---------------------------------
    public fun count(): Int { return count(null); }
    public fun count(expresion: Predicate<in T?>?): Int
    {
        if (expresion == null)
            return iterable!!.size;
        listGeneric = where(expresion).toList();
        return listGeneric!!.size;
    }

    public fun sum(expresion: ToIntFunction<in T>?): Int
    {
        if (expresion == null)
            throw Exception("The expresion is null");
        listGeneric = java.util.ArrayList(iterable);
        return listGeneric!!.stream().mapToInt(expresion).sum();
    }

    public fun min(expresion: ToIntFunction<in T>?): Int
    {
        if (expresion == null)
            throw Exception("The expresion is null");
        listGeneric = java.util.ArrayList(iterable);
        return listGeneric!!.stream().mapToInt(expresion).min().asInt;
    }

    public fun max(expresion: ToIntFunction<in T>?): Int
    {
        if (expresion == null)
            throw Exception("The expresion is null");
        listGeneric = java.util.ArrayList(iterable);
        return listGeneric!!.stream().mapToInt(expresion).max().asInt;
    }

    public fun average(expresion: ToIntFunction<in T>?): Double
    {
        if (expresion == null)
            throw Exception("The expresion is null");
        listGeneric = java.util.ArrayList(iterable);
        return listGeneric!!.stream().mapToInt(expresion).average().asDouble;
    }

    // AsEnumerable, ToDictionary, Cast<TResult> ---------------
    public fun <K>cast(): List<K> { return toList() as List<K>; }

    public fun asEnumerable(): Collection<T> { return iterable!!; }

    public fun toDictionary(expresion: Function<in T, out String>?): Map<String, T>
    {
        listGeneric = java.util.ArrayList(iterable);
        return listGeneric!!.stream()
            .collect(Collectors.toMap(expresion, Function.identity()));
    }
}