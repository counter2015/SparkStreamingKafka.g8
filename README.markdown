A [Giter8][g8] template for sparkstreaming kafka!

## Useage
```shell
$ sbt new counter2015/SparkStreamingKafka.g8
# or 
$ g8 counter2015/SparkStreamingKafka.g8
```

the template struct may like this 
```shell
$ tree
.
├── build.sbt
├── project
│   ├── Dependencies.scala
│   ├── assembly.sbt
│   └── build.properties
└── src
    └── main
        ├── resources
        │   └── application.conf
        └── scala
            ├── ConfigInstance.scala
            └── SparkStreamingApp.scala
```


You should change configuration to adapt your project.
- application.conf where include kafka brokers and topic
- checkpointPath in `SparkStreamingApp.scala`

the packages version listed at `Dependencies.scala`, default
- kafka 2.0.1
- spark 2.4.0
- spark-streaming-kafka 1.6.3


Template license
----------------
Written in 2019 by counter2015

To the extent possible under law, the author(s) have dedicated all copyright and related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.

[g8]: http://www.foundweekends.org/giter8/
