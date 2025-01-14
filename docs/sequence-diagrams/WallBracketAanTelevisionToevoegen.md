````mermaid

sequenceDiagram
participant Client as Actor
participant TelevisionWallBracketController
participant TelevisionWallBracketService
participant TelevisionRepository
participant WallBracketRepository
participant TelevisionWallBracketRepository


    Client->>+TelevisionWallBracketController: 1: addTelevisionWallBracket(TelevisionId , WallBracketId)
    TelevisionWallBracketController->>+TelevisionWallBracketService: 2: addTelevisionWallBracket(TelevisionId , WallBracketId)
    TelevisionWallBracketService->>+TelevisionRepository: 3: findById(televisionId)
    TelevisionRepository->>-TelevisionWallBracketService: 6: Optional<Television>
    TelevisionWallBracketService->>+WallBracketRepository: 7: findById(wallBracketId)
    WallBracketRepository->>-TelevisionWallBracketService: 10: Optional<WallBracket>
    TelevisionWallBracketService->>TelevisionWallBracketService: 11: setTelevision(television)
    TelevisionWallBracketService->>TelevisionWallBracketService: 12: setWallBracket(wallBracket)
    TelevisionWallBracketService->>+TelevisionWallBracketRepository: 13: setId(id)
    TelevisionWallBracketRepository->>-TelevisionWallBracketService: 16: TelevisionWallBracket
    TelevisionWallBracketService->>-TelevisionWallBracketController: 17: Collection<TelevisionWallBracket>
    TelevisionWallBracketController->>-Client:  19: Http status 201 CREATED
````