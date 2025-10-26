package io.github.magwas.konveyor.runtime;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.github.magwas.konveyor.annotations.Glue;

@Configuration
@Glue
@ComponentScan(basePackages = {"io.github.magwas"})
public class Config {}
