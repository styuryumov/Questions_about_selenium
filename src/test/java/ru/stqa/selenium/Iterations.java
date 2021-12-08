package ru.stqa.selenium;

public class Iterations {

    public static Builder newEntity() { return new Iterations().new Builder(); }

    private int iterations;

    public int getIterations() {
        return iterations;
    }

    public class Builder {
        private Builder() {}
        public Builder withIterations(int iterations) { Iterations.this.iterations = iterations; return this; }
        public Iterations build() {return Iterations.this; }
    }
}
