codeCampService = CodeCampService.instance

lines = codeCampService.stage1("Metro_CDMX.kml")
println "-------- Stage 1 ------------"
lines.each { line ->
  println "* $line.name"
  line.stations.each { s ->
    println "\t $s.name $s.textCoordinates"
  }
}
println "-------- Stage 2 ------------"

println "* ${lines[4].name}"
lines[4].stations.each { s ->
  println "\t $s.name $s.textCoordinates"
}

graph = []
for(i = 0; i < lines[4].stations.size() - 1; i++){
  graph << [lines[4].stations[i], lines[4].stations[i+1]]
}
println graph
