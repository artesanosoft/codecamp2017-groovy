import groovy.transform.*

file = new File("Metro_CDMX.kml")
text = file.text

root = new XmlSlurper().parseText(text)

println root.Document.name
//root.Document.Style.each {
//  println it
//}
subwaylines = []
root.Document.Folder[0].Placemark.each {
  subwayline = new SubwayLine(name: it.name.text().trim(), textCoordinates: it.LineString.coordinates.text().trim())
  subwaylines.add subwayline
}
stations = []
root.Document.Folder[1].Placemark.each {
  textCoordinates = it.Point.coordinates.text().trim()
  coords = textCoordinates.split(",")*.toFloat()
  station = new Station(
    name: it.name.text().trim(),
    textCoordinates: textCoordinates,
    latitude: coords[0],
    longitude: coords[1] )
  stations << station
}

subwaylines.each { line ->
  stations.each { station ->
    if (line.textCoordinates.contains(station.textCoordinates)) {
      line.stations << station
    }
  }
}

subwaylines.each { line ->
  println "* $line.name"
  line.stations.each { s ->
    println "\t $s.name $s.textCoordinates"
  }
  println "*"*100
  line.stations.sort().each { s ->
    println "\t $s.name $s.textCoordinates"
  }
}

@ToString
class SubwayLine {
  String name
  String textCoordinates
  List<Station> stations = []
}

@ToString
@EqualsAndHashCode(includes = ["order"])
class Station {
  Integer order
  String name
  String textCoordinates
}
