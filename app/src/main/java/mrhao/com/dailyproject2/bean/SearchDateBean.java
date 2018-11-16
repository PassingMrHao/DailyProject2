package mrhao.com.dailyproject2.bean;

import java.util.ArrayList;
import java.util.List;

public class SearchDateBean {

    List<String> list=new ArrayList<>();
    List<String> imglist=new ArrayList<>();
    List<String> desclist=new ArrayList<>();

    @Override
    public String toString() {
        return "SearchDateBean{" +
                "list=" + list +
                ", imglist=" + imglist +
                ", desclist=" + desclist +
                '}';
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<String> getImglist() {
        return imglist;
    }

    public void setImglist(List<String> imglist) {
        this.imglist = imglist;
    }

    public List<String> getDesclist() {
        return desclist;
    }

    public void setDesclist(List<String> desclist) {
        this.desclist = desclist;
    }
}

