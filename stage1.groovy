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
  subwayline = new SubwayLine(name: it.name, coords: it.LineString.coordinates.text())
  subwaylines.add subwayline
}

println subwaylines

@ToString
class SubwayLine {
  String name
  String coords
  List<Station> stations
}

class Station {
  String name
  String coords
}
