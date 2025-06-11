package com.example;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.junit5.AnalyzeClasses;
import com.tngtech.archunit.junit5.ArchTest;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

@AnalyzeClasses(packages = "com.example")
public class ArchitectureTest {

    @ArchTest
    static final ArchRule endpoint_should_only_access_services =
            ArchRuleDefinition.classes()
                    .that().resideInAPackage("..controller..")
                    .should().onlyAccessClassesThat()
                    .resideInAnyPackage(
                            "..controller..",
                            "..service..",
                            "java..",
                            "org.springframework.."
                    );

    @ArchTest
    static final ArchRule services_should_be_named_properly =
            ArchRuleDefinition.classes()
                    .that().resideInAPackage("..service..")
                    .should().haveSimpleNameStartingWith("Serv");

    @ArchTest
    static final ArchRule service_should_not_access_controller =
            ArchRuleDefinition.noClasses()
                    .that().resideInAPackage("..service..")
                    .should().accessClassesThat().resideInAPackage("..controller..");

    @ArchTest
    static final ArchRule classes_should_not_exceed_700_lines =
            ArchRuleDefinition.classes()
                    .should(haveLessThan700Lines());

    private static ArchCondition<com.tngtech.archunit.core.domain.JavaClass> haveLessThan700Lines() {
        return new ArchCondition<>("have less than or equal to 700 lines") {
            @Override
            public void check(com.tngtech.archunit.core.domain.JavaClass item, ConditionEvents events) {
                item.getSource().ifPresent(source -> {
                    URI uri = source.getUri();
                    try {
                        long count = Files.lines(Paths.get(uri)).count();
                        if (count > 700) {
                            String message = item.getName() + " has " + count + " lines";
                            events.add(SimpleConditionEvent.violated(item, message));
                        }
                    } catch (IOException e) {
                        String message = "Could not read source for " + item.getName();
                        events.add(SimpleConditionEvent.violated(item, message));
                    }
                });
            }
        };
    }
}
