package ca.uhn.fhir.jpa.ips.strategy;

import ca.uhn.fhir.jpa.ips.api.Section;
import ca.uhn.fhir.jpa.ips.jpa.DefaultJpaIpsGenerationStrategy;
import ca.uhn.fhir.jpa.ips.jpa.JpaSectionSearchStrategyCollection;
import ca.uhn.fhir.jpa.ips.strategy.ImmunizationNoInfoR4Generator;
import ca.uhn.fhir.jpa.ips.jpa.section.ImmunizationsJpaSectionSearchStrategy;
import org.hl7.fhir.r4.model.Immunization;

public class LacBundleIpsGenerationStrategy extends DefaultJpaIpsGenerationStrategy {


	@Override
	public String getBundleProfile() {
		System.out.println("Lac strategy profile requested!");
		return "http://lacpass.racsel.org/StructureDefinition/lac-bundle";
	}

    @Override
	public String getCompositionProfile() {
		return "http://lacpass.racsel.org/StructureDefinition/lac-composition";
	}

}