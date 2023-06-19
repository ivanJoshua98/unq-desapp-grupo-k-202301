package ar.edu.unq.grupok.backenddesappapi.arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

public class ArchTests {

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

//    @Test
//    void webserviceClassesShouldEndWithController(){
//        classes().that().resideInAPackage("..webservice..")
//                .should().haveSimpleNameEndingWith("Controller").check(baseClasses);
//    }


}
