package org.bundler;

import org.androidtransfuse.gen.invocationBuilder.PackageHelperGenerator;
import org.androidtransfuse.transaction.AbstractCompletionTransactionWorker;

import javax.inject.Inject;

public class PackageHelperGeneratorAdapter extends AbstractCompletionTransactionWorker<Void, Void> {

    private final PackageHelperGenerator generator;

    @Inject
    public PackageHelperGeneratorAdapter(PackageHelperGenerator generator) {
        this.generator = generator;
    }

    @Override
    public Void innerRun(Void value) {
        generator.generate();
        return null;
    }
}