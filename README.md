# JReact
Variable reactivity similar to SolidJS, but in Java!

## Usage

Similar to SolidJS. When a reactive variable is changed, all related
reactive functions are re-run.
See example below. It's quite simple.

```java
// Create a reactive variable
Reactive<String> name = new Reactive<>("Alice");

// Create a reactive function
ReactiveFunctionController controller = Reactive.use(() -> {
  System.out.println("Hello, " + name.get())
}, name);

// Force-run the reactive function
//
// NOTE: Will not run if reactivity is disabled.
// (this might be subject to change)
controller.run();

// Prints out "Hello, Alice"

// Change the reactive variable
name.set("Bob");

// Prints out "Hello, Bob" AUTOMATICALLY, because
// the reactive variable was assigned to the reactive
// function. (It was included as a parameter for
// Reactive::use, just after the lambda)

// Temporarily disable reactivity
controller.setReactive(false);

name.set("John Doe");
// This time, nothing will be printed out, because
// reactivity has been temporarily disabled

// Re-enabling reactivity
controller.setReactive(true);

name.set("Jane Doe");
// Prints out "Hello, Jane Doe", because reactivity
// has been re-enabled

// Invalidates the reactive function, so it won't be
// called automatically anymore when any assigned
// reactive variables are changed. Also, running
// the function manually through controller.run()
// will still work.
//
// NOTE: THIS CANNOT BE UNDONE.
controller.invalidate();

```

## Maven repository

No maven repository yet.
