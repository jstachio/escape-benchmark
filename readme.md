# Benchmark HTML escaping

```
Benchmark                                 Mode  Cnt         Score          Error  Units
EscaperBenchmark.best                    thrpt    3  19410859.412 ±  1023259.087  ops/s
EscaperBenchmark.charAt                  thrpt    3   9400973.364 ±   449089.997  ops/s
EscaperBenchmark.charAtNoAllocate        thrpt    3  15936691.096 ±   970473.697  ops/s
EscaperBenchmark.jmustache               thrpt    3  17504519.828 ±  5691478.713  ops/s
EscaperBenchmark.substring               thrpt    3  10421831.787 ±   728935.443  ops/s
EscaperBenchmark.substringNoAllocate     thrpt    3  15765473.159 ±  2318367.606  ops/s
StreamEscaperBenchmark.streamCharAt      thrpt    3  21085415.779 ±   571932.581  ops/s
StreamEscaperBenchmark.streamJMustache   thrpt    3  16973083.879 ±  7603396.663  ops/s
StreamEscaperBenchmark.streamLookup      thrpt    3  28384651.065 ± 20826988.780  ops/s
StreamEscaperBenchmark.streamSubstring   thrpt    3  23325005.089 ±  1191970.621  ops/s
StreamEscaperBenchmark.streamSubstring2  thrpt    3  26887956.332 ±  6456859.418  ops/s
```

This (micro) benchmark is to test various HTML escaping for template engines specifically
JMustache and JStachio.

This benchmark tests calling the escaper three times with various text to simulate template
engine rendering.

There are two types of escaping output:

## Stream Output

The stream escaping assumes the output is an
appendable. This is what JStachio currently does and is a version of `streamSubstring`.
JMustache does not currently (as of 1.16) support stream escaping but if it did using
its naive implementation to just replace the whole string is included as `streamJMustache`.

`streamLookup` appears to be the fastest (on my machine).

## String Output

String escaping returns a String instead of writing directly to an appendable.
JMustache does this by default and its version is called `jmustache`. The JMustache
version naively uses `String.replace` which is surprisingly not too bad performance wise
for String output. `best` is currently the fastest but a `lookup` table instead of a
switch might make it faster as discussed in stream.

# Results

Obviously stream escaping is generally superior for template engines as it avoids unnecessary
duplication (object allocation).

The `streamLookup` uses a sparse `String[]` of size 127 which is the lower 7 bit ascii.
That is the characters to be replaced have to be ascii 127 or less.

**Surprisingly the lookup table approach is faster than `switch` which is what all the other
implementations (except JMustache) use instead!** 

The above is actually benificial because some folks may want a different escape for 
apostrophe (`'` ) aka decimal `39` in ascii. 
JStachio uses hex entity `&#x27;` but JMustache uses decimal `&#39;`.

