package ca.uhn.fhir.jpa.ips.jpa.section;

import ca.uhn.fhir.jpa.ips.api.IpsSectionContext;
import ca.uhn.fhir.jpa.ips.jpa.IJpaSectionSearchStrategy;
import org.hl7.fhir.r4.model.Condition;

public class EmptyConditionSearchStrategy implements IJpaSectionSearchStrategy<Condition> {

    @Override
    public boolean shouldInclude(IpsSectionContext<Condition> ctx, Condition candidate) {
        // Nunca incluir -> obligará a usar el NoInfoGenerator
        return false;
    }
}
