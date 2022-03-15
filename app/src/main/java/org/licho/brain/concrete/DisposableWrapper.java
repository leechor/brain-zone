package org.licho.brain.concrete;

import org.licho.brain.utils.simu.system.IDisposable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class DisposableWrapper implements IDisposable {
    private final IDisposable[] disposable;

    public DisposableWrapper(IDisposable[] disposables) {
        this.disposable = disposables;

    }

    @Override
    public void Dispose() {
        List<Exception> exceptions = new ArrayList<Exception>();
        for (IDisposable disposable : this.disposable)
        {
            try {
                disposable.Dispose();
            } catch (Exception item) {
                exceptions.add(item);
            }
        }
        if (exceptions.size() > 0) {
            throw new IllegalArgumentException(Arrays.toString(exceptions.toArray(new Exception[0])));
        }
    }
}
