````mermaid

sequenceDiagram
participant Client as Actor
participant TelevisionController
participant TelevisionService
participant TelevisionRepository
participant CIModuleRepository

     Client->>+TelevisionController: 1: assignCIModuleToTelevision(TelevisionID , CIModuleID)
     TelevisionController->>+TelevisionService: 2: assignCIModuleToTelevision(TelevisionID , CIModuleID)
     TelevisionService->>+TelevisionRepository: 3: findById(TelevisionID)
     TelevisionRepository->>-TelevisionService: 4: Optional<Television>
     TelevisionService->>+CIModuleRepository: 5: findById(CIModuleID)
     CIModuleRepository->>-TelevisionService: 6: Optional<CIModule>
     TelevisionService->>TelevisionService: 7: setCIModule(CIModule)
     TelevisionService->>+TelevisionRepository: 8: save(television)
     TelevisionRepository->>-TelevisionService: 9: Television
     TelevisionService->>-TelevisionController: 10: void
     TelevisionController->>-Client: 11: Http Status 200 OK

````