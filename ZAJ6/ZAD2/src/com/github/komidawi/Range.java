package com.github.komidawi;

class Range {
    private double start;
    private double end;

    public Range(double start, double end) {
        if (end >= start) {
            this.start = start;
            this.end = end;
        } else {
            throw new IllegalArgumentException("Ending point must be greater or equal than starting point.");
        }
    }

    public Range(Range range) {
        this.start = range.start;
        this.end = range.end;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }
}