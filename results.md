
## MacBook Pro

```
Model Name: MacBook Pro
Model Identifier: MacBookPro18,3
Chip: Apple M1 Pro
Total Number of Cores: 8 (6 performance and 2 efficiency)
Memory: 32 GB
```

```
Benchmark                                               Mode  Cnt     Score     Error   Units
EscaperBenchmark.charExternalSwitch2                   thrpt    5  5978.943 ±  67.055  ops/ms
EscaperBenchmark.charInlineSwitch                      thrpt    5  4401.016 ±  82.350  ops/ms
EscaperBenchmark.charSwitchPreAllocate                 thrpt    5  3014.154 ±  24.828  ops/ms
EscaperBenchmark.jmustache                             thrpt    5  4835.486 ±  98.064  ops/ms
EscaperBenchmark.substringExternalSwitch2              thrpt    5  7657.910 ±  37.567  ops/ms
EscaperBenchmark.substringInlineSwitch2                thrpt    5  4176.153 ±  25.481  ops/ms
EscaperBenchmark.substringInlineSwitchPreAllocate      thrpt    5  3384.366 ±  14.871  ops/ms
StreamEscaperBenchmark.streamCharExternalSwitch        thrpt    5  5853.454 ±  50.936  ops/ms
StreamEscaperBenchmark.streamCharExternalSwitch2       thrpt    5  6588.039 ±  44.263  ops/ms
StreamEscaperBenchmark.streamJMustache                 thrpt    5  4861.607 ±  21.025  ops/ms
StreamEscaperBenchmark.streamLookup2                   thrpt    5  8670.128 ± 102.673  ops/ms
StreamEscaperBenchmark.streamMustacheJava              thrpt    5  6546.330 ±  83.800  ops/ms
StreamEscaperBenchmark.streamSubstringExternalSwitch   thrpt    5  7325.905 ±  17.699  ops/ms
StreamEscaperBenchmark.streamSubstringExternalSwitch2  thrpt    5  8000.138 ±  81.513  ops/ms
StreamEscaperBenchmark.streamSubstringInlineSwitch     thrpt    5  3423.695 ±  28.765  ops/ms
StreamEscaperBenchmark.streamSubstringInlineSwitch2    thrpt    5  4401.521 ±  36.975  ops/ms
```

----

## Low power Intel Atom

```
Architecture:        x86_64
CPU op-mode(s):      32-bit, 64-bit
Byte Order:          Little Endian
CPU(s):              8
On-line CPU(s) list: 0-7
Thread(s) per core:  1
Core(s) per socket:  8
Socket(s):           1
NUMA node(s):        1
Vendor ID:           GenuineIntel
CPU family:          6
Model:               95
Model name:          Intel(R) Atom(TM) CPU C3758 @ 2.20GHz
Stepping:            1
CPU MHz:             932.925
CPU max MHz:         2200.0000
CPU min MHz:         800.0000
BogoMIPS:            4400.00
Virtualization:      VT-x
L1d cache:           24K
L1i cache:           32K
L2 cache:            2048K
NUMA node0 CPU(s):   0-7
```

```
Benchmark                                               Mode  Cnt     Score    Error   Units
EscaperBenchmark.charExternalSwitch2                   thrpt    5   885.198 ±  2.848  ops/ms
EscaperBenchmark.charInlineSwitch                      thrpt    5   936.115 ±  3.686  ops/ms
EscaperBenchmark.charSwitchPreAllocate                 thrpt    5   639.959 ±  2.436  ops/ms
EscaperBenchmark.jmustache                             thrpt    5   766.907 ±  7.839  ops/ms
EscaperBenchmark.substringExternalSwitch2              thrpt    5  1185.327 ±  7.940  ops/ms
EscaperBenchmark.substringInlineSwitch2                thrpt    5  1058.558 ± 11.299  ops/ms
EscaperBenchmark.substringInlineSwitchPreAllocate      thrpt    5  1094.447 ± 13.775  ops/ms
StreamEscaperBenchmark.streamCharExternalSwitch        thrpt    5   806.255 ±  1.475  ops/ms
StreamEscaperBenchmark.streamCharExternalSwitch2       thrpt    5  1095.954 ±  7.452  ops/ms
StreamEscaperBenchmark.streamJMustache                 thrpt    5   783.838 ±  7.994  ops/ms
StreamEscaperBenchmark.streamLookup2                   thrpt    5  1461.633 ±  8.188  ops/ms
StreamEscaperBenchmark.streamMustacheJava              thrpt    5  1170.827 ±  3.536  ops/ms
StreamEscaperBenchmark.streamSubstringExternalSwitch   thrpt    5  1097.066 ±  3.765  ops/ms
StreamEscaperBenchmark.streamSubstringExternalSwitch2  thrpt    5  1475.201 ±  5.175  ops/ms
StreamEscaperBenchmark.streamSubstringInlineSwitch     thrpt    5  1227.002 ±  6.280  ops/ms
StreamEscaperBenchmark.streamSubstringInlineSwitch2    thrpt    5  1509.688 ±  4.238  ops/ms
```

----

## Oracle AMD Cloud Machine

```
Architecture:            x86_64
  CPU op-mode(s):        32-bit, 64-bit
  Address sizes:         40 bits physical, 48 bits virtual
  Byte Order:            Little Endian
CPU(s):                  4
  On-line CPU(s) list:   0-3
Vendor ID:               AuthenticAMD
  Model name:            AMD EPYC 7J13 64-Core Processor
    CPU family:          25
    Model:               1
    Thread(s) per core:  2
    Core(s) per socket:  2
    Socket(s):           1
    Stepping:            1
    BogoMIPS:            5090.43
Virtualization features:
  Hypervisor vendor:     KVM
  Virtualization type:   full
Caches (sum of all):
  L1d:                   128 KiB (2 instances)
  L1i:                   128 KiB (2 instances)
  L2:                    1 MiB (2 instances)
  L3:                    16 MiB (1 instance)
```

```
Benchmark                                               Mode  Cnt     Score     Error   Units
EscaperBenchmark.charExternalSwitch2                   thrpt    5  3016.109 ± 114.658  ops/ms
EscaperBenchmark.charInlineSwitch                      thrpt    5  3272.593 ± 167.203  ops/ms
EscaperBenchmark.charSwitchPreAllocate                 thrpt    5  2155.193 ±  58.755  ops/ms
EscaperBenchmark.jmustache                             thrpt    5  2888.921 ± 162.638  ops/ms
EscaperBenchmark.substringExternalSwitch2              thrpt    5  4063.140 ± 115.664  ops/ms
EscaperBenchmark.substringInlineSwitch2                thrpt    5  3506.069 ± 155.610  ops/ms
EscaperBenchmark.substringInlineSwitchPreAllocate      thrpt    5  3456.650 ±  93.013  ops/ms
StreamEscaperBenchmark.streamCharExternalSwitch        thrpt    5  3079.005 ± 122.175  ops/ms
StreamEscaperBenchmark.streamCharExternalSwitch2       thrpt    5  3864.179 ±  80.554  ops/ms
StreamEscaperBenchmark.streamJMustache                 thrpt    5  3012.376 ± 173.964  ops/ms
StreamEscaperBenchmark.streamLookup2                   thrpt    5  4548.430 ± 133.516  ops/ms
StreamEscaperBenchmark.streamMustacheJava              thrpt    5  4704.203 ± 137.310  ops/ms
StreamEscaperBenchmark.streamSubstringExternalSwitch   thrpt    5  3959.939 ± 212.366  ops/ms
StreamEscaperBenchmark.streamSubstringExternalSwitch2  thrpt    5  6170.374 ± 200.035  ops/ms
StreamEscaperBenchmark.streamSubstringInlineSwitch     thrpt    5  4453.090 ±  65.211  ops/ms
StreamEscaperBenchmark.streamSubstringInlineSwitch2    thrpt    5  4502.828 ± 214.649  ops/ms
```
