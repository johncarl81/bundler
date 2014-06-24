/**
 * Copyright 2014 John Ericksen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bundler;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import org.androidtransfuse.adapter.element.ReloadableASTElementFactory;
import org.androidtransfuse.bootstrap.Bootstrap;
import org.androidtransfuse.bootstrap.Bootstraps;
import org.androidtransfuse.scope.ScopeKey;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author John Ericksen
 */
@SupportedAnnotationTypes("*")
@Bootstrap
public class BundlerAnnotationProcessor extends AbstractProcessor {

    @Inject
    private BundlerProcessor processor;
    @Inject
    private ReloadableASTElementFactory reloadableASTElementFactory;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        Bootstraps.getInjector(BundlerAnnotationProcessor.class)
                .add(Singleton.class, ScopeKey.of(ProcessingEnvironment.class), processingEnv)
                .inject(this);
    }

    @Override
    public boolean process(Set<? extends TypeElement> typeElements, RoundEnvironment roundEnvironment) {

        ImmutableSet<Element> containingElements = FluentIterable
                .from(roundEnvironment.getElementsAnnotatedWith(Bundled.class))
                .transform(new Function<Element, Element>() {
                    public Element apply(Element element) {
                        return element.getEnclosingElement();
                    }
                }).toSet();

        processor.submit(reloadableASTElementFactory.buildProviders(containingElements));

        processor.execute();

        if (roundEnvironment.processingOver()) {
            // Throws an exception if errors still exist.
            processor.checkForErrors();
        }

        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
