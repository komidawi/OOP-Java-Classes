package com.github.komidawi;

public class SubtitleLine {
    private int startFrame;
    private int endFrame;
    private String line;

    public SubtitleLine(int startFrame, int endFrame, String line) {
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        this.line = line;
    }

    public String toString() {
        return String.format("{%d}{%d}%s", getStartFrame(), getEndFrame(), getLine());
    }

    public int getStartFrame() {
        return startFrame;
    }

    public int getEndFrame() {
        return endFrame;
    }

    public String getLine() {
        return line;
    }

    public void setStartFrame(int startFrame) {
        this.startFrame = startFrame;
    }

    public void setEndFrame(int endFrame) {
        this.endFrame = endFrame;
    }

    public void setLine(String line) {
        this.line = line;
    }
}

