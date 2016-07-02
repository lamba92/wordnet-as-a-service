package com.github.jacopofar.wordnetservice.messages;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * An annotation to be sent back
 */
public class Annotation {
    Integer start;
    Integer end;

    public Annotation(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Annotation(int start, int end, ObjectNode posNote) {
        this.start = start;
        this.end = end;
        this.annotation = posNote;
    }

    public ObjectNode getAnnotation() {
        return annotation;
    }

    public void setAnnotation(ObjectNode annotation) {
        this.annotation = annotation;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    ObjectNode annotation;
}