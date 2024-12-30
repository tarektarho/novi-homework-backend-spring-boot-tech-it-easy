````mermaid

sequenceDiagram
participant Client as Actor
participant TelevisionController
participant TelevisionService
participant TelevisionRepository
participant RemoteControllerRepository


    Client->>+TelevisionController: 1: assignRemoteControllerToTelevision(TelevisionID , RemoteControllerID)
    TelevisionController->>+TelevisionService: 2: assignRemoteControllerToTelevision(TelevisionID , RemoteControllerID)
    TelevisionService->>+TelevisionRepository: 3: findById(TelevisionID)
    TelevisionRepository->>-TelevisionService: 4: Optional<Television>
    TelevisionService->>+RemoteControllerRepository: 5: findById(RemoteControllerID)
    RemoteControllerRepository->>-TelevisionService: 6: Optional<RemoteController>
    TelevisionService->>TelevisionService: 7: setRemoteController(remoteController)
    TelevisionService->>+TelevisionRepository: 8: save(television)
    TelevisionRepository->>-TelevisionService: 9: Television
    TelevisionService->>-TelevisionController: 10: void
    TelevisionController->>-Client: 11: Http Status 200 OK
````

