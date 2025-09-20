package io.github.magwas.runtime;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.github.magwas.kodekonveyorannotations.Glue;

@Configuration
@Glue
@ComponentScan(basePackages = {"io.github.magwas"})
public class Config {}
