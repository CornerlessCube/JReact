package cornerlesscube.jreact;

import java.util.ArrayList;
import java.util.List;

/**
 * Reactive value container
 * @param <T>
 */

public class Reactive<T> {

    private T value;
    private final List<ReactiveFunctionController> reactiveFunctions = new ArrayList<>();

    public Reactive(T value) {
        this.value = value;
    }

    /**
     * Set the value inside the container.
     * This will trigger the execution of all {@link ReactiveFunction}s
     * @param value The value to set inside the container
     */

    public void set(T value) {
        this.value = value;
        for(ReactiveFunctionController function : reactiveFunctions) {
            function.run();
        }
    }

    /**
     * Gets the value inside the container.
     * Using this method alone will not make any code
     * reactive. For that, use {@link Reactive}.use()
     * @return The value inside the container
     */

    public T get() {
        return value;
    }

    private void addReactiveFunction(ReactiveFunctionController function) {
        reactiveFunctions.add(function);
    }

    private void removeReactiveFunction(ReactiveFunctionController function) {
        reactiveFunctions.remove(function);
    }

    /**
     * Used to define a lambda function and make it reactive.
     * If any one of the {@link Reactive}s' values
     * change, the {@link ReactiveFunction} will be run.
     *
     * Usually, the {@link Reactive}s defined are
     * the ones used in the {@link ReactiveFunction}.
     *
     * @param function The lambda function to become reactive. ({@link ReactiveFunction})
     * @param reactives The {@link Reactive}s used in the lambda function
     * @return {@link ReactiveFunctionController} A controller of the
     * reactive function. Can be used to force-run or deactivate the function.
     */

    public static ReactiveFunctionController use(ReactiveFunction function, Reactive<?> ...reactives) {
        final ReactiveFunctionController controller = new ReactiveFunctionControllerImpl(function, reactives);
        for(Reactive<?> reactive : reactives) {
            reactive.addReactiveFunction(controller);
        }
        return controller;
    }

    private static class ReactiveFunctionControllerImpl implements ReactiveFunctionController {

        private boolean reactive = true;
        private ReactiveFunction function;
        private Reactive<?>[] reactives;

        public ReactiveFunctionControllerImpl(ReactiveFunction function, Reactive<?>... reactives) {
            this.function = function;
            this.reactives = reactives;
        }

        @Override
        public ReactiveFunctionController run() {
            if(!reactive) return this;
            function.run();
            return this;
        }

        @Override
        public void setReactive(boolean reactive) {
            this.reactive = reactive;
        }

        @Override
        public boolean isReactive() {
            return reactive;
        }

        @Override
        public void invalidate() {
            for (Reactive<?> reactive : reactives) {
                reactive.removeReactiveFunction(this);
            }
        }
    }

}
