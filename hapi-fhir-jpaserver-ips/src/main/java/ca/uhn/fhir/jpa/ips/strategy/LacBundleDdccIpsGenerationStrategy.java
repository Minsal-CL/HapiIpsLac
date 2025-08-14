package ca.uhn.fhir.jpa.ips.strategy;

import ca.uhn.fhir.jpa.ips.api.Section;
import ca.uhn.fhir.jpa.ips.jpa.DefaultJpaIpsGenerationStrategy;
import ca.uhn.fhir.jpa.ips.jpa.JpaSectionSearchStrategyCollection;
import ca.uhn.fhir.jpa.ips.strategy.AllergyIntoleranceNoInfoR4Generator;
import ca.uhn.fhir.jpa.ips.strategy.ImmunizationNoInfoR4Generator;
import ca.uhn.fhir.jpa.ips.strategy.MedicationNoInfoR4Generator;
import ca.uhn.fhir.jpa.ips.strategy.ProblemNoInfoR4Generator;
import ca.uhn.fhir.jpa.ips.jpa.section.AllergyIntoleranceJpaSectionSearchStrategy;
import ca.uhn.fhir.jpa.ips.jpa.section.ImmunizationsJpaSectionSearchStrategy;
import ca.uhn.fhir.jpa.ips.jpa.section.MedicationSummaryJpaSectionSearchStrategyMedicationAdministration;
import ca.uhn.fhir.jpa.ips.jpa.section.MedicationSummaryJpaSectionSearchStrategyMedicationDispense;
import ca.uhn.fhir.jpa.ips.jpa.section.MedicationSummaryJpaSectionSearchStrategyMedicationRequest;
import ca.uhn.fhir.jpa.ips.jpa.section.MedicationSummaryJpaSectionSearchStrategyMedicationStatement;
import ca.uhn.fhir.jpa.ips.jpa.section.EmptyMedicationAdministrationSearchStrategy;
import ca.uhn.fhir.jpa.ips.jpa.section.EmptyMedicationDispenseSearchStrategy;
import ca.uhn.fhir.jpa.ips.jpa.section.EmptyMedicationRequestSearchStrategy;
import ca.uhn.fhir.jpa.ips.jpa.section.EmptyMedicationStatementSearchStrategy;
import ca.uhn.fhir.jpa.ips.jpa.section.EmptyConditionSearchStrategy;

import org.hl7.fhir.r4.model.MedicationAdministration;
import org.hl7.fhir.r4.model.MedicationDispense;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.AllergyIntolerance;

// import java.util.concurrent.locks.Condition;

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
		addJpaSectionAllergyIntoleranceNoInfoOnly();
		addJpaSectionMedicationSummaryNoInfoOnly();
		addJpaSectionProblemListNoInfoOnly();
        addJpaSectionImmunizationsNoInfo();	
	}

	/**
	 * Sección de inmunizaciones con NoInfoGenerator.
	 */
	
	protected void addJpaSectionAllergyIntoleranceNoInfoOnly() {
		Section section = Section.newBuilder()
			.withTitle("Alergías e Intolerancias")
			.withSectionSystem(SECTION_SYSTEM_LOINC)
			.withSectionCode(SECTION_CODE_ALLERGY_INTOLERANCE)
			.withSectionDisplay("Allergies and adverse reactions Document")
			.withResourceType(org.hl7.fhir.r4.model.AllergyIntolerance.class)
			.withProfile("https://hl7.org/fhir/uv/ips/StructureDefinition-Composition-uv-ips-definitions.html#Composition.section:sectionAllergies")
			.withNoInfoGenerator(new AllergyIntoleranceNoInfoR4Generator())
			.build();

		JpaSectionSearchStrategyCollection strategies = JpaSectionSearchStrategyCollection.newBuilder()
			.addStrategy(org.hl7.fhir.r4.model.AllergyIntolerance.class,
						new ca.uhn.fhir.jpa.ips.jpa.section.EmptyAllergyIntoleranceSearchStrategy())
			.build();

		addJpaSection(section, strategies);
	}

	protected void addJpaSectionMedicationSummaryNoInfoOnly() {
		Section section = Section.newBuilder()
			.withTitle("Lista de Medicamentos")
			.withSectionSystem(SECTION_SYSTEM_LOINC)
			.withSectionCode(SECTION_CODE_MEDICATION_SUMMARY)
			.withSectionDisplay("History of Medication use Narrative")
			.withResourceType(org.hl7.fhir.r4.model.MedicationStatement.class)
			.withResourceType(org.hl7.fhir.r4.model.MedicationRequest.class)
			.withResourceType(org.hl7.fhir.r4.model.MedicationAdministration.class)
			.withResourceType(org.hl7.fhir.r4.model.MedicationDispense.class)
			.withProfile("https://hl7.org/fhir/uv/ips/StructureDefinition-Composition-uv-ips-definitions.html#Composition.section:sectionMedications")
			.withNoInfoGenerator(new MedicationNoInfoR4Generator())
			.build();

		JpaSectionSearchStrategyCollection strategies = JpaSectionSearchStrategyCollection.newBuilder()
			.addStrategy(org.hl7.fhir.r4.model.MedicationAdministration.class,
						new ca.uhn.fhir.jpa.ips.jpa.section.EmptyMedicationAdministrationSearchStrategy())
			.addStrategy(org.hl7.fhir.r4.model.MedicationDispense.class,
						new ca.uhn.fhir.jpa.ips.jpa.section.EmptyMedicationDispenseSearchStrategy())
			.addStrategy(org.hl7.fhir.r4.model.MedicationRequest.class,
						new ca.uhn.fhir.jpa.ips.jpa.section.EmptyMedicationRequestSearchStrategy())
			.addStrategy(org.hl7.fhir.r4.model.MedicationStatement.class,
						new ca.uhn.fhir.jpa.ips.jpa.section.EmptyMedicationStatementSearchStrategy())
			.build();

		addJpaSection(section, strategies);
	}

	protected void addJpaSectionProblemListNoInfoOnly() {
		Section section = Section.newBuilder()
			.withTitle("Lista de Problemas")
			.withSectionSystem(SECTION_SYSTEM_LOINC)
			.withSectionCode(SECTION_CODE_PROBLEM_LIST)
			.withSectionDisplay("Problem list - Reported")
			.withResourceType(org.hl7.fhir.r4.model.Condition.class)
			.withProfile("https://hl7.org/fhir/uv/ips/StructureDefinition-Composition-uv-ips-definitions.html#Composition.section:sectionProblems")
			.withNoInfoGenerator(new ProblemNoInfoR4Generator())
			.build();

		JpaSectionSearchStrategyCollection strategies = JpaSectionSearchStrategyCollection.newBuilder()
			.addStrategy(org.hl7.fhir.r4.model.Condition.class,
				new ca.uhn.fhir.jpa.ips.jpa.section.EmptyConditionSearchStrategy())
			.build();

		addJpaSection(section, strategies);
	}

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

