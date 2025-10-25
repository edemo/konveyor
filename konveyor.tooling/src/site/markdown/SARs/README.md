This directory contains the documentation of the Security Assurance Requirements (SARs) supported by the package.

Each file describes:

- reference to the supported SAR
- implementation status
- how the SAR is supported
- what should a developer do in the project to implement the SAR

I believe that everything we do is at least partially driven by our need for security. Business logic in a business is driven by the stakeholders' need for material security, and the human rights which non-profit environemnts emphasize are probably even more tied to the security of human beings. Hence there is no point in discriminating between security and other kind of policies, or between TSF and business logic. Hence the TOE can be divided to the TSF, and its supporting code. The supporting code is the dependencies and the glue allowing the TSF and the dependencies to work together.

The main aims of this project:

- give support for implementing TOEs (TOEs aiming at high level of security assurance), in a way that
  - does not require excessive skills from the developers
  - uses automated checks to ensure compliance
  - signals deviations early
  - minimizes duplication of effort by generating everything which can be generated
  - makes it possible to develop small pieces of code independently from each other
- by
  - providing design standards through a metamodel and its constraints
  - providing design elements through its own model
  - generating ST and design documentation from the model
  - maintain github issues to drive development, which
    - details the design
    - together with the coding standard make possible to independently implement each issue
  - generating parts of the code from the model, notably
    - data structures (java records)
    - end-to-end tests
    - service skeletons
    - repositories
  - providing coding standards
  - providing checks of the coding standards through
    - PMD rules
    - mutation testing
  - providing exclusion markers and ways to define milestones such that real-world implementation is possible
  - checking that all constraints and rules are adhered to or correctly excluded. This means that
    - all levels of design (including implementation) are checked for internal consistency, and
    - all adjacent levels of design are checked for consistency with each other.
  - building and deploying the project such that
    - it is flexibly configureable to allow real-world implementation. Proove it by using this project to implement itself and its companion, the Inez architecture modeler
    - the documentation is standardised and generated
    - fail the build in case of any unexcluded deviation
  - providing the designers and developers the necessary documentation, tools and artifacts to easily use it

A big part of the design philosophy is metamodel based modeling. It supports Common Criteria in the following way:

The metamodel contains the following levels of grouping:

- group id
- projects
- (maven) modules
- (java) packages in a tree hierarchy
- (java) classes: services, records, repositories, delegates and glue code

The metamodel contains constraints making sure that:

- each project belongs to a group id
- each module belongs to a project
- a  module can contain at most 5 packages
- a package can contain at most 8 production classes
- each package belongs to exactly one maven production module
- packages are contained within exactly one project or another package, without cycles
- classes are contained within exactly one package

There are the following implicit correspondences:

- test classes are implicitly defined by the production class and the behaviours implemented by it
- each (production) maven module implicitly allows the existence of the corresponding test module containing the same packages

The metamodel contains the following levels of design:

- policy level, containing
  - policies
  - assumptions
  - threats
- objective level, containing
  - objectives of the TOE
- requirement level, containing
  - functional and assurance requirements
  - requirement interdependencies
  - environment objectives
- process level, containing
  - process steps
  - errors
  - business objects
  - parameters
  - roles
  - UI elements
- implementation level, containig
  - services and repositories

Some of the elements related to assurace are provided by this and related projects.
These are notably some policies, assumptions and threats, assurance requirements documented here and their interdependencies,
and environment objectives provided by this project for the TOE development.

The metamodel contains constraints ensuring the following:

- each requirement interdependency is satisfied
- each policy, assumption and threat are covered by objectives
- each objective covers some policy, assumption, threat
- each objective are covered by requirements
- each requirement covers some objectives
- each process step covers some behaviours
- possible errors with their message are associated with each process step
- each requirement are covered by some process steps
- process steps are defining comprehensive processes
- each functional requirement implemented by the TOE (behaviour) are covered by some process steps
- each process step defines its input and output data and the used UI elements
- each process step is implemented by a role or a service
- each UI element are implemented by a service

The modeling tool generates an error on violation of any metamodel constraint.

This project generates the following:
- ST chapters corresponding to the above elements TODO
- Mapping between
  - policy and objective level TODO
  - objective and requirement level TODO
  - requirement and process level TODO
  - process and implementation level TODO
- design documentation for each service TODO including:
  - the behaviours it implements
  - the tested aspects of those behaviours in the service
- github issue for each behaviour of each service TODO
- data structures (java records) TODO
- end-to-end tests TODO

The coding standard and the corresponding PMD checks make sure that

- behaviour and data are separated, and
- each service has only one method
- each classes' cyclomatic complexity is below FIXME

The build fails if:

- there are modeling errors
- the model and the code do not correspond to each other
- the coding standard (which includes the complexity rules) is violated

TODO: code complexity rules for PMD

TODO: module and package complexity rules

TODO: implement inez

TODO: implement the konveyor metamodel in inez

