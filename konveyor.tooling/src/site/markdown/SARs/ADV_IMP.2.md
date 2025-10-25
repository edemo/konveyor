# ADV_IMP.2 Complete mapping of the implementation representation of the TSF

## Developer action elements

### ADV_IMP.2.1D
The developer shall make available the implementation representation for the entire TSF.

implementation status: implemented, partially documented

TODO: put the .github folder to the maven archetype

TODO: deployment_secrets.md

TODO: site-deploy.md

The .github folder of this project contains the following github actions:

.github/workflows/maven.yml: builds the code to mvn install when a pull request is issued

.github/workflows/maven_deploy.yml: deploys the maven artifacts to sonatype central,
deploys the documentation and selected artifacts to the site repository


how to adapt:

- Use github as the source code repository. Use the source code layout standard to separate production and test code. See FIXME for details.
- Configure secrets for maven_deploy.yml in the central repository and sonatype central. See deployment_secrets.md for details.
- Copy and adapt maven.yml and maven_deploy.yml to the special needs of the project. This is project dependent.
- Adapt pom.xml files and hooks to deploy selected artifacts, update site.xml to link them from the documentation. See site-deploy.md for details.
- Document every dependency not automatically documented. Prefer declaring dependencies in pom.xml and (if applicable) one eclipse target definition per repository. See FIXME for automatic dependency documentation.
- To ease evaluation, delete merged or otherwise not needed branches from github.

What to check:

- Check that the github repository does not contain binary blobs and compiled artifacts in any branch
- Check that dependencies are correctly documented, especially
	- maven dependencies (using mvn dependency:tree)
	- dependencies installed by github actions
  - dependencies installed from hooks and maven plugins (if any, there is none in konveyor.parent)
  - a special case of dependencies installed from maven plugins is eclipse target definitions.
- Given that the TOE builds in github actions, all the TSF will come either from the github repository or a dependency.


### ADV_IMP.2.2D
The developer shall provide a mapping between the TOE design description and the entire
implementation representation.

implementation status: not implemented

TODO: @Behaviour annotation

TODO: mutation testing

TODO: autodocument behaviour-class and behaviour-test mapping

TODO: document naming conventions

The build generates the mapping between behaviours in the model, the behaviour-implementing class and behaviour - testclass mappings based on naming conventions and annotations for the ST.
If the mappings are not complete from any side, the build fails.
If the mutation test finds any zombies in the production code, the build fails.

How to adapt:

- Label unit tests with @Behaviour annotation referencing the implemented behaviour
- Label unit tests with @DisplayName annotation referencing what they are testing
- Use the naming conventions
- Write enough unit tests for each behaviour such that there are no zombies left in the mutation tests

What to check:

- Check that the following automatisms are not tampered with:
	- ST documentation generation
	- failing the build in ST documentation generation errors
	- failing the build on mutation test coverage inadequacies
- Check that classes with @Glue annotation are minimal and do not contain any business/TSF logic
- Check the rationale for mutation test exclusion markers
- Check that tests actually test what they claim in the @DisplayName annotation
- Check the behaviour-test mapping to make sure that the behaviour documentation corresponds to what is actually tested

## Content and presentation elements

### ADV_IMP.2.1C
The implementation representation shall define the TSF to a level of detail such that the TSF may
be generated without further design decisions.

implementation status: implemented

Handled in ADV_IMP.2.1D

How to adapt:

No further action is necessary

### ADV_IMP.2.2C
The implementation representation shall be in the form used by the development personnel.

TODO: document coding standard (java, bash, pom xml, github action yml)

TODO: develop all PMD rules from coding standard

TODO: fail the build in PMD rule violations

Implementation status: partially implemented

The build checks the coding standard in the following ways:

- uses PMD to check production and test code
- maven uses xml schema check to make sure pom.xmls are conforming to their schema
- github checks the ymls used in github actions

Hooks need manual check

How to adapt:

- Code according to the coding standard
- Document any languages or uses beyond the coding standard

What to check:

- Check that the mechanism to fail the build in case of PMD violations are not tampered with
- Check the rationale for PMD exclusion markers
- Check the hooks for adherence to bash coding standards
- Check the extra language or use documentation and the corresponding code
- Check that no other language is used


### ADV_IMP.2.3C
The mapping between the TOE design description and the entire implementation representation
shall demonstrate their correspondence.

See ADV_IMP.2.2D

## Evaluator action elements

### ADV_IMP.2.1E
The evaluator shall confirm that the information provided meets all requirements for content and
presentation of evidence.

See "What to check" sections

