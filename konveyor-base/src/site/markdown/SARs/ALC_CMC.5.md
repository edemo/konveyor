# ALC_CMC.5 Advanced support

## Developer action elements

### ALC_CMC.5.1D
The developer shall provide the TOE and a unique reference for the TOE.

See ALC_CMS.5.1D

### ALC_CMC.5.2D
The developer shall provide the CM documentation.

implementation status: not implemented

the build puts the CM documentation to the maven site

### ALC_CMC.5.3D
The developer shall use a CM system.

implementation status: not implemented

The build copies the "pull requests to develop" and "push" workflows to the `.github/workflows` directory if a `.git` directory is present. TODO

The CM system is github

How to adapt:

- use github. configure the project in github in the following way:
  - in General:
    - the default branch is "develop"
    - in "Pull Request" allow only squash merging
    - Auto-close issues with merged linked pull requests 
  - in Rules/Rulesets add the following rules
    - pull request rule
      - name: "pull requests to develop"
      - enforcement status: active
      - bypass list: empty 
      - target branches: include default branch, so there will be one branch listed: "default"
      - do not restrict creations FIXME: check if needed for pull requests
      - do not restrict updates FIXME: check if needed for pull requests, and if require pull request disallows direct git push
      - restrict deletions
      - require linear history
      - require signed commits
      - require pull request before merging
        - required approvals: at least 1
        - Dismiss stale pull request approvals when new commits are pushed
        - do not require review from code owners (if there are multiple teams working on a project, split it)
        - require approval of the most recent reviewable push
        - Require conversation resolution before merging
        - do not Automatically request Copilot code review
        - allowed merge methods: only squash
      - require status checks to pass
        - Require branches to be up to date before merging
        - status checks that are required: build
      - block force pushes
    - push rule
      - name: "push"
      - enforcement status: active
      - bypass list: empty 
      - target branches: all
      - do not restrict creations FIXME: check if needed for pull requests
      - do not restrict updates FIXME: check if needed for pull requests, and if require pull request disallows direct git push
      - do not restrict deletions
      - require linear history
      - require signed commits
      - do not require pull request before merging
      - do not require status checks to pass
      - block force pushes
  - in Actions/general
    - Allow <owner>, and select non-<owner>, actions and reusable workflows 
		 - Allow actions created by github
    - Require actions to be pinned to a full-length commit SHA 
    - Artifact and log retention: at least 90 days
    - Require approval for all external contributors 
    - Workflow permission: read and write permission
    - Do not Allow GitHub Actions to create and approve pull requests 

  - Models: disabled
  - Environment: no environment
  - In advanced security:
    - Enable Private vulnerability reporting 
    - Enable Dependency graph
    - Disable Automatic dependency submission (the github actions do that)
    - Enable dependabot alerts
    - Disable the "Dismiss low-impact alerts for development-scoped dependencies " rule
    - Enable Dependabot security updates
    - Enable Secret Protection
    - Enable Push Protection
  - In Secrets and variables/Actions add the following secrets:
    - GPG_PASSPHRASE is the passphrase of the GPG key
    - GPG_PRIVATE_KEY is the GPG key used to sign artifacts. Publish the private key of it to keyserver.ubuntu.com.
    - KNOWN_HOSTS is a .ssh/known_hosts file containing the host key of the host where the maven site is rsynced to
    - NEXUS_PASSWORD is the password of the central user token for deploying artifacts, generated at https://central.sonatype.com/account
    - NEXUS_USERNAME is the username of the same
    - SSH_CONFIG is a .ssh/config file defining properties for authentication to the host where the maven site is rsynced to (so not even the hostname should be exposed in pom.xml)
    - SSH_KEY is the ssh key to authenticate to the host where the maven site is rsynced to
    - Do not define variables for actions, or secrets for codespaces or dependabot
  - Do not enable github apps
- if you deviate from any of this, document the reasons for the evaluator


How to check:

- check that the repository is configured according to the above list, and any deviations are documented and reasonable

##Content and presentation elements

### ALC_CMC.5.1C
The TOE shall be labelled with its unique reference.

- deployed jars contain version in their filename. OSGI bundles use Bundle-Version in MANIFEST.MF
- In case of a deliverable which provides interactive services, the developer is responsible to provide and document a way for users to obtain the version number of the TOE in the way appropriate for the technology:
  - command line tools must have a `--version` parameter according to GNU standards
  - graphical user interfaces must display the version number on their main screen or have a `About` menupoint. In case of complex menu tree, it should be `Help/About`
  - web applications must have a '/version' URL

How to adapt:

- in case the TOE provides interactive services, provide the version number in the above way and document it.

How to check:

- check the filename of deployed jars
- check the MANIFEST.MF of OSGI bundles
- check the deliverables providing interactive services for the version number

### ALC_CMC.5.2C
The CM documentation shall describe the method used to uniquely identify the configuration
items.

see ALC_CMC.5.1C

### ALC_CMC.5.3C
The CM documentation shall justify that the acceptance procedures provide for an
adequate and appropriate review of changes to all configuration items.

implementation status: implemented

All configuration items are defined in the implementation representation, and the github settings make sure that all changes are reviewed.

No further developer or evaluation action is required.

### ALC_CMC.5.4C
The CM system shall uniquely identify all configuration items.

implementation status: not implemented

The `Require-Bundle` field in MANIFEST.MF and the `<plugin>` tags in the eclipse product definitions must designate a specific version of the artifacts.
The .konveyor/tools/pickVersionsFromTarget.sh updates the above version definitions based on available versions in the defined target. TODO

How to adapt:
- when the target definition is changed, run .konveyor/tools/pickVersionsFromTarget.sh to update MANIFEST.MF and product files.
- check that the right versions are picked by the tool

How to check:
- check all MANIFEST.MF and target files to ensure that specific versions are referenced
- check the implementation representation that all other dependencies are referenced with an exact version number

### ALC_CMC.5.5C
The CM system shall provide automated controls such that only authorized changes are made to the configuration items.

implementation status: implemented

All configuration items are defined in the implementation representation, and the github settings make sure that all changes are reviewed.

No further developer or evaluation action is required.

### ALC_CMC.5.6C
The CM system shall support the production of the TOE by automated means.

the github actions check and deploy the TOE by automated means
see ALC_CMC.5.3D

### ALC_CMC.5.7C
The CM system shall ensure that the person responsible for accepting a configuration item into CM is not the person who developed it.

github ensures that
see ALC_CMC.5.3D

### ALC_CMC.5.8C
The CM system shall identify the configuration items that comprise the TSF.

See ALC_CMS.5.1D

### ALC_CMC.5.9C
The CM system shall support the audit of all changes to the TOE by automated means, including the originator, date, and time in the audit trail.

Git log does just that
see ALC_CMC.5.3D

### ALC_CMC.5.10C
The CM system shall provide an automated means to identify all other configuration items that are affected by the change of a given configuration item

implementation status: not implemented

The build generates dependency graph of all artifacts to the maven site TODO

### ALC_CMC.5.11C
The CM system shall be able to identify the version of the implementation representation from which the TOE is generated.

The released TOE version is {describe.tag}.${describe.distance}, where {describe.tag} is a tag in the github repository, and ${describe.distance} is the commit distance on the develop branch from it.
The version 0.0.1 is generated from a commit not in the develop branch, and not TOE.
see ALC_CMC.5.3D

### ALC_CMC.5.12C
The CM documentation shall include a CM plan.

- The developers fork the github repository and make changes in that in unspecified ways. However supported best practices include:
  - using the Eclipse IDE with the eclipse-pmd plugin
  - naming branches implementing features `feature/<some identifier>` and naming branches fixing bugs `bug/<some identifier>`
- The developer marks an issue in the repository as "in progress" when starts to work on it.
- The developer implements the feature or fixes the bug described in the issue, making sure that all coding standards are adhered to, and the tests demonstrate that the feature is correctly implemented/ the bug is correctly fixed.
- The developer commits the changes, using a `closes:` tag referencing the issue.
- The developer pushes the changes to the forked repository, and issues a pull request to the repository of the TOE.
- If the automated build fails, (one of) the code owner(s) closes the pull request.
- If the automated build succeeds, some reviewer reviews the changes, makig sure that
  - The changes are indeed implement the feature or fix the bug described in the issue
  - The test descriptions assess that the feature is fully implemented/ the bug is fully fixed.
  - The tests actually test what the test description assess.
  - If the change is not changing the behaviour of the TOE (the only case where lack of tests is acceptable), the reviewer reviews the evidence provided by the developer and/or the build process, and/or the generated TOE itself to make sure that the change is indeed correct.
  - All the implementation standards and non-conflicting best practices are adhered to.
- When the review is approved and all the disputes are positively resolved, (one of) the code owner(s) merges the pull request,
  - editing the commit message to include all relevent information and only the relevant information, including the `closes:` tag.

If the deploy workflow fails for some reason, or a security issue arises, no other work is done on the TOE other than resolving that.

How to adapt:

Follow the procedure above (most of which is enforced by the project settings on github).

How to check:

- Review the pull requests and git diffs to make sure that the above procedure is followed.
- Review the evidence provided by the developer/build process and the TOE to make sure that the change is correct.

### ALC_CMC.5.13C
The CM plan shall describe how the CM system is used for the development of the TOE.

see ALC_CMC.5.12C

### ALC_CMC.5.14C
The CM plan shall describe the procedures used to accept modified or newly created configuration items as part of the TOE.

see ALC_CMC.5.12C

### ALC_CMC.5.15C
The evidence shall demonstrate that all configuration items are being maintained under the CM system.

see ALC_CMS.5.1D

### ALC_CMC.5.16C
The evidence shall demonstrate that the CM system is being operated in accordance with the CM plan.

see ALC_CMC.5.12C


## Evaluator action elements

### ALC_CMC.5.1E
The evaluator shall confirm that the information provided meets all requirements for content and
presentation of evidence.

See "how to check" sections above.

### ALC_CMC.5.2E
The evaluator shall determine that the application of the production support procedures
results in a TOE as provided by the developer for testing activities.

See "how to check" sections above.

