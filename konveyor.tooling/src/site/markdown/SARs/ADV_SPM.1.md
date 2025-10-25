# ADV_SPM.1 Formal TOE security policy model

## Developer action elements

### ADV_SPM.1.1D
The developer shall provide a formal model for the TSF supported by explanatory text.

implementation status: not implemented

The business object (which are directly mapped to java records) and the implementation level of the metamodel makes sure that the model have a functional specification.

How to adapt:

Do not describe any of the business objects and the implementation level elements.
They are sufficiently and formally described if they adhere to the metamodel, so any extra description is either excessive or results in implementation not formally modeled.

How to check:

Make sure that none of the business objects and the implementation level elements contain non-generated description.

### ADV_SPM.1.2D
The developer shall provide the set of formal properties for the TOE supported by
explanatory text.

implementation status: not implemented

The FIXME metamodel defines the formal properties. TODO
The @LSPP annotation causes the annotated methods to be wrapped to code which checks that labeled security requirements are adhered to in the annotated function.
If the konveyor.spm pom property is set to true, then 

- the build checks that all interface methods are annotated with @LSPP
- the build wraps the annotated function with the LSPP checker
- the build make sure that the generated documentation contains the required documentation to refer to the LSPP, and cover ADV_SPM.1 and the not already covered elements of the LSPP.

How to adapt:

- Use the FIXME metamodel when modeling the TOE.
- Make sure that the konveyor.spm pom property is turned on in the parent pom and not turned off anywhere.
- Annotate all interface functions with the @LSPP annotation
- Provide the missing parts of the LSPP related documentation (marked by model errors).


How to check:

- Check that the FIXME metamodel is used when modeling the TOE.
- Check that the konveyor.spm pom property is turned on in the parent pom and not turned off anywhere.
- Check the rationale for any related exemption markers.
- Check that the developer-provided parts of the LSPP are correct and correspond to the model and exemption markers.
- Make sure that the related code generation and build checks are not tampered with.


### ADV_SPM.1.3D
The developer shall provide a formal proof that the model satisfies the formal properties
supported by explanatory text.

FIXME: use formal proof from the literature

### ADV_SPM.1.4D
The developer shall provide a correspondence rationale between the formal model and
the functional specification.

TODO: part of the functionality turned on by konveyor.spm

### ADV_SPM.1.5D
The developer shall provide a semi-formal demonstration of correspondence between the
formal model and any semi-formal functional specification.

TODO: part of the functionality turned on by konveyor.spm

### ADV_SPM.1.6D
The developer shall provide a formal proof of correspondence between the formal model
and any formal functional specification.

TODO: part of the functionality turned on by konveyor.spm

## Content and presentation elements

The rest is TODO

### ADV_SPM.1.1C
The formal model, properties and proofs shall be defined using a well-founded
mathematical theory.


### ADV_SPM.1.2C
The explanatory text shall cover the entire formal model, formal properties and proofs,
including instructions for reproducing the proofs and the correspondence rationale, and
it shall provide a rationale for the modeling and verification choices.

### ADV_SPM.1.3C
The formal model shall cover the complete set of SFRs that define the TSF.

### ADV_SPM.1.4C
The formal properties shall cover the complete set of security objectives for the TOE.

### ADV_SPM.1.5C
The formal proof shall show that the formal model satisfies all the formal properties and
that the consistency of the underlying mathematical theory is preserved.

### ADV_SPM.1.6C
The correspondence rationale shall show that the formal properties proven for the formal
model hold for the functional specification.

### ADV_SPM.1.7C
The semi-formal demonstration of correspondence shall show that the formal properties
proven for the formal model hold for any semi-formal functional specification.

### ADV_SPM.1.8C
The formal proof of correspondence shall show that the properties proven for the formal
model hold for any formal functional specification.

### ADV_SPM.1.9C
Any tool used to model or to prove the formal properties or the relationship between the
formal model and the functional specification shall be well-defined and unambiguously
identified and it shall be accompanied by documentation and a rationale of the tool’s
suitability and trustworthiness.

## Evaluator action elements

### ADV_SPM.1.1E
The evaluator shall confirm that the information provided meets all requirements for
content and presentation of evidence.
