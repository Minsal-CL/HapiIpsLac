package ca.uhn.fhir.jpa.ips.jpa.section;

import ca.uhn.fhir.jpa.ips.api.IpsSectionContext;
import ca.uhn.fhir.jpa.ips.jpa.IJpaSectionSearchStrategy;
import org.hl7.fhir.r4.model.MedicationDispense;

public class EmptyMedicationDispenseSearchStrategy
        implements IJpaSectionSearchStrategy<MedicationDispense> {

    @Override
    public boolean shouldInclude(IpsSectionContext<MedicationDispense> ctx, MedicationDispense candidate) {
        return false;
    }
}
