import groovy.transform.*

@ToString
@EqualsAndHashCode(includes = ["order"])
class Station {
  Integer order
  String name
  String textCoordinates
}

