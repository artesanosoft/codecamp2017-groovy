codeCampService = CodeCampService.instance

lines = codeCampService.stage1("Metro_CDMX.kml")
lines.each { line ->
  println "* $line.name"
  line.stations.each { s ->
    println "\t $s.name $s.textCoordinates"
  }
}

