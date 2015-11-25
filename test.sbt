fork in Test := true

parallelExecution in Test := false

javaOptions in Test += "-Dzookeeper.jmx.log4j.disable=true"
