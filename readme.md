Benchmark HTML escaping

```
Benchmark                                Mode  Cnt         Score         Error  Units
EscaperBenchmark.best                   thrpt    3  20681092.359 ±  884538.278  ops/s
EscaperBenchmark.charAt                 thrpt    3   9613526.979 ±  950605.850  ops/s
EscaperBenchmark.charAtNoAllocate       thrpt    3  15294104.415 ±  424094.836  ops/s
EscaperBenchmark.jmustache              thrpt    3  17583233.512 ± 1218796.260  ops/s
EscaperBenchmark.substring              thrpt    3  10786061.661 ±  312750.957  ops/s
EscaperBenchmark.substringNoAllocate    thrpt    3  15680291.821 ±  650175.921  ops/s
StreamEscaperBenchmark.streamCharAt     thrpt    3  21761058.450 ± 1012018.089  ops/s
StreamEscaperBenchmark.streamOriginal   thrpt    3  15339866.611 ±  595936.086  ops/s
StreamEscaperBenchmark.streamSubstring  thrpt    3  27169769.380 ± 1446965.161  ops/s
```
