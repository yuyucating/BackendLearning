package com.gtalent.commerce.service.responses;

import com.gtalent.commerce.service.models.Segment;

public class SegmentResponse {
    private Segment segment;
    private int id;
    private String segment_name;

    public SegmentResponse() {
    }

    public SegmentResponse(int id, String segment_name) {
        this.id = id;
        this.segment_name = segment_name;
    }

    public SegmentResponse(Segment segment) {
        this.segment = segment;
        this.id = segment.getId();
        this.segment_name = segment.getName();
    }

    public String getSegment_name() {
        return segment_name;
    }

    public void setSegment_name(String segment_name) {
        this.segment_name = segment_name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

}
