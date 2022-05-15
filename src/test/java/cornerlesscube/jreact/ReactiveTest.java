package cornerlesscube.jreact;

public class ReactiveTest {

    public static void main(String[] args) {
        Reactive<String> testString = new Reactive<>("Alice");
        ReactiveFunctionController reactiveCodeBlock = Reactive.use(() -> {
            System.out.println("Hello, " + testString.get());
        }, testString).run();
        testString.set("Bob");
        reactiveCodeBlock.invalidate();
    }

}
