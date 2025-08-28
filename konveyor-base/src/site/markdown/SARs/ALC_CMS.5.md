# ALC_CMS.5 Development tools CM coverage

## Developer action elements

### ALC_CMS.5.1D
The developer shall provide a configuration list for the TOE.

implementation status: partially implemented

The build generates a maven site, deployed using rsync using the pom variable `${konveyor.uploadRsyncURI}`.
The maven site is available at the url described by in the pom under `project/distributionManagement/site[@id='website']/url
The site contains all the evaluation evidence required by the SARs, including:
- the ST TODO
- the architecture model of the TOE TODO
- API documentation TODO: check content and presentation 
- the TOE itself:
  - maven packages published in sonatype central, with a link to the artifact and the maven dependency tag, which includes the version number TODO
  - eclipse products published to the maven site, along with the version number TODO
- the parts that comprise the TOE beyond the implementation representation, aka the dependencies:
  - output of `mvn dependency:tree` containing the exact version number TODO
  - list of dependencies defined in MANIFEST.MF TODO
  - list of actually packaged dependencies of the eclipse products, with exact version number TODO
- reference to the github repository containing the implementation representation, with the commit identifier of the built version TODO check it
- reference to the issue list on github containing the issues marked as security, where their status and resolution documented TODO
- development tool informationm containing:
  - reference to the maven version and java version used TODO
  - list of maven plugins and their declared dependencies within the build section of the effective pom TODO
  - list of installed debian packages in the build environment with version numbers TODO

How to adapt:
- set the `${konveyor.uploadRsyncURI}` in the parent pom.
- set the url in `project/distributionManagement/site[@id='website']/url to the url where the maven site is located
- use only maven dependencies, debian packages and requred bundles in MANIFEST.MF. If additional tools are needed, package them as maven packages and publish to sonatype central
- close issues only
 - through pull requests or
 - with the reference to the commit ID/version which solves it
 - with WONTFIX or INVALID tags

How to check:
- Make sure that the site contains the items listed above
- Make sure that the documentation generation is not tampered with.
- Make sure that no undocumented dependencies or tools are used
- When the identity of the developer of a specific part of the TOE is needed to be figured out, use `git blame`

## Content and presentation elements

### ALC_CMS.5.1C
The configuration list shall include the following: the TOE itself; the evaluation evidence required
by the SARs; the parts that comprise the TOE; the implementation representation; security flaw
reports and resolution status; and development tools and related information.

see ALC_CMS.5.1D

### ALC_CMS.5.2C
The configuration list shall uniquely identify the configuration items.

all packages (excluding the informal MANIFEST.MF dependency list) are listed with version number.
the implementation representation reference contains the commit id of the build
in case of github issues there is no point of talking about a version. The closed issues reference 

No additional actions above ALC_CMS.5.1D

### ALC_CMS.5.3C
For each TSF relevant configuration item, the configuration list shall indicate the developer of the
item.

The command `git blame` can be used to identity the developer in the implementation representation.

No additional actions above ALC_CMS.5.1D

## Evaluator action elements

### ALC_CMS.5.1E
The evaluator shall confirm that the information provided meets all requirements for content and
presentation of evidence

See "What to check" sections above

