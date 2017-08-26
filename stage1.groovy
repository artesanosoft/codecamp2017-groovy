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
  station = new Station(name: it.name.text().trim(), textCoordinates: textCoordinates )
  stations << station
}

subwaylines.each { line ->
  stations.each { station ->
    if (line.textCoordinates.contains(station.textCoordinates)) {
      line.stations << station
    }
  }
}

println subwaylines

@ToString
class SubwayLine {
  String name
  String textCoordinates
  List<Station> stations = []
}

@ToString
class Station {
  String name
  String textCoordinates
  Float latitude
  Float longitude
}
