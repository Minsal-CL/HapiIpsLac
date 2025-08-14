package ca.uhn.fhir.jpa.ips.jpa.section;

import ca.uhn.fhir.jpa.ips.api.IpsSectionContext;
import ca.uhn.fhir.jpa.ips.jpa.IJpaSectionSearchStrategy;
import org.hl7.fhir.r4.model.AllergyIntolerance;

public class EmptyAllergyIntoleranceSearchStrategy
        implements IJpaSectionSearchStrategy<AllergyIntolerance> {

    @Override
    public boolean shouldInclude(IpsSectionContext<AllergyIntolerance> ctx, AllergyIntolerance candidate) {
        // Nunca incluir -> obligará a usar el NoInfoGenerator
        return false;
    }
}
