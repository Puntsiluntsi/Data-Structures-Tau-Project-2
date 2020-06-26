# Data Structures Project 2 - HashTable 
- Maayan Kestenberg - _kestenberg1_ [213056674]
- Re'em Kishnevsky - _reemk_ [213057094]

------------------------------
## Experiment 3
### Section A
- Size of Q1 is 3286
- Size of Q2 is 6571

### Section B
- Exceptions in `QPHashTable`: 90
- Exceptions in `AQPHashTable`: 0

The set of indices in the probe sequence of an element in `QPHashTable` is Q1 (from section A)
with some constant added (the element's hash); therefore, in QP we check |Q1|=3286 indices (~half the table)
when searching for a vacant place to insert the element in. Consequently, as the table gets filled,
there is a non-negligible chance that the probe sequence will contain only occupied indices (up to
a probability close to 1/2 when there is one free slot left), resulting in a `TableIsFullError`.

On the other hand, the set of indices in `AQPHashTable`'s probe sequences is Q2 (from section A) again with the
element's hash added, which means that every such probe sequence contains 6571 indices - the whole table.
Therefore, we only get `TableIsFull` errors when the whole table is full, and in our case, this never happens
because we only insert m elements (the size of the table) and not a single element extra. That is why we got 0 errors.

### Section C
Every prime number p>2 has (p+1)/2 quadratic residues mod p (including 0). The set Q1 is exactly the set of quadratic
residues mod m=6751, which is prime. It is obviously a set of quadratic residues per definition because it is defined
as the remainder of an integer i squared mod p. It contains ALL the quadratic residues because for every integer x>m,
there exists some i&lt;m
for which x is equivalent to i mod m, which also means that x squared is equivalent to i squared
mod m - and i squared mod m is a member of Q1. This is why Q1 has exactly (m+1)/2=(6751+1)/2=3286 elements, causing the
probe sequence in QP to only contain half (rounded up) of the table's indices.
For m=2, every integer is a quadratic residue mod m, so the probe sequence will contain all 2 indices. This is the only
prime for which the phenomenon (the probe sequence only containing half the indices) doesn't occur, since for a any prime
m>2 we have seen that it does occur.


------------------------------
## Experiment 4
### Section A
 Class             | Running Time 
-------------------|--------------
 `LPHashTable`     | 2.141 sec
 `QPHashTable`     | 2.515 sec
 `AQPHashTable`    | 2.179 sec
 `DoubleHashTable` | 1.107 sec

We can see that the main difference in running time is that of `DoubleHashTable` compared to the other tables.
This short running time is obtained by `DoubleHashTable`'s use of a secondary hash function to 
determine the step from the base hash function - unlike the rest of the tables, in which the step 
is not determined by the key. In this way, `DoubleHashTable` minimizes collisions because even if two 
elements are hashed to the same cell, they have different search sequences.

 ### Section B
 Class             | Running Time 
-------------------|--------------
 `LPHashTable`     | 5.082 sec
 `AQPHashTable`    | 2.733 sec
 `DoubleHashTable` | 3.183 sec

 We don't run `QPHashTable` because this time we add a number of elements that is bigger than half the table size, and as we saw in experiment 3, this can cause `QPHashTable` to throw `TableIsFull` exceptions - which, as instructed, are not supposed to be thrown in this experiment.

 The long running time of `LPHashTable` reveals it's limitation when the table becomes close to full. Since the search sequence of an element in linear probing is consecutive, cluster are formed in the table. As we insert more elements, these clusters are getting longer. When the table is getting close to full (like in this experiment), different clusters begin to merge, forming even larger clusters and further lengthening the running time of additional inserts. This is portrayed in the results by the long running time of `LPHashTable`.
 This problem of merging clusters was not apparent in the previous results because we inserted way less elements, so the chances of clusters merging together were lower.

 This time, we observe that `AQPHashTable` ran faster than `DoubleHashTable`, that can be explained:
 `DoubleHashTable` uses a hash function to calculate the step of the search sequence, while `AQPHashTable` uses a simple calculation. The modulo operations of `DoubleHashTable`'s step hash function are more expensive than `AQPHashTable`'s calculation, so when using `DoubleHashTable` we trade the efficiency of our search sequence calculation for a more sophisticated search sequence that results in fewer collisions. This trade does not benefit us in the case that we fill up a large portion of the table (the above results are when filling 95% of the table). Because then, collisions are much more frequent, so we will be calculating a larger portion of the search sequence. Therefore it becomes more profitable to have an efficient way of calculating the search sequence (at the cost of more collisions) than to have a search sequence that is heavy to compute but avoids more collisions. That's the reason `DoubleHashTable` ran slower than `AQPHashTable`.

------------------------------
## Experiment 5
 Iterations         | Running Time 
--------------------|--------------
 First 3 iterations | 6.926 sec
 Last 3 iterations  | 26.319 sec

The difference in running time stems from the fact that as we perform more iterations, the hash 
table is filled with more `deleted` marks due the elements we delete at each iteration.
In the last 3 iterations, each iteration starts with significantly more cells in the hash table 
that are marked `deleted` than in the first 3 iterations. Now, the more `deleted` cells in the 
table >>> the more cells that are scanned when inserting a new element >>> the more time required 
for a single insert operation, hence the longer running time.
Another intuitive way of thinking about it is that each `deleted` mark is treated just like an 
ordinary taken cell when scanning the table to find a place to insert an element.
And we already know from experiment 4 that the fuller the hash table, the longer it takes for each insert.



 
