/*
 * Copyright 2013-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.platform.cli.compiler;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.springframework.boot.cli.compiler.AstUtils;
import org.springframework.boot.cli.compiler.CompilerAutoConfiguration;
import org.springframework.boot.cli.compiler.DependencyCustomizer;

/**
 * @author Dave Syer
 *
 */
public class EurekaClientCompilerAutoConfiguration extends CompilerAutoConfiguration {

	@Override
	public boolean matches(ClassNode classNode) {
		return AstUtils.hasAtLeastOneAnnotation(classNode, "EnableEurekaClient");
	}

	@Override
	public void applyDependencies(DependencyCustomizer dependencies) {
		dependencies.ifAnyMissingClasses(
				"org.springframework.platform.netflix.eureka.EnableEurekaClient").add(
				"spring-platform-starter-eureka");
	}

	@Override
	public void applyImports(ImportCustomizer imports) throws CompilationFailedException {
		imports.addImports(
				"org.springframework.platform.netflix.eureka.EnableEurekaClient",
				"org.springframework.platform.netflix.eureka.EurekaInstanceConfigBean",
				"org.springframework.platform.netflix.eureka.EurekaClientConfigBean",
				"com.netflix.discovery.DiscoveryClient",
				"com.netflix.appinfo.InstanceInfo");
	}

}
