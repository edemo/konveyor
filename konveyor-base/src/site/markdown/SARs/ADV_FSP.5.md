# ADV_FSP.5 Complete semi-formal functional specification with additional error information

## Developer action elements
### ADV_FSP.5.1D
The developer shall provide a functional specification.

implementation status: not implemented

The business object (which are directly mapped to java records) and the implementation level of the metamodel makes sure that the model have a functional specification.

How to adapt:

no additional deeds beyond what is described in ADV_INT.3.1D

How to check:

no additional deeds beyond what is described in ADV_INT.3.1D

### ADV_FSP.5.2D
The developer shall provide a tracing from the functional specification to the SFRs.

implementation status: not implemented

The metamodel and documentation generation takes care of this.

How to adapt:

no additional deeds beyond what is described in ADV_INT.3.1D

How to check:

no additional deeds beyond what is described in ADV_INT.3.1D


## Content and presentation elements

### ADV_FSP.5.1C
The functional specification shall completely represent the TSF.

implementation status: not implemented

The metamodel and documentation generation takes care of this.

How to adapt:

no additional deeds beyond what is described in ADV_INT.3.1D

How to check:

no additional deeds beyond what is described in ADV_INT.3.1D

### ADV_FSP.5.2C
The functional specification shall describe the TSFI using a semi-formal style.

implementation status: not implemented

The metamodel and documentation generation takes care of this.

All TOE functionality, including the TSFI (aka the interfaces of the TOE) are documented.

The design model is a semi-formal description.

How to adapt:

no additional deeds beyond what is described in ADV_INT.3.1D

How to check:

no additional deeds beyond what is described in ADV_INT.3.1D


### ADV_FSP.5.3C
The functional specification shall describe the purpose and method of use for all TSFI.

implementation status: not implemented

The metamodel and documentation generation takes care of this.

How to adapt:

no additional deeds beyond what is described in ADV_INT.3.1D

How to check:

no additional deeds beyond what is described in ADV_INT.3.1D


### ADV_FSP.5.4C
The functional specification shall identify and describe all parameters associated with each TSFI.

implementation status: not implemented

The metamodel and documentation generation takes care of this.

Parameters are part of the process level of the metamodel

How to adapt:

no additional deeds beyond what is described in ADV_INT.3.1D

How to check:

no additional deeds beyond what is described in ADV_INT.3.1D


### ADV_FSP.5.5C
The functional specification shall describe all actions associated with each TSFI.

implementation status: not implemented

The metamodel and documentation generation takes care of this.

Actions (process steps) are part of the process level of the metamodel

How to adapt:

no additional deeds beyond what is described in ADV_INT.3.1D

How to check:

no additional deeds beyond what is described in ADV_INT.3.1D


### ADV_FSP.5.6C
The functional specification shall describe all direct error messages that may result from an
invocation of each TSFI.

implementation status: not implemented

Errors are part of the process level of the metamodel

TODO:
The @CatchAndRethrow annotation associated with a simple implementation of wrapping the annotated method in a try-catch block, rethrowing a FIXME error for any error captured.

The build makes sure that
- all interface functions are annotated with one of the guard annotations defined in the model.
- classes annotated with @CatchandRethrow have the FIXME error associated with them in the model

How to adapt:

- Define the guard annotation names in your model and the errors it can throw.
- If the used framework does not contain such annotation, use CatchAndRethrow
- Annotate all interface methods with a guard annotation.
- Annotate the initialization of the TOE with a guard annotation.
- Check for concievable error scenarios in the integration tests.

How to check:

- Make sure that the aop weaving mechanisms are not tempered with.
- Check for tests of rethrown errors in integration test of each interface, or the reasoning of lack thereof
- Check @Glue code ensuring that all initializations are guarded.

### ADV_FSP.5.7C
The functional specification shall describe all error messages that do not result from an
invocation of a TSFI.

implementation status: not implemented

The metamodel and documentation generation takes care of this.

Errors are part of the process level of the metamodel, and described for all parts of the TOE,
including those which are not part of the TSFI.

Due to ADV_FSP.5.6C implementation all possible errors encountered in the TSFI invocation are documented, even those which have reasons beyond the incorrect invocation of the TSFI (e.g. configuration problems).

How to adapt:

no additional deeds beyond what is described in ADV_FSP.5.6C

How to check:

no additional deeds beyond what is described in ADV_FSP.5.6C

### ADV_FSP.5.8C
The functional specification shall provide a rationale for each error message contained in
the TSF implementation yet does not result from an invocation of a TSFI.

implementation status: not implemented

The metamodel and documentation generation takes care of this.

Errors are part of the process level of the metamodel, and described for all parts of the TOE,
including those which are not part of the TSFI.

Hence all errors directly generated by the TOE are documented, and all errors generated by dependent code are rethrown as documented errors.

How to adapt:

no additional deeds beyond what is described in ADV_FSP.5.6C

How to check:

no additional deeds beyond what is described in ADV_FSP.5.6C

### ADV_FSP.5.9C
The tracing shall demonstrate that the SFRs trace to TSFIs in the functional specification.

implementation status: not implemented

The metamodel and documentation generation takes care of this.

The correspondence between the requirement level (SFRs) and process and implementation level (including the TSFIs) is fully traced for the entire TOE.

How to adapt:

no additional deeds beyond what is described in ADV_INT.3.1D

How to check:

no additional deeds beyond what is described in ADV_INT.3.1D


## Evaluator action elements

### ADV_FSP.5.1E
The evaluator shall confirm that the information provided meets all requirements for content and
presentation of evidence.

See "How to check" sections above.

### ADV_FSP.5.2E
The evaluator shall determine that the functional specification is an accurate and complete
instantiation of the SFRs.

See "How to check" sections above.

