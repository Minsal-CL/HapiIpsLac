package ca.uhn.fhir.jpa.ips.strategy;

import ca.uhn.fhir.jpa.ips.api.Section;
import ca.uhn.fhir.jpa.ips.jpa.DefaultJpaIpsGenerationStrategy;
import ca.uhn.fhir.jpa.ips.jpa.JpaSectionSearchStrategyCollection;
import ca.uhn.fhir.jpa.ips.strategy.ImmunizationNoInfoR4Generator;
import ca.uhn.fhir.jpa.ips.jpa.section.ImmunizationsJpaSectionSearchStrategy;
import org.hl7.fhir.r4.model.Immunization;

public class LacBundleDdccIpsGenerationStrategy extends DefaultJpaIpsGenerationStrategy {


	@Override
	public String getBundleProfile() {
		System.out.println("Lac strategy profile requested!");
		return "http://lacpass.racsel.org/StructureDefinition/lac-bundle-ddcc";
	}

    @Override
	public String getCompositionProfile() {
		return "http://lacpass.racsel.org/StructureDefinition/lac-composition-ddcc";
	}

	/**
	 * Este método se llama desde initialize() de la clase padre.
	 */
	@Override
	protected void addSections() {
		addJpaSectionAllergyIntolerance();
		addJpaSectionMedicationSummary();
		addJpaSectionProblemList();
        addJpaSectionImmunizationsNoInfo();
		addJpaSectionProcedures();
		addJpaSectionMedicalDevices();
		addJpaSectionDiagnosticResults();
		addJpaSectionVitalSigns();
		addJpaSectionPregnancy();
		addJpaSectionSocialHistory();
		addJpaSectionIllnessHistory();
		addJpaSectionFunctionalStatus();
		addJpaSectionPlanOfCare();
		addJpaSectionAdvanceDirectives();		
	}

	/**
	 * Sección de inmunizaciones con NoInfoGenerator.
	 */
	protected void addJpaSectionImmunizationsNoInfo() {
		Section section = Section.newBuilder()
			.withTitle("Historial de Inmunizaciones")
			.withSectionSystem(SECTION_SYSTEM_LOINC)
			.withSectionCode(SECTION_CODE_IMMUNIZATIONS)
			.withSectionDisplay("History of Immunization Narrative")
			.withResourceType(Immunization.class)
			.withProfile("https://hl7.org/fhir/uv/ips/StructureDefinition-Composition-uv-ips-definitions.html#Composition.section:sectionImmunizations")
			.withNoInfoGenerator(new ImmunizationNoInfoR4Generator())
			.build();

		JpaSectionSearchStrategyCollection searchStrategyCollection = JpaSectionSearchStrategyCollection.newBuilder()
			.addStrategy(Immunization.class, new ImmunizationsJpaSectionSearchStrategy())
			.build();

		addJpaSection(section, searchStrategyCollection);
	}
}