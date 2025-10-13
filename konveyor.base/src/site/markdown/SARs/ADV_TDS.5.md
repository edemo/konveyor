# ADV_TDS.5 Complete semiformal modular design

## Developer action elements

### ADV_TDS.5.1D
The developer shall provide the design of the TOE.

implementation status: not implemented

The TOE is designed as an Inez model using the Kode Konveyor metamodel.

The build generates all the CC documentation from the model and the implementation.

How to adapt:

Model the TOE using the Kode Konveyor metamodel.

How to check:

Check that the documentation required by CC is generated.

### ADV_TDS.5.2D
The developer shall provide a mapping from the TSFI of the functional specification to the lowest
level of decomposition available in the TOE design.

implementation status: not implemented

See README.md for details of the mapping. It is across all the design levels for the entire TOE.

How to adapt:

See ADV_INT.3.2D

How to check:

See ADV_INT.3.2D

# Content and presentation elements

### ADV_TDS.5.1C
The design shall describe the structure of the TOE in terms of subsystems.

There are multiple levels of subsystem decomposition (see README.md for details):

- projects
- (maven) modules
- (java) packages in a tree hierarchy

The structure of decomposition:

- each project belongs to a group id
- each module belongs to a project
- a  module can contain at most 5 packages
- a package can contain at most 8 production classes
- each package belongs to exactly one maven production module
- packages are contained within exactly one project or another package, without cycles

How to adapt:

See ADV_INT.3.2D

How to check:

See ADV_INT.3.2D

### ADV_TDS.5.2C
The design shall describe the TSF in terms of modules, designating each module as SFR-enforcing,
SFR-supporting, or SFR-non-interfering.

implementation status: not implemented

The design modules are the services and repositories of the implementation level and glue code defined by the implementation and showing up in the generated documentation. services are SF-enforcing, repositories and glue code are SFR-supporting. There is no SFR-non-interfering code.

How to adapt:

See ADV_INT.3.2D

How to check:

See ADV_INT.3.2D


### ADV_TDS.5.3C
The design shall identify all subsystems of the TSF.

implementation status: not implemented

The correspondence checks in the build make sure that all code belongs to a defined package.

How to adapt:

See ADV_INT.3.2D

How to check:

See ADV_INT.3.2D

### ADV_TDS.5.4C
The design shall provide a semiformal description of each subsystem of the TSF, supported by
informal, explanatory text where appropriate.

implementation status: not implemented

TODO use the metamodel definition of project, modules and package to give the definition of them, and the ways to describe them.

How to adapt:

See ADV_INT.3.2D

How to check:

See ADV_INT.3.2D

### ADV_TDS.5.5C
The design shall provide a description of the interactions among all subsystems of the TSF.

implementation status: not implemented

The generated documentation contains
- the interdependencies of the projects, modules and packages
- the API of the modules
- the indirect interactions between modules through the implemented process TODO

How to adapt:

See ADV_INT.3.2D

How to check:

See ADV_INT.3.2D

### ADV_TDS.5.6C
The design shall provide a mapping from the subsystems of the TSF to the modules of the TSF.

implementation status: not implemented

See ADV_INT.3.2D

### ADV_TDS.5.7C
The design shall provide a semiformal description of each module in terms of its purpose,
interaction, interfaces, return values from those interfaces, and called interfaces to other
modules, supported by informal, explanatory text where appropriate.

implementation status: not implemented

TODO: use here basically most of the metamodel to describe how it is done.

How to adapt:

See ADV_INT.3.2D

How to check:

See ADV_INT.3.2D

### ADV_TDS.5.8C
The mapping shall demonstrate that all TSFIs trace to the behaviour described in the TOE design
that they invoke.


implementation status: not implemented

The build lists the TSFis (all the services which serve as the interface of the project) based on the implemented processes and UIs.
All services are documented with the behaviours (the functional requirements they implement) among other properties.
The metamodel ensures that all of the above are traceable up to the policy level and down to the implementation.

How to adapt:

See ADV_INT.3.2D

How to check:

See ADV_INT.3.2D

## Evaluator action elements

### ADV_TDS.5.1E
The evaluator shall confirm that the information provided meets all requirements for content and
presentation of evidence.

See "How to check" sections above.

### ADV_TDS.5.2E
The evaluator shall determine that the design is an accurate and complete instantiation of all
security functional requirements.

See "How to check" sections above.

