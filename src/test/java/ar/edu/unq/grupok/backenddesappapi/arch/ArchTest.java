package ar.edu.unq.grupok.backenddesappapi.arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

class ArchTest {

    private JavaClasses baseClasses;


    @BeforeEach
    public void setup() {
        baseClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("ar.edu.unq.grupok.backenddesappapi");
    }

    @Test
    void persistanceClassesShouldEndWithRepo(){
        classes().that().resideInAPackage("..persistence..")
                .should().haveSimpleNameEndingWith("Repository").check(baseClasses);
    }

    @Test
    void webserviceClassesShouldEndWithController(){
        classes().that().resideInAPackage("..webservice..")
                .should().haveSimpleNameEndingWith("Controller").check(baseClasses);
    }

    @Test
    void serviceClassesShouldEndWithServiceOrServiceImp(){
        classes().that().resideInAPackage("..service..")
                .should().haveSimpleNameEndingWith("Service").orShould().haveSimpleNameEndingWith("ServiceImpl").check(baseClasses);
    }


    @Test
    void dtoClassesShouldEndWithDTO(){
        classes().that().resideInAPackage("..dto..")
                .should().haveSimpleNameEndingWith("DTO").check(baseClasses);
    }

    @Test
    void layeredArchitectureShouldBeRespected(){
        layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy("..webservice..")
                .layer("Service").definedBy("..service..")
                .layer("Persistence").definedBy("..persistence..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service");
    }

    @Test
    void controllerClassesShouldHaveSpringControllerAnnotation() {
        classes().that().resideInAPackage("..webservice..")
                .should().beAnnotatedWith("org.springframework.web.bind.annotation.RestController")
                .check(baseClasses);
    }

}
