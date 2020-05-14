package com.pdio.jhipsterapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.pdio.jhipsterapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.pdio.jhipsterapp.service..")
            .or()
            .resideInAnyPackage("com.pdio.jhipsterapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.pdio.jhipsterapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
