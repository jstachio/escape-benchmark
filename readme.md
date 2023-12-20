# Benchmark HTML escaping

This (micro) benchmark is to test various HTML escaping for template engines specifically
JMustache and JStachio.

This benchmark tests calling the escaper three times with various text to simulate template
engine rendering.

```
Benchmark                                               Mode  Cnt         Score         Error  Units

EscaperBenchmark.substringExternalSwitch2              thrpt    3  24537015.630 ± 1455989.530  ops/s
EscaperBenchmark.charExternalSwitch2                   thrpt    3  19602063.605 ± 3519193.744  ops/s
EscaperBenchmark.substringInlineSwitchPreAllocate      thrpt    3  17988381.452 ±  520606.681  ops/s
EscaperBenchmark.jmustache                             thrpt    3  17541191.540 ±  448452.049  ops/s
EscaperBenchmark.substringInlineSwitch2                thrpt    3  15941900.867 ±  896278.174  ops/s
EscaperBenchmark.charInlineSwitch                      thrpt    3  15595279.690 ± 1569244.880  ops/s
EscaperBenchmark.charSwitchPreAllocate                 thrpt    3   9442511.383 ± 1026043.969  ops/s

StreamEscaperBenchmark.streamLookup2                   thrpt    3  27593484.190 ± 2424533.814  ops/s
StreamEscaperBenchmark.streamSubstringExternalSwitch2  thrpt    3  26991816.005 ± 3204615.721  ops/s
StreamEscaperBenchmark.streamSubstringExternalSwitch   thrpt    3  26455216.560 ±  789025.629  ops/s
StreamEscaperBenchmark.streamMustacheJava              thrpt    3  22950058.962 ± 1205694.560  ops/s
StreamEscaperBenchmark.streamCharExternalSwitch2       thrpt    3  21795621.673 ± 3208067.365  ops/s
StreamEscaperBenchmark.streamCharExternalSwitch        thrpt    3  18602715.349 ± 1356057.326  ops/s
StreamEscaperBenchmark.streamJMustache                 thrpt    3  17673184.382 ±  638278.779  ops/s
StreamEscaperBenchmark.streamSubstringInlineSwitch     thrpt    3  15860186.194 ± 1291959.679  ops/s
StreamEscaperBenchmark.streamSubstringInlineSwitch2    thrpt    3  14527009.571 ± 2770665.402  ops/s

```

*(~~I have~~ ... errr chatgpt sorted the results in order of performance and separated the streaming)*



Run by:

```
mvn clean package
java -jar target/escape-benchmark.jar
```

## Benchmark Naming

### "char" vs "substring"

*"char"* Means appending one `char` (`append(c)`) at a time instead of using bulk `append(csq, start, end)`.
This is generally less performant than bulk appending via subsequence. 

*"substring"* is bulk appending contiguous blocks of text that does not need to be escaped.

### "InlineSwitch" vs "ExternalSwitch" vs "Lookup"

*"InlineSwitch"* is using the newer `switch` expression in the same method call to map 
characters to be escaped. Both JStachio (1.3.4) and JTE use this technique. 

*"ExternalSwitch"* is using old school switch statements in an external static function
that returns the mapped string.

*"Lookup"* uses a sparse `String[]` of size 127 which is the lower 7 bit ascii.
That is the characters to be replaced have to be ascii 127 or less. 
I accidentally tried this thinking it would be slower than `switch` but it is faster!
While examing other template engines after writing this benchmark I found 
"Mustache.java" and sort JMustache to use this technique probably because they were written
before switch expressions.

### 2 on the end

The 2 on the end of the benchmark name means it employs a double loop technique where
the first loop creates a sort of fast path for strings that do not need escaping.
The second loop then picks up where the first loop left off if escaping is needed.
This again was accidentally found while trying to improve JMustache string escaping.

### Stream Output

*All benchmarks prefixed with "stream"*

The stream escaping assumes the output is an
appendable. This is what JStachio currently does and is a version of `streamSubstringInlineSwitch`.
JMustache does not currently (as of 1.16) support stream escaping but if it did using
its naive implementation to just replace the whole string is included as `streamJMustache`.

`streamLookup2` appears to be the fastest (on my machine).

### String Output

String escaping returns a String instead of writing directly to an appendable.
JMustache does this by default and its version is called `jmustache`. The JMustache
version naively uses `String.replace` which is surprisingly not too bad performance wise
for String output. `best` is currently the fastest but a `lookup` table instead of a
switch might make it faster as discussed in stream.

# Interpretation of Results

Obviously stream escaping is generally superior for template engines as it avoids unnecessary
duplication (object allocation).

The `streamLookup2` uses a sparse `String[]` of size 127 which is the lower 7 bit ascii.
That is the characters to be replaced have to be ascii 127 or less.

**Surprisingly the lookup table approach is faster than `switch` which is what all the other
implementations (except JMustache) use instead!** 

The above is actually benificial because some folks may want a different escape for 
apostrophe (`'` ) aka decimal `39` in ascii. 
JStachio uses hex entity `&#x27;` but JMustache uses decimal `&#39;`.


I'm surprised how poorly switch expressions did compared to even statement switch that required
a separate method call. JStachio and JTE use this technique. I will probably eventually change
JStachio escape algorithm because of these results

