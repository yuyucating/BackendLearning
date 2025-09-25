package com.gtalent.commerce.service.requests;

import java.util.List;

public class IdListRequest {
    private List<Integer> ids;

    public IdListRequest(List<Integer> ids){
        this.ids = ids;
    }
    public IdListRequest(){

    }
    public List<Integer> getIds(){
        return ids;
    }
    public void setIds(List<Integer> ids){
        this.ids = ids;
    }

}
