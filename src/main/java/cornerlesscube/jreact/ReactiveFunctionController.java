package cornerlesscube.jreact;

/**
 * A utility for controlling a {@link ReactiveFunction}
 */
public interface ReactiveFunctionController {

    /**
     * Run the {@link ReactiveFunction}
     * @return Returns the controller itself. Useful when
     * a {@link ReactiveFunction} has to be run right after
     * definition. In that case, this return value is useful
     * for a one-liner.
     */
    ReactiveFunctionController run();

    /**
     * Temporarily disable or enable reactivity
     * (aka the automatic execution of the {@link ReactiveFunction}
     * when an associated {@link Reactive} changes)
     * @param reactive Should reactivity be enabled
     */
    void setReactive(boolean reactive);

    /**
     * Get whether reactivity is enabled or not
     * @return Whether it is enabled
     */
    boolean isReactive();

    /**
     * Make the {@link ReactiveFunction} no longer reactive.
     * This means that the function will not be run automatically
     * anymore if any of the defined {@link Reactive}s change.
     * Should ONLY be used when reactivity is NO LONGER NEEDED.
     * CANNOT BE UNDONE.
     * (If you want something that can be undone, check the
     * setReactive() method)
     */
    void invalidate();

}
