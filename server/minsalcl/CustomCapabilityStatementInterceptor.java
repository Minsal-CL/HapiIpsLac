package server.minsalcl;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.server.interceptor.InterceptorAdapter;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.instance.model.api.IBaseConformance;
import org.hl7.fhir.r4.model.CapabilityStatement;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.Enumeration;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.Narrative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomCapabilityStatementInterceptor extends InterceptorAdapter {

    @Hook(Pointcut.SERVER_CAPABILITY_STATEMENT_GENERATED)
    public void customize(IBaseConformance theCapabilityStatement) {
        // Cast to the appropriate version
        CapabilityStatement cs = (CapabilityStatement) theCapabilityStatement;

        List<CanonicalType> canonicalTypes = Arrays.asList(
                new CanonicalType("http://lacpass.racsel.org/ImplementationGuide/lacpass.racsel.org"),
                new CanonicalType("http://hl7.org/fhir/uv/ips/ImplementationGuide/hl7.fhir.uv.ips"),
                new CanonicalType("https://profiles.ihe.net/ITI/MHD/ImplementationGuide/ihe.iti.mhd")
        );

        // Lista de Formatos
        // List<CodeType> codeTypes = Arrays.asList(
        //         new CodeType("application/fhir+xml"),
        //         new CodeType("xml"),
        //         new CodeType("application/fhir+json"),
        //         new CodeType("json")
        // );

        // // Lista de recursos a conservar
        // List<ResourceType> resourcesToKeep = Arrays.asList(
        //         ResourceType.AuditEvent,
        //         ResourceType.AllergyIntolerance,
        //         ResourceType.Bundle,
        //         ResourceType.Patient,
        //         ResourceType.Encounter,
        //         ResourceType.Condition,
        //         ResourceType.Composition,
        //         ResourceType.Immunization,
        //         ResourceType.Practitioner,
        //         ResourceType.Organization,
        //         ResourceType.PractitionerRole,
        //         ResourceType.CodeSystem,
        //         ResourceType.ValueSet,
        //         ResourceType.Observation,
        //         ResourceType.Provenance,
        //         ResourceType.Medication,
        //         ResourceType.Location,
        //         ResourceType.DiagnosticReport,
        //         ResourceType.ImagingStudy,
        //         ResourceType.MedicationRequest,
        //         ResourceType.MedicationStatement,
        //         ResourceType.Procedure,
        //         ResourceType.DeviceUseStatement,
        //         ResourceType.Parameters
        // );

        // List<Enumeration<CapabilityStatement.ReferenceHandlingPolicy>> referenceHandlingPolicy = Arrays.asList(
        //     new Enumeration<>(new CapabilityStatement.ReferenceHandlingPolicyEnumFactory(), CapabilityStatement.ReferenceHandlingPolicy.LITERAL),
        //     new Enumeration<>(new CapabilityStatement.ReferenceHandlingPolicyEnumFactory(), CapabilityStatement.ReferenceHandlingPolicy.LOGICAL)
        // );

        // List<CapabilityStatement.CapabilityStatementRestResourceComponent> filteredResources = cs.getRest().stream()
        // .flatMap(rest -> rest.getResource().stream())
        // .map(resource -> {
        //     String typeName = resource.getType(); // Obtener el nombre del tipo de recurso como cadena
        //     ResourceType type = ResourceType.fromCode(typeName); // Convertir la cadena en un objeto ResourceType

        //     // Verificar si el tipo de recurso está en la lista de recursos a conservar
        //     if (type != null && resourcesToKeep.contains(type)) {
        //         // Si es CodeSystem, ValueSet o StructureDefinition, actualizar las interacciones
        //         if (ResourceType.CodeSystem.equals(type) || ResourceType.ValueSet.equals(type) || ResourceType.StructureDefinition.equals(type)) {
        //             List<CapabilityStatement.TypeRestfulInteraction> interactionsToKeep = Arrays.asList(
        //                     CapabilityStatement.TypeRestfulInteraction.VREAD,
        //                     CapabilityStatement.TypeRestfulInteraction.READ,
        //                     CapabilityStatement.TypeRestfulInteraction.SEARCHTYPE
        //             );
        //             resource.getInteraction().removeIf(interaction -> !interactionsToKeep.contains(interaction.getCode()));
        //             resource.setVersioning(CapabilityStatement.ResourceVersionPolicy.NOVERSION);
        //             resource.setConditionalCreate(false);
        //             resource.setConditionalUpdate(false);
        //             resource.setConditionalDelete(CapabilityStatement.ConditionalDeleteStatus.NOTSUPPORTED);
        //         } else {
        //             resource.setReferencePolicy(referenceHandlingPolicy);
        //         }

        //         // Limpiar los RevIncludes
        //         List<StringType> searchRevInclude = resource.getSearchRevInclude(); // Obtener la lista actual de RevIncludes
        //         List<StringType> filteredRevInclude = searchRevInclude.stream()
        //                 .filter(revInclude -> {
        //                     String[] parts = revInclude.getValue().split(":"); // Dividir el RevInclude en partes por ":"
        //                     if (parts.length > 0) {
        //                         String resourceName = parts[0]; // Tomar la parte antes del ":"
        //                         return resourcesToKeep.stream().anyMatch(resourceToKeep -> resourceToKeep.name().equals(resourceName));
        //                     }
        //                     return false;
        //                 })
        //                 .collect(Collectors.toList());

        //         // Actualizar los RevIncludes filtrados
        //         resource.setSearchRevInclude(filteredRevInclude);

        //         return resource;
        //     }

        //     return null; // Descartar otros recursos
        // })
        // .filter(Objects::nonNull)
        // .collect(Collectors.toList());



        // // Actualizar los recursos en el CapabilityStatement
        // cs.getRest().forEach(rest -> rest.setResource(new ArrayList<>(filteredResources)));

        // Customize the CapabilityStatement as desired
        cs.setId("server.ministeriosalud.repositorioclinico");
        cs.setName("Servidor Ministerio de Salud - Repositorio Clínico");
        cs.setVersion("1.0");
        cs.setPublisher("Ministerio de Salud");
        // cs.setFormat(codeTypes);
        cs.setImplementationGuide(canonicalTypes);
        cs.getImplementation().setDescription("Repositorio de Datos Clínico");
        cs.getSoftware().setName("Servidor Minsiterio de Salud - Repositorio Clínico")
                .setVersion("1.0")
                .setReleaseDateElement(new DateTimeType("2025-05-12"));
    }
}
