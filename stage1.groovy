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
  textCoordinates = it.LineString.coordinates.text().trim()
  subwayline = new SubwayLine(name: it.name.text().trim(), textCoordinates: textCoordinates)
  textCoordinates.split("\n").eachWithIndex { c, i ->
    station = new Station(order: i, textCoordinates: c.trim())
    subwayline.stations << station
  }
  subwaylines.add subwayline
}

root.Document.Folder[1].Placemark.each {
  textCoordinates = it.Point.coordinates.text().trim()
  stationName = it.name.text().trim()
  subwaylines.each { line ->
    if (line.textCoordinates.contains(textCoordinates)) {
      station = line.stations.find { s ->
        s.textCoordinates == textCoordinates
      }
      station.name = stationName
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
