# ADV_INT.3 Minimally complex internals

## Developer action elements
### ADV_INT.3.1D
The developer shall design and implement the entire TSF such that it has well-structured
internals.

implementation status: not implemented

See README.md for the description of the metamodel, its constraints and build-time checks.

How to adapt:

- Design the TOE with Inez using the konveyor metamodel
- Code according to the design
- Design the TOE without unnecessary exclusion markers in the model
- Implement the TOE according to the generated github issues.

What to check:

- The metamodel, its constraints, the generation of model errors, the ST generation and the PMD checks are not tampered with
- Reasoning for the exclusion markers of the model
- Reasoning for the exclusion markers of the PMD checks


### ADV_INT.3.2D
The developer shall provide an internals description and justification.

implementation status: not implemented

The metamodel and documentation generation takes care of this.

How to adapt:

- design the model based on the metamodel, satisfy the constraints

What to check:

- The TOE is designed using the metamodel provided by this project
- The documentation generation is not tampered with
- The exclusion rationales

## Content and presentation elements
### ADV_INT.3.1C
The justification shall describe the characteristics used to judge the meaning of “well-structured” and “complex”.

- Well-structured is defined by:
  - Having enough design layers,
  - Having enough levels of grouping for the implementation
  - The elements of the design layer and the mplementation are meaningfully grouped

- Complexity means:
  - Having more than one purpose
  - Expressed using unnecessary details (some details are not necessary to reach the purpose)

Complexity can be captured in formal means with limits of the size of contents.
Metamodel constraints and build checks are covering this.

How to adapt:

- refer to this section and amend if necessary
- create automated checks for the amendments if necessary

What to check:

- this section is referenced and if there are amendments they are meaningful
- if there are amendments, check their automated checks or the rationale of the lack thereof

### ADV_INT.3.2C
The TSF internals description shall demonstrate that the entire TSF is well-structured and is not
overly complex.


How to adapt:

- make sure that every design element
  - have exactly one purpose
  - described in a clear, concise and unambigous manner
  - does not contain details which are unnecessary to reach the purpose
  - everywhere where it is possible document by relationships and checks, not by words

What to check:

- All element descriptions in the policy, objective and requirement levels are clear, concise, unambigous and have exactly one purpose
- If descriptions are given in lower levels, they are clear, concise, unambigous, have exactly one purpose, and actually implemented.
- Design elements below policy layer are meaningfully grouped by higher level design elements
- Implementation units have one purpose and meaningfully grouped by grouping of the implementation
- The coverage of each elements in the model are full, makes sense, and not excessive (automated tools check the existence of the coverage, but cannot check its meaningfullness in any assurance without using natural intelligence).

## Evaluator action elements
### ADV_INT.3.1E
The evaluator shall confirm that the information provided meets all requirements for content and
presentation of evidence.

See the "What to check" sections above.

### ADV_INT.3.2E
The evaluator shall perform an internals analysis on the entire TSF.

See the "What to check" sections above.

